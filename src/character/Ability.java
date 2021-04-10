package character;

/**
 * Represents an ability from the game Dungeons and Dragons.
 */
public class Ability {
    
    private Integer id;
    private String name;

    public Ability(){
        this("");
    }
    public Ability(String name) {
        this(null, name);
    }
    public Ability(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    
    /**
     * Returns the id of the ability.
     * @return id of the ability
     */
    public Integer getId() {
        return this.id;
    }
    
    /**
     * Sets the id of the ability.
     * @param id id to be set for the ability
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the ability.
     * @return name of the ability 
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the ability.
     * @param name name to be set for the ability
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ability)) {
            return false;
        }
        Ability other = (Ability) o;
        return this.id == other.id;
    }
}
