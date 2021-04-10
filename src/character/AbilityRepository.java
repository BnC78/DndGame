package character;

import java.util.List;

/**
 * Represents a DAO interface for abilities equipment.
 */
public interface AbilityRepository {
    
    /**
     * Returns all the abilities stored in the database.
     * @return all the abilities stored in the database
     * @throws AbilityDAOException 
     */
    public abstract List<Ability> findAll() throws AbilityDAOException;

    /**
     * Returns the ability with the given id.
     * @param id id of the ability
     * @return the ability with the given id
     * @throws AbilityDAOException 
     */
    public abstract Ability findById(int id) throws AbilityDAOException;

    /**
     * Returns the abilities of the given character.
     * @param id id of the character
     * @return the abilities of the given character
     * @throws AbilityDAOException 
     */
    public abstract List<Ability> findByCharacter(int id) throws AbilityDAOException;

    /**
     * Inserts the ability into the database.
     * @param a ability to be inserted
     * @param character_id id of the character with this ability
     * @throws AbilityDAOException 
     */
    public abstract void insert(Ability a, int character_id) throws AbilityDAOException;

    /**
     * Updates the ability in the database.
     * @param a ability to be updated
     * @param character_id id of the character with this ability
     * @throws AbilityDAOException 
     */
    public abstract void update(Ability a, int character_id) throws AbilityDAOException;

    /**
     * Saves the ability to the database.
     * @param a ability to be saved
     * @param character_id id of the character with this ability
     * @throws AbilityDAOException 
     */
    public abstract void save(Ability a, int character_id) throws AbilityDAOException;

    /**
     * Deletes the ability from the database.
     * @param id id of the ability.
     * @throws AbilityDAOException 
     */
    public abstract void delete(int id) throws AbilityDAOException;

    /**
     * Closes the ability's database connections.
     * @throws AbilityDAOException 
     */
    public abstract void close() throws AbilityDAOException;
}
