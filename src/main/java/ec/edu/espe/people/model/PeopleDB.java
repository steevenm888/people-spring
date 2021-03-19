/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.people.model;

import java.util.ArrayList;
import lombok.Data;

/**
 *
 * @author esteban
 */

@Data
public class PeopleDB {
    private static PeopleDB peopleDBInstance = null;
    
    private ArrayList<People> peopleDB = new ArrayList<>();
    
    public static PeopleDB getInstance() {
        if(peopleDBInstance == null) {
            peopleDBInstance = new PeopleDB();
        }
        return peopleDBInstance;
    }
}
