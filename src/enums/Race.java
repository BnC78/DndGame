package enums;

/**
 * Represents an race from the game Dungeons and Dragons.
 */
public enum Race {
    
    DRAGONBORN(10),
    DWARF(5),
    ELF(0),
    GNOME(4),
    HALF_ELF(2),
    HALFLING(1),
    HALF_ORC(8),
    HUMAN(6),
    THIEFLING(2);
    
    private int hitPointsModifier;

    private Race(int hitPointsModifier) {
        this.hitPointsModifier = hitPointsModifier;
    }

    /**
     * Returns the hit points modifier of the race.
     * @return hit points modifier of the race
     */
    public int getHitPointsModifier() {
        return this.hitPointsModifier;
    }

    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase().replace("_", "-");
    }
}
