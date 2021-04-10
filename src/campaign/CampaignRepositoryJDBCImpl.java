package campaign;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import character.CharacterDAOException;
import character.CharacterRepository;
import character.CharacterRepositoryJDBCImpl;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Implements the <code>CampaignRepository</code> for MySQL.
 */
public class CampaignRepositoryJDBCImpl implements CampaignRepository {

    private Connection conn;
    private PreparedStatement findAll;
    private PreparedStatement findById;
    private PreparedStatement findByCharacter;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement delete;
    private PreparedStatement deleteConnections;
    private PreparedStatement addCharacter;
    private PreparedStatement removeCharacter;

    public CampaignRepositoryJDBCImpl(Connection conn) throws CampaignDAOException {
        try {
            this.conn = conn;
            this.findAll = conn.prepareStatement("SELECT * FROM campaign");
            this.findById = conn.prepareStatement("SELECT * FROM campaign WHERE id = ?");
            this.findByCharacter = conn.prepareStatement("SELECT campaign.* FROM campaign INNER JOIN campaign_character ON campaing.id = campaign_character.campaign_id WHERE campaign_character.character_id = ?");
            this.insert = conn.prepareStatement("INSERT INTO campaign (name, dungeon_master, world, background_story) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            this.update = conn.prepareStatement("UPDATE campaign SET name = ?, dungeon_master = ?, world = ?, background_story = ? WHERE id = ?");
            this.delete = conn.prepareStatement("DELETE FROM campaign WHERE id = ?");
            this.deleteConnections = conn.prepareStatement("DELETE FROM campaign_character WHERE campaign_id = ?");
            this.addCharacter = conn.prepareStatement("INSERT INTO campaign_character (campaign_id, character_id) VALUES (?, ?)");
            this.removeCharacter = conn.prepareStatement("DELETE FROM campaign_character WHERE campaign_id = ? AND character_id = ?");
        } catch (SQLException ex) {
            throw new CampaignDAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Campaign> findAll() throws CampaignDAOException {
        List<Campaign> ret;
        try {
            ResultSet all = this.findAll.executeQuery();
            ret = makeList(all);
            all.close();
        } catch (SQLException ex) {
            throw new CampaignDAOException(ex.getMessage(), ex);
        }
        return ret;
    }

    @Override
    public Campaign findById(int id) throws CampaignDAOException {
        Campaign ret;
        try {
            this.findById.setInt(1, id);
            ResultSet one = this.findById.executeQuery();
            ret = makeOne(one);
            one.close();
        } catch (SQLException ex) {
            throw new CampaignDAOException(ex.getMessage(), ex);
        }
        return ret;
    }

    @Override
    public List<Campaign> findByCharacter(int id) throws CampaignDAOException {
        List<Campaign> ret;
        try {
            this.findByCharacter.setInt(1, id);
            ResultSet all = this.findByCharacter.executeQuery();
            ret = makeList(all);
            all.close();
        } catch (SQLException ex) {
            throw new CampaignDAOException(ex.getMessage(), ex);
        }
        return ret;
    }

    @Override
    public void insert(Campaign c) throws CampaignDAOException {
        try {
            this.insert.setString(1, c.getName());
            this.insert.setString(2, c.getDungeonMaster());
            this.insert.setString(3, c.getWorld());
            this.insert.setString(4, c.getBackgroundStory());
            this.insert.executeUpdate();
            ResultSet generatedKeys = this.insert.getGeneratedKeys();
            generatedKeys.next();
            c.setId(generatedKeys.getInt(1));
            generatedKeys.close();
        } catch (SQLException ex) {
            throw new CampaignDAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void update(Campaign c) throws CampaignDAOException {
        try {
            this.update.setString(1, c.getName());
            this.update.setString(2, c.getDungeonMaster());
            this.update.setString(3, c.getWorld());
            this.update.setString(4, c.getBackgroundStory());
            this.update.setInt(5, c.getId());
            this.update.executeUpdate();
        } catch (SQLException ex) {
            throw new CampaignDAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void save(Campaign c) throws CampaignDAOException {
        if (c.getId() == null) {
            insert(c);
        } else {
            update(c);
        }
    }

    @Override
    public void delete(int id) throws CampaignDAOException {
        try {
            this.delete.setInt(1, id);
            this.delete.executeUpdate();
        } catch (SQLException ex) {
            throw new CampaignDAOException(ex.getMessage(), ex);
        }
    }
    
    @Override
    public void deleteConnections(int id) throws CampaignDAOException {
        try {
            this.deleteConnections.setInt(1, id);
            this.deleteConnections.executeUpdate();
        } catch (SQLException ex) {
            throw new CampaignDAOException(ex.getMessage(), ex);
        }
    }
    
    @Override
    public void addCharacter(int campaign, int character) throws CampaignDAOException {
        try {
            this.addCharacter.setInt(1, campaign);
            this.addCharacter.setInt(2, character);
            this.addCharacter.executeUpdate();
        } catch (SQLException ex) {
            throw new CampaignDAOException(ex.getMessage(), ex);
        }
    }
    
    @Override
    public void removeCharacter(int campaign, int character) throws CampaignDAOException {
        try {
            this.removeCharacter.setInt(1, campaign);
            this.removeCharacter.setInt(2, character);
            this.removeCharacter.executeUpdate();
        } catch (SQLException ex) {
            throw new CampaignDAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void close() throws CampaignDAOException {
        try {
            this.findAll.close();
            this.findById.close();
            this.findByCharacter.close();
            this.insert.close();
            this.update.close();
            this.delete.close();
            this.deleteConnections.close();
            this.addCharacter.close();
            this.removeCharacter.close();
        } catch (SQLException ex) {
            throw new CampaignDAOException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Makes a list from the <code>ResultSet</code> generated by the database query.
     * @param rs
     * @return campaigns returned by the database query
     * @throws SQLException 
     */
    private List<Campaign> makeList(ResultSet rs) throws SQLException {
        List<Campaign> ret = new ArrayList<>();
        while(rs.next()) {
            ret.add(makeOne(rs));
        }
        return ret;
    }

    /**
     * Creates one <code>Campaign</code> from the given <code>ResultSet</code>.
     * @param rs
     * @return campaign from the <code>ResultSet</code>
     * @throws SQLException 
     */
    private Campaign makeOne(ResultSet rs) throws SQLException {
        Campaign c = new Campaign();
        c.setId(rs.getInt("id"));
        c.setName(rs.getString("name"));
        c.setDungeonMaster(rs.getString("dungeon_master"));
        c.setWorld(rs.getString("world"));
        try {
            CharacterRepository characterRepository = new CharacterRepositoryJDBCImpl(this.conn);
            c.addCharacters(characterRepository.findByCampaign(c.getId()));
            characterRepository.close();
        } catch (CharacterDAOException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "A " + c.getName() + " kampány karaktereinek betöltése sikertelen.", "Adatbázis hiba!", JOptionPane.ERROR_MESSAGE);
        }
        c.setBackgroundStory(rs.getString("background_story"));
        return c;
    }
}
