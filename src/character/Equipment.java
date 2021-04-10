package character;

import enums.Type;

/**
 * Represents an equipment from the game Dungeons and Dragons.
 */
public class Equipment {
    
    private Integer id;
    private Type type;
    private String name;
    private int quantity;

    public Equipment(){
        this(null, "", 0);
    }
    public Equipment(Type type, String name, int quantity) {
        this(null, type, name, quantity);
    }
    public Equipment(Integer id, Type type, String name, int quantity) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.quantity = quantity;
    }
    
    /**
     * Returns the id of the equipment.
     * @return id of the equipment 
     */
    public Integer getId() {
        return this.id;
    }
    
    /**
     * Sets the id of the equipment.
     * @param id id to be set for the equipment
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the type of the equipment.
     * @return type of the equipment 
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Sets the type of the equipment.
     * @param type type to be set for the equipment
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Returns the name of the equipment.
     * @return name of the equipment
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the equipment.
     * @param name name to be set for the equipment
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Returns the quantity of the equipment.
     * @return quantity of the equipment
     */
    public int getQuantity() {
        return this.quantity;
    }
    
    /**
     * Sets the quantity of the equipment.
     * @param quantity quantity to be set for the equipment
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return this.name + " - " + quantity + " db";
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Equipment)) {
            return false;
        }
        Equipment other = (Equipment) o;
        return this.id == other.id;
    }
}
