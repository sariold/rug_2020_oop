package nl.rug.oop.rpg.npcs;

import java.io.Serializable;

public class Boss extends Enemy implements Serializable {

    private static final long serialVersionUID = 16L;

    public Boss(String description, String name, int hitPoints, int attackPoints, int goldValue){
        super(description, name, hitPoints, attackPoints, goldValue);
    }
}
