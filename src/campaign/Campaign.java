package campaign;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import character.DndCharacter;

/**
 * Represents a campaign from the game Dungeons and Dragons.
 */
public class Campaign {
    
    private Integer id;
    private String name;
    private String dungeonMaster;
    private String world;
    private Set<DndCharacter> characters;
    private String backgroundStory;
    
    public Campaign() {
        this(null, "", "", "", new HashSet<>(), "");
    }
    
    public Campaign(String name, String dungeonMaster, String world, String backgroundStory) {
        this(null, name, dungeonMaster, world, backgroundStory);
    }

    public Campaign(Integer id, String name, String dungeonMaster, String world, String backgroundStory) {
        this(id, name, dungeonMaster, world, new HashSet<DndCharacter>(), backgroundStory);
    }

    public Campaign(String name, String dungeonMaster, String world, Set<DndCharacter> characters, String backgroundStory) {
        this(null, name, dungeonMaster, world, characters, backgroundStory);
    }

    public Campaign(Integer id, String name, String dungeonMaster, String world, Set<DndCharacter> characters, String backgroundStory) {
        this.id = id;
        this.name = name;
        this.dungeonMaster = dungeonMaster;
        this.world = world;
        this.characters = characters;
        this.backgroundStory = backgroundStory;
    }

    /**
     * Returns the id of the campaign.
     * @return id of the campaign
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Sets the id of the campaign.
     * @param id id to be set for the campaign
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the name of the campaign 
     * @return name of the campaign 
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the campaign.
     * @param name name to be set for the campaign
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the Dungeon Master of the campaign 
     * @return Dungeon Master of the campaign 
     */
    public String getDungeonMaster() {
        return this.dungeonMaster;
    }

    /**
     * Sets the Dungeon Master of the campaign.
     * @param dungeonMaster Dungeon Master to be set for the campaign
     */
    public void setDungeonMaster(String dungeonMaster) {
        this.dungeonMaster = dungeonMaster;
    }

    /**
     * Returns the world of the campaign 
     * @return world of the campaign 
     */
    public String getWorld() {
        return this.world;
    }

    /**
     * Sets the world of the campaign.
     * @param world world to be set for the campaign
     */
    public void setWorld(String world) {
        this.world = world;
    }

    /**
     * Returns the characters of the campaign 
     * @return characters of the campaign 
     */
    public Set<DndCharacter> getCharacters() {
        return this.characters;
    }

    /**
     * Sets the characters of the campaign.
     * @param characters characters to be set for the campaign
     */
    public void setCharacters(Set<DndCharacter> characters) {
        this.characters = characters;
    }
    
    /**
     * Adds <code>character</code> to the characters of the campaign.
     * @param character character to be added to the campaign
     */
    public void addCharacter(DndCharacter character) {
        if (character == null) {
            return;
        }
        this.characters.add(character);
    }
    
    /**
     * Adds <code>characters</code> to the characters of the campaign.
     * @param characters characters to be added to the campaign
     */
    public void addCharacters(Collection<DndCharacter> characters) {
        if (characters == null) {
            return;
        }
        this.characters.addAll(characters);
    }

    /**
     * Removes <code>character</code> from the characters of the campaign.
     * @param character character to be removed from the campaign
     */
    public void removeCharacter(DndCharacter character) {
        if (character == null) {
            return;
        }
        this.characters.remove(character);
    }

    /**
     * Returns the backgrounds story of the campaign.
     * @return background story of the campaign 
     */
    public String getBackgroundStory() {
        return this.backgroundStory;
    }

    /**
     * Sets the background story of the campaign.
     * @param backgroundStory background story to be set for the campaign
     */
    public void setBackgroundStory(String backgroundStory) {
        this.backgroundStory = backgroundStory;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.world;
    }
}
