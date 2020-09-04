package nl.rug.oop.cardgame.view.textures;

import lombok.Data;

import java.awt.*;

/**
 * Card image class
 */
@Data
public class CardImage {

    private int[] coordinates;
    private Image image;

    /**
     * Creates a card image, and sets default coordinates
     * @param image image of card
     */
    public CardImage(Image image) {
        this.image = image;
        this.coordinates = new int[]{0,0,0,0};
    }
}
