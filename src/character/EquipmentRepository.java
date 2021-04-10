package character;

import java.util.List;

/**
 * Represents a DAO interface for the equipment.
 */
public interface EquipmentRepository {
    
    /**
     * Returns all the equipment stored in the database.
     * @return all the equipment stored in the database.
     * @throws EquipmentDAOException 
     */
    public abstract List<Equipment> findAll() throws EquipmentDAOException;

    /**
     * Returns the equipment with the given id.
     * @param id id of the equipment
     * @return the equipment with the given id.
     * @throws EquipmentDAOException 
     */
    public abstract Equipment findById(int id) throws EquipmentDAOException;

    /**
     * Returns the equipment of the given character.
     * @param id id of the character
     * @return the equipment of the given character
     * @throws EquipmentDAOException 
     */
    public abstract List<Equipment> findByCharacter(int id) throws EquipmentDAOException;

    /**
     * Inserts the equipment into the database.
     * @param e equipment to be inserted
     * @param character_id id of the character with this equipment
     * @throws EquipmentDAOException 
     */
    public abstract void insert(Equipment e, int character_id) throws EquipmentDAOException;

    /**
     * Updates the equipment in the database.
     * @param e equipment to be updated
     * @param character_id id of the character with this equipment
     * @throws EquipmentDAOException 
     */
    public abstract void update(Equipment e, int character_id) throws EquipmentDAOException;

    /**
     * Saves the equipment to the database.
     * @param e equipment to be saved
     * @param character_id id of the character with this equipment
     * @throws EquipmentDAOException 
     */
    public abstract void save(Equipment e, int character_id) throws EquipmentDAOException;

    /**
     * Deletes the equipment from the database.
     * @param id id of the equipment
     * @throws EquipmentDAOException 
     */
    public abstract void delete(int id) throws EquipmentDAOException;

    /**
     * Closes the equipment's database connections.
     * @throws EquipmentDAOException 
     */
    public abstract void close() throws EquipmentDAOException;

}
