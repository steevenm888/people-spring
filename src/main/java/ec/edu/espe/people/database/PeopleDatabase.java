package ec.edu.espe.people.database;

import ec.edu.espe.people.model.People;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

/**
 *
 * @author esteban
 */
@Getter
public class PeopleDatabase {

    private static PeopleDatabase peopleDbInstance;
    private final Map<String, People> peopleDatabase;

    public PeopleDatabase() {
        this.peopleDatabase = new HashMap<>();
        PeopleDatabase.peopleDbInstance = null;
    }

    public static PeopleDatabase getInstance() {
        if (peopleDbInstance == null) {
            peopleDbInstance = new PeopleDatabase();
        }
        return peopleDbInstance;
    }
}
