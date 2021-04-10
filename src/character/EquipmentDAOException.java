package character;

/**
 * Represents an exception for the equipment's DAO functions.
 */
public class EquipmentDAOException extends Exception {

    public EquipmentDAOException(){}

    public EquipmentDAOException(String message) {
        super(message);
    }

    public EquipmentDAOException(Throwable cause) {
        super(cause);
    }

    public EquipmentDAOException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
