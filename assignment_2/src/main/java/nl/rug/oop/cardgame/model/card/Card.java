package nl.rug.oop.cardgame.model.card;

import lombok.Data;
import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.interfaces.Playable;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A card used in the game
 */
@Data
public abstract class Card implements Playable {

        protected String type;
        protected String name;
        protected int cost;
        protected int cardNumber;
        protected static final AtomicInteger atomicInteger = new AtomicInteger(0);
        EnumCard enumCard;

    /**
     * Creates a new Card
     * @param enumCard EnumCard
     */
    public Card(EnumCard enumCard) {
        this.type = enumCard.getType().toString();
        this.name = enumCard.getFace().toString();
        this.cost = enumCard.getCost();
        this.cardNumber = atomicInteger.incrementAndGet();
        this.enumCard = enumCard;
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
