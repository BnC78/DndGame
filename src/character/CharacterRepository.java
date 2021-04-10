package character;

import java.util.List;

/**
 * Represents a DAO interface for the characters.
 */
public interface CharacterRepository {
    
    /**
     * Returns all the characters stored in the database.
     * @return all the characters stored in the database
     * @throws CharacterDAOException 
     */
    public abstract List<DndCharacter> findAll() throws CharacterDAOException;

    /**
     * Returns the character with the given id.
     * @param id id of the character
     * @return the character with the given id
     * @throws CharacterDAOException 
     */
    public abstract DndCharacter findById(int id) throws CharacterDAOException;

    /**
     * Returns the characters in the given campaign.
     * @param id id of the campaign
     * @return the characters in the given campaign.
     * @throws CharacterDAOException 
     */
    public abstract List<DndCharacter> findByCampaign(int id) throws CharacterDAOException;

    /**
     * Inserts the character into the database.
     * @param ch character to be inserted
     * @throws CharacterDAOException 
     */
    public abstract void insert(DndCharacter ch) throws CharacterDAOException;

    /**
     * Updates the character in the database.
     * @param ch character to be updated
     * @throws CharacterDAOException 
     */
    public abstract void update(DndCharacter ch) throws CharacterDAOException;

    /**
     * Saves the character to the database.
     * @param ch character to be saved
     * @throws CharacterDAOException 
     */
    public abstract void save(DndCharacter ch) throws CharacterDAOException;

    /**
     * Deletes the character from database.
     * @param id id of the character
     * @throws CharacterDAOException 
     */
    public abstract void delete(int id) throws CharacterDAOException;

    /**
     * Deletes all the campaign-character connections of the given character from the database.
     * @param id id of the character
     * @throws CharacterDAOException 
     */
    public abstract void deleteConnections(int id) throws CharacterDAOException;
    
    /**
     * Closes the character's database connections.
     * @throws CharacterDAOException 
     */
    public abstract void close() throws CharacterDAOException;
}
