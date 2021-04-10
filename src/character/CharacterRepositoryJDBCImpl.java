package character;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import enums.CharacterClass;
import enums.Race;
import enums.Traits;
import java.util.HashSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Implements the <code>CharacterRepository</code> for MySQL.
 */
public class CharacterRepositoryJDBCImpl implements CharacterRepository {

    private Connection conn;
    private PreparedStatement findAll;
    private PreparedStatement findById;
    private PreparedStatement findByCampaign;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement delete;
    private PreparedStatement deleteConnections;

    public CharacterRepositoryJDBCImpl(Connection conn) throws CharacterDAOException {
        try {
            this.conn = conn;
            this.findAll = conn.prepareStatement("SELECT * FROM dnd_character");
            this.findById = conn.prepareStatement("SELECT * FROM dnd_character WHERE id = ?");
            this.findByCampaign = conn.prepareStatement("SELECT * FROM dnd_character INNER JOIN campaign_character ON dnd_character.id = campaign_character.character_id WHERE campaign_character.campaign_id = ?");
            this.insert = conn.prepareStatement("INSERT INTO dnd_character (name, birth_place, race, class, strength, dexterity, constitution, intelligence, wisdom, charisma, level, hit_points) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            this.update = conn.prepareStatement("UPDATE dnd_character SET name = ?, birth_place = ?, race = ?, class = ?, strength = ?, dexterity = ?, constitution = ?, intelligence = ?, wisdom = ?, charisma = ?, level = ?, hit_points = ? WHERE id = ?");
            this.delete = conn.prepareStatement("DELETE FROM dnd_character WHERE id = ?");
            this.deleteConnections = conn.prepareStatement("DELETE FROM campaign_character WHERE character_id = ?");
        } catch (SQLException ex) {
            throw new CharacterDAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<DndCharacter> findAll() throws CharacterDAOException {
        List<DndCharacter> ret;
        try {
            ResultSet all = this.findAll.executeQuery();
            ret = makeList(all);
            all.close();
        } catch (SQLException ex) {
            throw new CharacterDAOException(ex.getMessage(), ex);
        }
        return ret;
    }

    @Override
    public DndCharacter findById(int id) throws CharacterDAOException {
        DndCharacter ret;
        try {
            this.findById.setInt(1, id);
            ResultSet one = this.findById.executeQuery();
            ret = makeOne(one);
            one.close();
        } catch (SQLException ex) {
            throw new CharacterDAOException(ex.getMessage(), ex);
        }
        return ret;
    }

    @Override
    public List<DndCharacter> findByCampaign(int id) throws CharacterDAOException {
        List<DndCharacter> ret;
        try {
            this.findByCampaign.setInt(1, id);
            ResultSet all = this.findByCampaign.executeQuery();
            ret = makeList(all);
            all.close();
        } catch (SQLException ex) {
            throw new CharacterDAOException(ex.getMessage(), ex);
        }
        return ret;
    }

    @Override
    public void insert(DndCharacter ch) throws CharacterDAOException {
        try {
            this.insert.setString(1, ch.getName());
            this.insert.setString(2, ch.getBirthPlace());
            this.insert.setString(3, ch.getRace().toString());
            this.insert.setString(4, ch.getCharacterClass().toString());
            this.insert.setInt(5, ch.getTrait(Traits.STRENGTH));
            this.insert.setInt(6, ch.getTrait(Traits.DEXTERITY));
            this.insert.setInt(7, ch.getTrait(Traits.CONSTITUTION));
            this.insert.setInt(8, ch.getTrait(Traits.INTELLIGENCE));
            this.insert.setInt(9, ch.getTrait(Traits.WISDOM));
            this.insert.setInt(10, ch.getTrait(Traits.CHARISMA));
            this.insert.setInt(11, ch.getLevel());
            this.insert.setInt(12, ch.getHitPoints());
            this.insert.executeUpdate();
            ResultSet generatedKeys = this.insert.getGeneratedKeys();
            generatedKeys.next();
            ch.setId(generatedKeys.getInt(1));
            generatedKeys.close();
        } catch (SQLException ex) {
            throw new CharacterDAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void update(DndCharacter ch) throws CharacterDAOException {
        try {
            this.update.setString(1, ch.getName());
            this.update.setString(2, ch.getBirthPlace());
            this.update.setString(3, ch.getRace().toString());
            this.update.setString(4, ch.getCharacterClass().toString());
            this.update.setInt(5, ch.getTrait(Traits.STRENGTH));
            this.update.setInt(6, ch.getTrait(Traits.DEXTERITY));
            this.update.setInt(7, ch.getTrait(Traits.CONSTITUTION));
            this.update.setInt(8, ch.getTrait(Traits.INTELLIGENCE));
            this.update.setInt(9, ch.getTrait(Traits.WISDOM));
            this.update.setInt(10, ch.getTrait(Traits.CHARISMA));
            this.update.setInt(11, ch.getLevel());
            this.update.setInt(12, ch.getHitPoints());
            this.update.setInt(13, ch.getId());
            this.update.executeUpdate();
        } catch (SQLException ex) {
            throw new CharacterDAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void save(DndCharacter ch) throws CharacterDAOException {
        if (ch.getId() == null) {
            insert(ch);
        } else {
            update(ch);
        }
    }

    @Override
    public void delete(int id) throws CharacterDAOException {
        try {
            this.delete.setInt(1, id);
            this.delete.executeUpdate();
        } catch (SQLException ex) {
            throw new CharacterDAOException(ex.getMessage(), ex);
        }
    }
    
    @Override
    public void deleteConnections(int id) throws CharacterDAOException {
        try {
            this.deleteConnections.setInt(1, id);
            this.deleteConnections.executeUpdate();
        } catch (SQLException ex) {
            throw new CharacterDAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void close() throws CharacterDAOException {
        try {
            this.findAll.close();
            this.findById.close();
            this.findByCampaign.close();
            this.insert.close();
            this.update.close();
            this.delete.close();
            this.deleteConnections.close();
        } catch (SQLException ex) {
            throw new CharacterDAOException(ex.getMessage(), ex);
        }
    }

    /**
     * Makes a list from the <code>ResultSet</code> generated by the database query. 
     * @param rs
     * @return characters returned by the database query
     * @throws SQLException 
     */
    private List<DndCharacter> makeList(ResultSet rs) throws SQLException {
        List<DndCharacter> ret = new ArrayList<>();
        while(rs.next()) {
            ret.add(makeOne(rs));
        }
        return ret;
    }

    /**
     * Creates one <code>DndCharacter</code> from the given <code>ResultSet</code>.
     * @param rs
     * @return character from the <code>ResultSet</code>.
     * @throws SQLException 
     */
    private DndCharacter makeOne(ResultSet rs) throws SQLException {
        DndCharacter ch = new DndCharacter();
        ch.setId(rs.getInt("id"));
        ch.setName(rs.getString("name"));
        ch.setBirthPlace(rs.getString("birth_place"));
        ch.setRace(getRace(rs.getString("race")));
        ch.setCharacterClass(getCharacterClass(rs.getString("class")));
        ch.setTrait(Traits.STRENGTH, rs.getInt("strength"));
        ch.setTrait(Traits.DEXTERITY, rs.getInt("dexterity"));
        ch.setTrait(Traits.CONSTITUTION, rs.getInt("constitution"));
        ch.setTrait(Traits.INTELLIGENCE, rs.getInt("intelligence"));
        ch.setTrait(Traits.WISDOM, rs.getInt("wisdom"));
        ch.setTrait(Traits.CHARISMA, rs.getInt("charisma"));
        try {
            AbilityRepository abilityRepository = new AbilityRepositoryJDBCImpl(this.conn);
            List<Ability> abilities = abilityRepository.findByCharacter(ch.getId());
            ch.setAbilities(new HashSet<>((abilities != null) ? abilities : new ArrayList<>()));
            abilityRepository.close();
        } catch (AbilityDAOException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "A " + ch.getName() + " karakter képességeinek betöltése sikertelen.", "Adatbázis hiba!", JOptionPane.ERROR_MESSAGE);
        }
        try {
            EquipmentRepository equipmentRepository = new EquipmentRepositoryJDBCImpl(this.conn);
            List<Equipment> equipment = equipmentRepository.findByCharacter(ch.getId());
            ch.setEquipment(new HashSet<>((equipment != null) ? equipment : new ArrayList<>()));
            equipmentRepository.close();
        } catch (EquipmentDAOException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "A " + ch.getName() + " karakter felszereléseinek betöltése sikertelen.", "Adatbázis hiba!", JOptionPane.ERROR_MESSAGE);
        }
        ch.setLevel(rs.getInt("level"));
        ch.setHitPoints(rs.getInt("hit_points"));
        return ch;
    }

    /**
     * Returns the <code>Race</code> with the string representation <code>race</code>.
     * @param race string representation of the <code>Race</code>
     * @return the <code>Race</code> with the string representation <code>race</code>.
     */
    private Race getRace(String race) {
        for (Race r : Race.values()) {
            if (race.equals(r.toString())) {
                return r;
            }
        }
        return null;
    }
    
    /**
     * Returns the <code>CharacterClass</code> with the string representation <code>characterClass</code>.
     * @param characterClass string representation of the <code>CharacterClass</code>
     * @return the <code>CharacterClass</code> with the string representation <code>characterClass</code>.
     */
    private CharacterClass getCharacterClass(String characterClass) {
        for (CharacterClass cc : CharacterClass.values()) {
            if (characterClass.equals(cc.toString())) {
                return cc;
            }
        }
        return null;
    }
}