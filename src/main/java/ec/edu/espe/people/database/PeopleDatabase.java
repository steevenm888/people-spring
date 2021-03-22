package ec.edu.espe.people.database;

import ec.edu.espe.people.model.People;
import java.util.Dictionary;
import java.util.Hashtable;
import lombok.Getter;

@Getter
public class PeopleDatabase {

    private static PeopleDatabase peopleDBInstance;
    private Dictionary<String, People> peopleDb;

    public PeopleDatabase() {
        this.peopleDBInstance = null;
        this.peopleDb = new Hashtable<String, People>();
    }

    public static PeopleDatabase getInstance() {
        if (peopleDBInstance == null) {
            peopleDBInstance = new PeopleDatabase();
        }
        return peopleDBInstance;
    }
}
