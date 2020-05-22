package nl.rug.oop.cardgame.model.card;

/**
 * Enumeration used to create cards
 */
public enum EnumCard {

    /**
     * All creatures
     */
    CREATURE_ZOMBIE(Type.CREATURE, Face.ZOMBIE, 1, 1, 1),
    CREATURE_WOLF(Type.CREATURE, Face.WOLF, 2, 2, 2),
    CREATURE_WEREWOLF(Type.CREATURE, Face.WEREWOLF, 3, 3, 3),
    CREATURE_DRAGON(Type.CREATURE, Face.DRAGON, 4, 4, 4),
    CREATURE_DEMON(Type.CREATURE, Face.DEMON, 5, 5, 5),
    CREATURE_GUARD(Type.CREATURE, Face.GUARD, 4, 2, 3),
    CREATURE_ARCHER(Type.CREATURE, Face.ARCHER, 1, 3, 2),
    CREATURE_GARGOYLE(Type.CREATURE, Face.GARGOYLE, 3, 1, 2),
    CREATURE_TORTOISE(Type.CREATURE, Face.TORTOISE, 5, 3, 4),

    /**
     * All spells
     */
    SPELL_INSTANTDRAW(Type.SPELL, Face.INSTANTDRAW, 4, 2),
    SPELL_INSTANTHEALTH(Type.SPELL, Face.INSTANTHEALTH, 3, 3),
    SPELL_INSTANTDAMAGE(Type.SPELL, Face.INSTANTDAMAGE, 5, 3),
    SPELL_COPYPASTE(Type.SPELL, Face.COPYPASTE, 4, 1),
    SPELL_HELLFIRE(Type.SPELL, Face.HELLFIRE, 4, 2),
    SPELL_DAMAGEBUFF(Type.SPELL, Face.DAMAGEBUFF, 3, 2);

    private final Type type;
    private final Face face;
    private int health;
    private int attack;
    private final int cost;
    private int value;
    private int cardNumber;

    /**
     * Create new Enum Card for Creatures
     * @param type Type
     * @param face Face
     * @param health Health
     * @param attack Attack
     * @param cost Cost
     */
    EnumCard(Type type, Face face, int health, int attack, int cost) {
        this.type = type;
        this.face = face;
        this.health = health;
        this.attack = attack;
        this.cost = cost;
        this.cardNumber = -1;
    }

    /**
     * Create new Enum Card for Spells
     * @param type Type
     * @param face Face
     * @param cost Cost
     * @param value Value
     */
    EnumCard(Type type, Face face, int cost, int value) {
        this.type = type;
        this.face = face;
        this.cost = cost;
        this.value = value;
        this.cardNumber = -1;
    }

    /**
     * sets the card number
     * @param num Number
     */
    public void setCardNumber(int num) { this.cardNumber = num; }

    /**
     * Return health
     * @return Health
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Return attack
     * @return Attack
     */
    public int getAttack() {
        return this.attack;
    }

    /**
     * Return cost
     * @return Cost
     */
    public int getCost() {
        return this.cost;
    }

    /**
     * Return Value
     * @return value
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Type is either creature or spell
     */
    public enum Type {
        CREATURE,
        SPELL
    }

    /**
     * Face used to determine the Image for the card
     */
    public enum Face {
        WOLF,
        WEREWOLF,
        ZOMBIE,
        DRAGON,
        DEMON,
        GUARD,
        ARCHER,
        GARGOYLE,
        TORTOISE,
        INSTANTDRAW,
        INSTANTHEALTH,
        INSTANTDAMAGE,
        COPYPASTE,
        HELLFIRE,
        DAMAGEBUFF
    }

    /**
     * Return type
     * @return Type
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Return Face
     * @return Face
     */
    public Face getFace() {
        return this.face;
    }

}
