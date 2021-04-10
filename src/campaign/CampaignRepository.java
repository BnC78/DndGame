package campaign;

import java.util.List;

/**
 * Represents a DAO interface for the campaigns.
 */
public interface CampaignRepository {
    
    /**
     * Returns all the campaigns stored in the database.
     * @return all the campaigns stored in the database
     * @throws CampaignDAOException 
     */
    public abstract List<Campaign> findAll() throws CampaignDAOException;
    
    /**
     * Returns the campaign with the given id.
     * @param id id of the campaign
     * @return the campaign with the given id
     * @throws CampaignDAOException 
     */
    public abstract Campaign findById(int id) throws CampaignDAOException;

    /**
     * Returns the campaigns that contains the given character.
     * @param id id of the character
     * @return the campaigns that contains the given character
     * @throws CampaignDAOException 
     */
    public abstract List<Campaign> findByCharacter(int id) throws CampaignDAOException;

    /**
     * Inserts the campaign into the database.
     * @param c campaign to be inserted
     * @throws CampaignDAOException 
     */
    public abstract void insert(Campaign c) throws CampaignDAOException;

    /**
     * Updates the campaign in the database.
     * @param c campaign to be updated
     * @throws CampaignDAOException 
     */
    public abstract void update(Campaign c) throws CampaignDAOException;
    
    /**
     * Saves the campaign to the database.
     * @param c campaign to be saved
     * @throws CampaignDAOException 
     */
    public abstract void save(Campaign c) throws CampaignDAOException;

    /**
     * Deletes the campaign from the database.
     * @param id id of the campaign
     * @throws CampaignDAOException 
     */
    public abstract void delete(int id) throws CampaignDAOException;
    
    /**
     * Deletes all the campaign-character connections of the given campaign from the database.
     * @param id id of the campaign
     * @throws CampaignDAOException 
     */
    public abstract void deleteConnections(int id) throws CampaignDAOException;
    
    /**
     * Adds the campaign-character connection to the database.
     * @param campaign id of the campaign
     * @param character id of the character
     * @throws CampaignDAOException 
     */
    public abstract void addCharacter(int campaign, int character) throws CampaignDAOException;
    
    /**
     * Removes the campaign-character connection from the database,
     * @param campaign id of the campaign
     * @param character id of the character
     * @throws CampaignDAOException 
     */
    public abstract void removeCharacter(int campaign, int character) throws CampaignDAOException;

    /**
     * Closes the campaign's database connections.
     * @throws CampaignDAOException 
     */
    public abstract void close() throws CampaignDAOException;
    
}
