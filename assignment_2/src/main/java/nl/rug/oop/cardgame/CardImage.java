package nl.rug.oop.cardgame;

import lombok.Data;

import java.awt.*;

@Data
public class CardImage {

    private int[] coordinates;
    private Image image;

    public CardImage(Image image) {
        this.image = image;
        this.coordinates = new int[]{0,0,0,0};
    }
}
