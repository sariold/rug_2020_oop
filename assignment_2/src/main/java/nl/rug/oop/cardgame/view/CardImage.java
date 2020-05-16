package nl.rug.oop.cardgame.view;

import lombok.Data;
import nl.rug.oop.cardgame.model.card.Card;

import java.awt.image.BufferedImage;

@Data
public class CardImage {

    private BufferedImage image;
    private int[] coordinates;

    public CardImage(BufferedImage image, int[] coordinates) {
        this.image = image;
        this.coordinates = coordinates;
    }
}
