package character;

/**
 * Represents an exception for the character's DAO functions.
 */
public class CharacterDAOException extends Exception {

    public CharacterDAOException(){}

    public CharacterDAOException(String message) {
        super(message);
    }

    public CharacterDAOException(Throwable cause) {
        super(cause);
    }

    public CharacterDAOException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
