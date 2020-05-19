package nl.rug.oop.cardgame.model.card;

public enum EnumCard {

    CREATURE_ZOMBIE(Type.CREATURE, Face.ZOMBIE, 1, 1, 1),
    CREATURE_WOLF(Type.CREATURE, Face.WOLF, 2, 2, 2),
    CREATURE_WEREWOLF(Type.CREATURE, Face.WEREWOLF, 3, 3, 3),
    CREATURE_DRAGON(Type.CREATURE, Face.DRAGON, 4, 4, 4),
    CREATURE_DEVIL(Type.CREATURE, Face.DEVIL, 5, 5, 5),
    CREATURE_GUARD(Type.CREATURE, Face.GUARD, 4, 2, 3),
    CREATURE_ARCHER(Type.CREATURE, Face.ARCHER, 1, 3, 2),
    CREATURE_GARGOYLE(Type.CREATURE, Face.GARGOYLE, 3, 1, 2),
    CREATURE_TORTOISE(Type.CREATURE, Face.TORTOISE, 5, 3, 4),

    SPELL_INSTANTDRAW(Type.SPELL, Face.INSTANTDRAW, 4, 2),
    SPELL_INSTANTHEALTH(Type.SPELL, Face.INSTANTHEALTH, 3, 3),
    SPELL_INSTANTDAMAGE(Type.SPELL, Face.INSTANTDAMAGE, 5, 3),
    SPELL_COPYPASTE(Type.SPELL, Face.COPYPASTE, 4, 1);

    private final Type type;
    private final Face face;
    private int health;
    private int attack;
    private final int cost;
    private int value;
    private int cardNumber;


    EnumCard(Type type, Face face, int health, int attack, int cost) {
        this.type = type;
        this.face = face;
        this.health = health;
        this.attack = attack;
        this.cost = cost;
        this.cardNumber = -1;
    }

    EnumCard(Type type, Face face, int cost, int value) {
        this.type = type;
        this.face = face;
        this.cost = cost;
        this.value = value;
        this.cardNumber = -1;
    }

    public int getCardNumber() { return this.cardNumber; }

    public void setCardNumber(int num) { this.cardNumber = num; }

    public int getHealth() {
        return this.health;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getCost() {
        return this.cost;
    }

    public int getValue() {
        return this.value;
    }

    public enum Type {
        CREATURE,
        SPELL
    }

    public enum Face {
        WOLF,
        WEREWOLF,
        ZOMBIE,
        DRAGON,
        DEVIL,
        GUARD,
        ARCHER,
        GARGOYLE,
        TORTOISE,
        INSTANTDRAW,
        INSTANTHEALTH,
        INSTANTDAMAGE,
        COPYPASTE
    }

    public Type getType() {
        return this.type;
    }

    public Face getFace() {
        return this.face;
    }

}
