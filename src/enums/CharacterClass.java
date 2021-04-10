package enums;

import java.util.EnumMap;

/**
 * Represents the class from the game Dungeons and Dragons.
 */
public enum CharacterClass {
    
    BARBARIAN   (19, 14, 12, 10, 11, 11, 10),
    BARD        (11, 16, 15, 11, 11, 16, 0),
    CLERIC      (12, 12, 15, 11, 18, 16, 0),
    DRUID       (10, 12, 14, 18, 20, 11, 8),
    FIGHTER     (18, 15, 15, 13, 14, 10, 9),
    MONK        (16, 18, 11, 10, 15, 12, 5),
    PALADIN     (16, 11, 11, 10, 16, 20, 3),
    RANGER      (13, 20, 12, 10, 11, 10, 1),
    ROGUE       (10, 20, 14, 17, 12, 11, 3),
    SORCERER    (10, 11, 16, 12, 12, 20, 0),
    WARLOCK     (10, 12, 12, 12, 15, 19, 3),
    WIZARD      (10, 15, 11, 17, 16, 11, 0),
    ARTIFICER   (13, 12, 16, 17, 12, 10, 1);
    
    private EnumMap<Traits, Integer> traits;
    private int hitPointsModifier;

    private CharacterClass(int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma, int hitPointsModifier) {
        this.traits = new EnumMap<>(Traits.class);
        this.traits.put(Traits.STRENGTH, strength);
        this.traits.put(Traits.DEXTERITY, dexterity);
        this.traits.put(Traits.CONSTITUTION, constitution);
        this.traits.put(Traits.INTELLIGENCE, intelligence);
        this.traits.put(Traits.WISDOM, wisdom);
        this.traits.put(Traits.CHARISMA, charisma);
        this.hitPointsModifier = hitPointsModifier;
    }

    /**
     * Returns the traits of the class.
     * @return traits of the class
     */
    public EnumMap<Traits, Integer> getTraits() {
        return this.traits;
    }
    
    /**
     * Returns the <code>trait</code> trait of the class.
     * @param trait
     * @return <code>trait</code> trait of the class
     */
    public int getTrait(Traits trait) {
        return this.traits.get(trait);
    }

    /**
     * Returns the hit points modifier of the class.
     * @return hit points modifier of the class
     */
    public int getHitPointsModifier() {
        return this.hitPointsModifier;
    }

    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}
