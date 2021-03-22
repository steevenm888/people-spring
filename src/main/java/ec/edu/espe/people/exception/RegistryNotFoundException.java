package ec.edu.espe.people.exception;

/**
 *
 * @author cofre
 */
public class RegistryNotFoundException extends RuntimeException{
    public RegistryNotFoundException(String msg) {
        super(msg);
    }
}
