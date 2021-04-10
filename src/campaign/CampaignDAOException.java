package campaign;

/**
 * Represents an exception for the campaigns's DAO functions.
 */
public class CampaignDAOException extends Exception {

    public CampaignDAOException(){}

    public CampaignDAOException(String message) {
        super(message);
    }

    public CampaignDAOException(Throwable cause) {
        super(cause);
    }

    public CampaignDAOException(String message, Throwable cause) {
        super(message, cause);
    }
}