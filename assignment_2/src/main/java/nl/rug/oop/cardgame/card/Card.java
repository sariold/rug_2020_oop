package nl.rug.oop.cardgame.card;

import nl.rug.oop.cardgame.interfaces.Playable;

import java.awt.*;

/**
 * A card used in the game
 */
public abstract class Card implements Playable {

        protected String name;
        protected int cost;
        protected Image image;

    /**
     * Creates a new Card
     * @param name
     * @param cost
     * @param image
     */
    public Card(String name, int cost, Image image) {
        this.name = name;
        this.cost = cost;
        this.image = image;
    }

    @Override
    public void play() {
        System.out.println("You played " + this.name);
    }

    public String getName() {
        return name;
    }
}
