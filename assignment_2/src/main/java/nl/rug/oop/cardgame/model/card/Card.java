package nl.rug.oop.cardgame.model.card;

import lombok.Data;
import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.interfaces.Playable;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A card used in the game
 */
@Data
public abstract class Card implements Playable {

        protected String name;
        protected int cost;
        protected Image image;
        protected int cardNumber;
        protected static final AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * Creates a new Card
     * @param name Name
     * @param cost Cost
     * @param image Image
     */
    public Card(String name, int cost, Image image) {
        this.name = name;
        this.cost = cost;
        this.image = image;
        this.cardNumber = atomicInteger.incrementAndGet();
    }

    /**
     * Play method
     * @return
     */
    @Override
    public boolean play(Battlefield battlefield, int hero) {
        System.out.println("You played " + this.name);
        return true;
    }
}
