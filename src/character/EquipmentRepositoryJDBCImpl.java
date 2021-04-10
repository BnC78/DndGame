package character;

import enums.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the <code>EquipmentRepository</code> for MySQL.
 */
public class EquipmentRepositoryJDBCImpl implements EquipmentRepository {

    private Connection conn;
    private PreparedStatement findAll;
    private PreparedStatement findById;
    private PreparedStatement findByCharacter;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement delete;
    
    public EquipmentRepositoryJDBCImpl(Connection conn) throws EquipmentDAOException {
        try {
            this.conn = conn;
            this.findAll = conn.prepareStatement("SELECT * FROM equipment");
            this.findById = conn.prepareStatement("SELECT * FROM equipment WHERE id = ?");
            this.findByCharacter = conn.prepareStatement("SELECT * FROM equipment WHERE character_id = ?");
            this.insert = conn.prepareStatement("INSERT INTO equipment (character_id, type, name, quantity) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            this.update = conn.prepareStatement("UPDATE equipment SET character_id = ?, type = ?, name = ?, quantity = ? WHERE id = ?");
            this.delete = conn.prepareStatement("DELETE FROM equipment WHERE id = ?");
        } catch (SQLException ex) {
            throw new EquipmentDAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Equipment> findAll() throws EquipmentDAOException {
        List<Equipment> ret;
        try {
            ResultSet all = this.findAll.executeQuery();
            ret = makeList(all);
            all.close();
        } catch (SQLException ex) {
            throw new EquipmentDAOException(ex.getMessage(), ex);
        }
        return ret;
    }

    @Override
    public Equipment findById(int id) throws EquipmentDAOException {
        Equipment ret;
        try {
            this.findById.setInt(1, id);
            ResultSet one = this.findById.executeQuery();
            ret = makeOne(one);
            one.close();
        } catch (SQLException ex) {
            throw new EquipmentDAOException(ex.getMessage(), ex);
        }
        return ret;
    }

    @Override
    public List<Equipment> findByCharacter(int id) throws EquipmentDAOException {
        List<Equipment> ret;
        try {
            this.findByCharacter.setInt(1, id);
            ResultSet all = this.findByCharacter.executeQuery();
            ret = makeList(all);
            all.close();
        } catch (SQLException ex) {
            throw new EquipmentDAOException(ex.getMessage(), ex);
        }
        return ret;
    }

    @Override
    public void insert(Equipment e, int character_id) throws EquipmentDAOException {
        try {
            this.insert.setInt(1, character_id);
            this.insert.setString(2, e.getType().toString());
            this.insert.setString(3, e.getName());
            this.insert.setInt(4, e.getQuantity());
            this.insert.executeUpdate();
            ResultSet generatedKeys = this.insert.getGeneratedKeys();
            generatedKeys.next();
            e.setId(generatedKeys.getInt(1));
            generatedKeys.close();
        } catch (SQLException ex) {
            throw new EquipmentDAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void update(Equipment e, int character_id) throws EquipmentDAOException {
        try {
            this.update.setInt(1, character_id);
            this.update.setString(2, e.getType().toString());
            this.update.setString(3, e.getName());
            this.update.setInt(4, e.getQuantity());
            this.update.setInt(5, e.getId());
            this.update.executeUpdate();
        } catch (SQLException ex) {
            throw new EquipmentDAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void save(Equipment e, int character_id) throws EquipmentDAOException {
        if (e.getId() == null) {
            insert(e, character_id);
        } else {
            update(e, character_id);
        }
    }

    @Override
    public void delete(int id) throws EquipmentDAOException {
        try {
            this.delete.setInt(1, id);
            this.delete.executeUpdate();
        } catch (SQLException ex) {
            throw new EquipmentDAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void close() throws EquipmentDAOException {
        try {
            this.findAll.close();
            this.findById.close();
            this.findByCharacter.close();
            this.insert.close();
            this.update.close();
            this.delete.close();
        } catch (SQLException ex) {
            throw new EquipmentDAOException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Makes a list from the <code>ResultSet</code> generated by the database query.
     * @param rs
     * @return equipment returned by the database query
     * @throws SQLException 
     */
    private List<Equipment> makeList(ResultSet rs) throws SQLException {
        List<Equipment> ret = new ArrayList<>();
        while(rs.next()) {
            ret.add(makeOne(rs));
        }
        return ret;
    }
    
    /**
     * Creates one <code>Equipment</code> from the given <code>ResultSet</code>.
     * @param rs
     * @return equipment from the <code>ResultSet</code>
     * @throws SQLException 
     */
    private Equipment makeOne(ResultSet rs) throws SQLException {
        Equipment e = new Equipment();
        e.setId(rs.getInt("id"));
        e.setType(getType(rs.getString("type")));
        e.setName(rs.getString("name"));
        e.setQuantity(rs.getInt("quantity"));
        return e;
    }
    
    /**
     * Returns the <code>Type</code> with the string representation <code>type</code>.
     * @param type string representation of the <code>Type</code>
     * @return the <code>Type</code> with the string representation <code>type</code>
     */
    private Type getType(String type) {
        for (Type t : Type.values()) {
            if (type.equals(t.toString())) {
                return t;
            }
        }
        return null;
    }
}
