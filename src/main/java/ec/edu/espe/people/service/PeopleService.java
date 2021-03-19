/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.people.service;

import ec.edu.espe.people.model.People;
import ec.edu.espe.people.model.PeopleDB;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author esteban
 */

@Service
@Slf4j
public class PeopleService {
    
    public void savePeople(People people) {
        PeopleDB peopleDB = PeopleDB.getInstance();
        
        peopleDB.getPeopleDB().add(people);
    }
    
    public List<People> listAll() {
        PeopleDB peopleDB = PeopleDB.getInstance();
        return peopleDB.getPeopleDB();
    }
}
