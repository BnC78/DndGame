package character;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;

import enums.CharacterClass;
import enums.Race;
import enums.Traits;

/**
 * Represent a character from the game Dungeons and Dragons.
 */
public class DndCharacter {
    
    private static final int BASEHITPOINTS = 10;

    private Integer id;
    private String name;
    private String birthPlace;
    private Race race;
    private CharacterClass characterClass;
    private EnumMap<Traits, Integer> traits;
    private Set<Ability> abilities;
    private Set<Equipment> equipment;
    private int level;
    private int hitPoints;


    public DndCharacter(){
        this.id = null;
        this.name = "";
        this.birthPlace = "";
        this.race = null;
        this.characterClass = null;
        this.traits = new EnumMap<>(Traits.class);
        this.abilities = new HashSet<>();
        this.equipment = new HashSet<>();
        this.level = 0;
        this.hitPoints = 0;
    }
    
    public DndCharacter(String name, String birthPlace, Race race, CharacterClass characterClass, Integer level, Integer hitPoints) {
        this(null, name, birthPlace, race, characterClass, level, hitPoints);
    }
    public DndCharacter(Integer id, String name, String birthPlace, Race race, CharacterClass characterClass, Integer level, Integer hitPoints) {
        this.id = id;
        this.name = name;
        this.birthPlace = birthPlace;
        this.race = race;
        this.characterClass = characterClass;
        this.traits = characterClass.getTraits();
        this.abilities = new HashSet<>();
        this.equipment = new HashSet<>();
        this.level = (level == null) ? 1 : level;
        this.hitPoints = (hitPoints == null) ? calculateHitPoints(race, characterClass) : hitPoints;
    }

    /**
     * Returns the hit points of the character based on the <code>BASEHITPOINTS</code>, <code>Race</code> and <code>CharacterClass</code> of the character.
     * @param race race of the character
     * @param characterClass class of the character
     * @return hit points of the character
     */
    private int calculateHitPoints(Race race, CharacterClass characterClass) {
        return BASEHITPOINTS + race.getHitPointsModifier() + characterClass.getHitPointsModifier();
    }

    /**
     * Returns the id of the character.
     * @return id of the character
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Sets the id of the character.
     * @param id id to be set for the character
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the name of the character.
     * @return name of the character
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the character.
     * @param name name to be set for the character
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the birth place of the character.
     * @return birth place of the character
     */
    public String getBirthPlace() {
        return this.birthPlace;
    }

    /**
     * Sets the birth place of the character.
     * @param birthPlace birth place to be set for the character
     */
    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    /**
     * Returns the race of the character.
     * @return race of the character
     */
    public Race getRace() {
        return this.race;
    }

    /**
     * Sets the race of the character.
     * @param race race to be set for the character
     */
    public void setRace(Race race) {
        this.race = race;
    }

    /**
     * Returns the class of the character.
     * @return class of the character
     */
    public CharacterClass getCharacterClass() {
        return this.characterClass;
    }

    /**
     * Sets the class of the character.
     * @param characterClass class to be set for the character
     */
    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
        this.traits = characterClass.getTraits();
    }

    /**
     * Returns the traits of the character.
     * @return traits of the character
     */
    public EnumMap<Traits, Integer> getTraits() {
        return this.traits;
    }

    /**
     * Sets the traits of the character.
     * @param traits traits to be set for the character
     */
    public void setTraits(EnumMap<Traits, Integer> traits) {
        this.traits = traits;
    }
    
    /**
     * Returns the <code>trait</code> trait of the character.
     * @param trait trait to be returned
     * @return <code>trait</code> trait of the character
     */
    public int getTrait(Traits trait) {
        return this.traits.get(trait);
    }

    /**
     * Sets the <code>trait</code> trait of the character to <code>value</code>.
     * @param trait trait to be modified
     * @param value value to be set to <code>trait</code>
     */
    public void setTrait(Traits trait, int value) {
        this.traits.put(trait, value);
    }

    /**
     * Returns the abilities of the character.
     * @return abilities of the character
     */
    public Set<Ability> getAbilities() {
        return this.abilities;
    }

    /**
     * Sets the abilities of the character.
     * @param abilities abilities to be set for the character
     */
    public void setAbilities(Set<Ability> abilities) {
        this.abilities = abilities;
    }

    /**
     * Adds <code>ability</code> ability to the abilities of the character.
     * @param ability ability to be added to the character's abilities
     */
    public void addAbility(Ability ability) {
        this.abilities.add(ability);
    }

    /**
     * Removes the <code>ability</code> from the abilities of the character.
     * @param ability ability to be removed from the character's abilities
     */
    public void removeAbility(Ability ability) {
        this.abilities.remove(ability);
    }

    /**
     * Return the equipment of the character.
     * @return equipment of the character
     */
    public Set<Equipment> getEquipment() {
        return this.equipment;
    }

    /**
     * Sets the equipment of the character.
     * @param equipment equipment to be set for the character
     */
    public void setEquipment(Set<Equipment> equipment) {
        this.equipment = equipment;
    }

    /**
     * Adds <code>equipment</code> to the equipment of the character.
     * @param equipment equipment to be added to the character's equipment
     */
    public void addEquipment(Equipment equipment) {
        this.equipment.add(equipment);
    }

    /**
     * Removes <code>equipment</code> from the equipment of the character.
     * @param equipment equipment to be removed from the character's equipment
     */
    public void removeEquipment(Equipment equipment) {
        this.equipment.remove(equipment);
    }

    /**
     * Returns the level of the character.
     * @return level of the character
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Sets the level of the character.
     * @param level level  to be set for the character
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Returns the hit points of the character.
     * @return hit points of the character.
     */
    public int getHitPoints() {
        return this.hitPoints;
    }

    /**
     * Sets the hit point of the character.
     * @param hitPoints hit points to be set for the character
     */
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Override
    public String toString() {
        return this.name;
    }
    
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof DndCharacter)) {
            return false;
        }
        DndCharacter other = (DndCharacter) o;
        return this.id == other.id;
    }
}
