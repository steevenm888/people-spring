/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.people.service;

import ec.edu.espe.people.exception.InsertException;
import ec.edu.espe.people.exception.RegistryNotFoundException;
import ec.edu.espe.people.model.People;
import ec.edu.espe.people.model.PeopleDB;
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
    
    public void savePeople(People people) throws InsertException {
        PeopleDB peopleDB = PeopleDB.getInstance();
        List<People> persons = peopleDB.getPeopleDB();
        Optional<People> optionalPeople = persons.stream().
            filter(p -> p.getIdentification().equals(people.getIdentification())).
            findFirst();
        if(optionalPeople.isEmpty()) {
            peopleDB.getPeopleDB().add(people);
        } else {
            throw new InsertException("Can't insert person with "+people.getIdentification()+". Identification already exists");
        }
    }
    
    public List<People> listAll() {
        PeopleDB peopleDB = PeopleDB.getInstance();
        return peopleDB.getPeopleDB();
    }
    
    public People findByIdentification(String identification) throws RegistryNotFoundException {
        PeopleDB peopleDB = PeopleDB.getInstance();
        List<People> people = peopleDB.getPeopleDB();
        Optional<People> optionalPeople = people.stream().
            filter(p -> p.getIdentification().equals(identification)).
            findFirst();
        if(optionalPeople.isPresent()) {
            return optionalPeople.get();
        } else {
            log.error("No person found with {} as identification", identification);
            throw new RegistryNotFoundException("No person found with identification: "+identification);
        }
    }
}
