package ec.edu.espe.people.database;

import ec.edu.espe.people.model.People;
import java.util.Dictionary;
import java.util.Hashtable;
import lombok.Data;

@Data
public class PeopleDb {

    private static PeopleDb peopleDBInstance = null;

    private Dictionary<String, People> peopleDb;

    public PeopleDb() {
        this.peopleDb = new Hashtable<String, People>();
    }

    public static PeopleDb getInstance() {
        if (peopleDBInstance == null) {
            peopleDBInstance = new PeopleDb();
        }
        return peopleDBInstance;
    }
}
