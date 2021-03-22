package ec.edu.espe.people.service;

import ec.edu.espe.people.database.PeopleDb;
import ec.edu.espe.people.exception.InsertException;
import ec.edu.espe.people.exception.RegistryNotFoundException;
import ec.edu.espe.people.model.People;
import java.util.Dictionary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PeopleService {

    public void savePeople(People person) throws InsertException {
        PeopleDb peopleDb = PeopleDb.getInstance();
        Dictionary<String, People> persons = peopleDb.getPeopleDb();
        try {
            this.findByIdentification(person.getIdentification());
            throw new InsertException("Can't insert person with "
                    + person.getIdentification()
                    + ". Identification already exists");
        } catch (RegistryNotFoundException e) {
            log.info("Person saved succesfully");
            persons.put(person.getIdentification(), person);
        }
    }

    public Dictionary<String, People> listAll() {
        PeopleDb peopleDb = PeopleDb.getInstance();
        log.info("Listed all the people in the database");
        return peopleDb.getPeopleDb();
    }

    public People findByIdentification(String identification) throws RegistryNotFoundException {
        PeopleDb peopleDb = PeopleDb.getInstance();
        Dictionary<String, People> people = peopleDb.getPeopleDb();
        People person = people.get(identification);
        if (person != null) {
            log.info("Found a person with {} as identification");
            return person;
        } else {
            throw new RegistryNotFoundException("No person found with identification: " + identification);
        }
    }
}
