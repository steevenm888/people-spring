package ec.edu.espe.people.database;

import ec.edu.espe.people.model.People;
import java.util.Dictionary;
import java.util.Hashtable;
import lombok.Data;

@Data
public class PeopleDB {
    private static PeopleDB peopleDBInstance = null;
    
    private Dictionary<String, People> peopleDB;
    
    public PeopleDB() {
        this.peopleDB = new Hashtable<String, People>();
    }
    
    public static PeopleDB getInstance() {
        if(peopleDBInstance == null) {
            peopleDBInstance = new PeopleDB();
        }
        return peopleDBInstance;
    }
}
