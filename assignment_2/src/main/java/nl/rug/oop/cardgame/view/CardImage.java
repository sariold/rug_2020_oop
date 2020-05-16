package nl.rug.oop.cardgame.view;

import lombok.Data;
import nl.rug.oop.cardgame.model.card.Card;

import java.awt.*;

@Data
public class CardImage {

    private Image image;
    private int[] coordinates;

    public CardImage(Image image, int[] coordinates) {
        this.image = image;
        this.coordinates = coordinates;
    }
}
