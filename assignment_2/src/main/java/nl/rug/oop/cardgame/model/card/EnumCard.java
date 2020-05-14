package nl.rug.oop.cardgame.model.card;

public enum EnumCard {

    CREATURE_ZOMBIE     (Type.CREATURE, Face.ZOMBIE, 1, 1, 1),
    CREATURE_WOLF       (Type.CREATURE, Face.WOLF, 2, 2, 2),
    CREATURE_WEREWOLF   (Type.CREATURE, Face.WEREWOLF, 3, 3, 3),
    CREATURE_DRAGON     (Type.CREATURE, Face.DRAGON, 4, 4, 4),

    SPELL_INSTANTDRAW   (Type.SPELL, Face.INSTANTDRAW, 4),
    SPELL_INSTANTHEALTH (Type.SPELL, Face.INSTANTHEALTH, 3),
    SPELL_INSTANTDAMAGE (Type.SPELL, Face.INSTANTDAMAGE, 5),
    SPELL_COPYPASTE     (Type.SPELL, Face.COPYPASTE, 4);

    private final Type type;
    private final Face face;
    private int health;
    private int attack;
    private final int cost;

    EnumCard(Type type, Face face, int health, int attack, int cost) {
        this.type = type;
        this.face = face;
        this.health = health;
        this.attack = attack;
        this.cost = cost;
    }

    EnumCard(Type type, Face face, int cost) {
        this.type = type;
        this.face = face;
        this.cost = cost;
    }

    public int getHealth() {
        return this.health;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getCost() {
        return this.cost;
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
