package character;

/**
 * Represents an exception for the ability's DAO functions.
 */
public class AbilityDAOException extends Exception {

    public AbilityDAOException(){}

    public AbilityDAOException(String message) {
        super(message);
    }

    public AbilityDAOException(Throwable cause) {
        super(cause);
    }

    public AbilityDAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
