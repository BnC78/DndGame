package enums;

/**
 * Represents the type of the <code>Equipment</code>. 
 */
public enum Type {
    
    WEAPON("Fegyver"), ARMOR("Páncél"), SHIELD("Pajzs"), MONEY("Pénz"), POTION("Varázsital"), FOOD("Étel"), BOOK("Könyv"), OTHER("Egyéb");

    private String hungarianName;

    private Type(String hungarianName) {
        this.hungarianName = hungarianName;
    }

    @Override
    public String toString() {
        return hungarianName;
    }
}
