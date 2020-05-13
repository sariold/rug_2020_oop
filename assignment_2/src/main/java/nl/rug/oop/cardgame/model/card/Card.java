package nl.rug.oop.cardgame.model.card;

import lombok.Data;
<<<<<<< HEAD:assignment_2/src/main/java/nl/rug/oop/cardgame/model/card/Card.java
import nl.rug.oop.cardgame.model.Battlefield;
=======
import nl.rug.oop.cardgame.battlefield.Battlefield;
>>>>>>> c530b26370df613ec733227bc978eb220c1d20cb:assignment_2/src/main/java/nl/rug/oop/cardgame/card/Card.java
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
     */
    @Override
    public void play(Battlefield battlefield, int hero) {
        System.out.println("You played " + this.name);
    }
}
