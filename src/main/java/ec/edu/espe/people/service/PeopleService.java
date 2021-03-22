package ec.edu.espe.people.service;

import ec.edu.espe.people.database.PeopleDatabase;
import ec.edu.espe.people.exception.InsertException;
import ec.edu.espe.people.exception.RegistryNotFoundException;
import ec.edu.espe.people.model.People;
import java.util.Dictionary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PeopleService {

    private final Dictionary<String, People> peopleDatabase;

    public PeopleService() {
        this.peopleDatabase = PeopleDatabase.getInstance().getPeopleDb();
    }

    public void savePeople(People person) throws InsertException {
        try {
            this.findByIdentification(person.getIdentification());
            throw new InsertException("Can't insert person with "
                    + person.getIdentification()
                    + ". Identification already exists");
        } catch (RegistryNotFoundException e) {
            this.peopleDatabase.put(person.getIdentification(), person);
            log.info("Person saved succesfully");
        }
    }

    public Dictionary<String, People> listAll() {
        log.info("Listed all the people in the database");
        return this.peopleDatabase;
    }

    public People findByIdentification(String identification) throws RegistryNotFoundException {
        People person = this.peopleDatabase.get(identification);
        if (person != null) {
            log.info("Found a person with {} as identification");
            return person;
        } else {
            throw new RegistryNotFoundException("No person found with identification: " + identification);
        }
    }
}
