/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.people.service;

import ec.edu.espe.people.exception.InsertException;
import ec.edu.espe.people.exception.RegistryNotFoundException;
import ec.edu.espe.people.model.People;
import ec.edu.espe.people.database.PeopleDB;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author esteban
 */

@Service
@Slf4j
public class PeopleService {
    
    public void savePeople(People person) throws InsertException {
        PeopleDB peopleDB = PeopleDB.getInstance();
        List<People> persons = peopleDB.getPeopleDB();
        Optional<People> optionalPeople = persons.stream().
            filter(p -> p.getIdentification().equals(person.getIdentification())).
            findFirst();
        if(optionalPeople.isEmpty()) {
            log.info("Person saved succesfully");
            peopleDB.getPeopleDB().add(person);
        } else {
            throw new InsertException("Can't insert person with "+person.getIdentification()+". Identification already exists");
        }
    }
    
    public List<People> listAll() {
        PeopleDB peopleDB = PeopleDB.getInstance();
        log.info("Listed all the people in the database");
        return peopleDB.getPeopleDB();
    }
    
    public People findByIdentification(String identification) throws RegistryNotFoundException {
        PeopleDB peopleDB = PeopleDB.getInstance();
        List<People> people = peopleDB.getPeopleDB();
        Optional<People> optionalPeople = people.stream().
            filter(p -> p.getIdentification().equals(identification)).
            findFirst();
        if(optionalPeople.isPresent()) {
            log.info("Found a person with {} as identification");
            return optionalPeople.get();
        } else {
            throw new RegistryNotFoundException("No person found with identification: "+identification);
        }
    }
}
