package character;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the <code>AbilityRepository</code> for MySQL.
 */
public class AbilityRepositoryJDBCImpl implements AbilityRepository {

    private Connection conn;
    private PreparedStatement findAll;
    private PreparedStatement findById;
    private PreparedStatement findByCharacter;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement delete;

    public AbilityRepositoryJDBCImpl(Connection conn) throws AbilityDAOException {
        try {
            this.conn = conn;
            this.findAll = conn.prepareStatement("SELECT * FROM ability");
            this.findById = conn.prepareStatement("SELECT * FROM ability WHERE id = ?");
            this.findByCharacter = conn.prepareStatement("SELECT * FROM ability WHERE character_id = ?");
            this.insert = conn.prepareStatement("INSERT INTO ability (character_id, name) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            this.update = conn.prepareStatement("UPDATE ability SET character_id = ?, name = ? WHERE id = ?");
            this.delete = conn.prepareStatement("DELETE FROM ability WHERE id = ?");
        } catch (SQLException ex) {
            throw new AbilityDAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Ability> findAll() throws AbilityDAOException {
        List<Ability> ret;
        try {
            ResultSet all = this.findAll.executeQuery();
            ret = makeList(all);
            all.close();
        } catch (SQLException ex) {
            throw new AbilityDAOException(ex.getMessage(), ex);
        }
        return ret;
    }

    @Override
    public Ability findById(int id) throws AbilityDAOException {
        Ability ret;
        try {
            this.findById.setInt(1, id);
            ResultSet one = this.findById.executeQuery();
            ret = makeOne(one);
            one.close();
        } catch (SQLException ex) {
            throw new AbilityDAOException(ex.getMessage(), ex);
        }
        return ret;
    }

    @Override
    public List<Ability> findByCharacter(int id) throws AbilityDAOException {
        List<Ability> ret;
        try {
            this.findByCharacter.setInt(1, id);
            ResultSet all = this.findByCharacter.executeQuery();
            ret = makeList(all);
            all.close();
        } catch (SQLException ex) {
            throw new AbilityDAOException(ex.getMessage(), ex);
        }
        return ret;
    }

    @Override
    public void insert(Ability a, int character_id) throws AbilityDAOException {
        try {
            this.insert.setInt(1, character_id);
            this.insert.setString(2, a.getName());
            this.insert.executeUpdate();
            ResultSet generatedKeys = this.insert.getGeneratedKeys();
            generatedKeys.next();
            a.setId(generatedKeys.getInt(1));
            generatedKeys.close();
        } catch (SQLException ex) {
            throw new AbilityDAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void update(Ability a, int character_id) throws AbilityDAOException {
        try {
            this.update.setInt(1, character_id);
            this.update.setString(2, a.getName());
            this.update.setInt(3, a.getId());
            this.update.executeUpdate();
        } catch (SQLException ex) {
            throw new AbilityDAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void save(Ability a, int character_id) throws AbilityDAOException {
        if (a.getId() == null) {
            insert(a, character_id);
        } else {
            update(a, character_id);
        }
    }

    @Override
    public void delete(int id) throws AbilityDAOException {
        try {
            this.delete.setInt(1, id);
            this.delete.executeUpdate();
        } catch (SQLException ex) {
            throw new AbilityDAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void close() throws AbilityDAOException {
        try {
            this.findAll.close();
            this.findById.close();
            this.findByCharacter.close();
            this.insert.close();
            this.update.close();
            this.delete.close();
        } catch (SQLException ex) {
            throw new AbilityDAOException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Makes a list from the <code>ResultSet</code> generated by the database query.
     * @param rs
     * @return abilities returned by the database query
     * @throws SQLException 
     */
    private List<Ability> makeList(ResultSet rs) throws SQLException {
        List<Ability> ret = new ArrayList<>();
        while(rs.next()) {
            ret.add(makeOne(rs));
        }
        return ret;
    }
    
    /**
     * Creates one <code>Ability</code> from the given <code>ResultSet</code>.
     * @param rs
     * @return ability from the <code>ResultSet</code>
     * @throws SQLException 
     */
    private Ability makeOne(ResultSet rs) throws SQLException {
        Ability a = new Ability();
        a.setId(rs.getInt("id"));
        a.setName(rs.getString("name"));
        return a;
    }
}
