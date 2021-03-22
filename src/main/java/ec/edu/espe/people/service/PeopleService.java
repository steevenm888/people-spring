package ec.edu.espe.people.service;

import ec.edu.espe.people.exception.InsertException;
import ec.edu.espe.people.exception.RegistryNotFoundException;
import ec.edu.espe.people.model.People;
import ec.edu.espe.people.database.PeopleDatabase;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author esteban
 */
@Service
@Slf4j
public class PeopleService {

    private final Map<String, People> peopleDatabase;

    public PeopleService() {
        this.peopleDatabase = PeopleDatabase.getInstance().getPeopleDatabase();
    }

    public void savePeople(People newPerson) throws InsertException {
        if (this.peopleDatabase.putIfAbsent(newPerson.getIdentification(), newPerson) == null) {
            log.info("Person saved succesfully");
        } else {
            throw new InsertException("Can't insert person with "
                    + newPerson.getIdentification()
                    + ". Identification already exists");
        }
    }

    public List<People> listAll() {
        log.info("Listed people");
        return (List<People>) peopleDatabase.values();
    }

    public People findByIdentification(String identification) throws RegistryNotFoundException {
        People person = this.peopleDatabase.get(identification);
        if (person != null) {
            log.info("Found a person with {} as identification", identification);
            return person;
        } else {
            throw new RegistryNotFoundException("No person found with identification: " + identification);
        }
    }
}
