package nl.rug.oop.cardgame.model.card;

import lombok.Data;
import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.interfaces.Playable;
import nl.rug.oop.cardgame.util.Displayable;
import nl.rug.oop.cardgame.view.CardImage;
import nl.rug.oop.cardgame.view.MagicStonePanel;
import nl.rug.oop.cardgame.view.textures.CardTextures;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A card used in the game
 */
@Data
public abstract class Card implements Playable, Displayable {

        protected String type;
        protected String name;
        protected int cost;
        protected int cardNumber;
        protected static final AtomicInteger atomicInteger = new AtomicInteger(0);
        protected CardImage cardImage;
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
        this.cardImage = new CardImage(CardTextures.getTexture(this.enumCard), null);
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

    /**
     * Display a card in a Panel
     * @param g Graphics
     * @param panel Game Panel
     */
    @Override
    public void display(Graphics g, MagicStonePanel panel) {
        g.drawImage(this.cardImage.getImage(), this.cardImage.getCoordinates()[0], this.cardImage.getCoordinates()[1],
                this.cardImage.getCoordinates()[2],this.cardImage.getCoordinates()[3], panel);
    }
}
