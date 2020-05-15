package nl.rug.oop.cardgame.view.textures;

import nl.rug.oop.cardgame.model.card.EnumCard;

import java.awt.*;
import java.util.EnumMap;

import java.io.File;
import java.io.IOException;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * For each card in a 54 card deck, this class loads a texture. It does so
 * statically to prevent duplicate loading of large images. It also does it
 * eagerly (at startup) to prevent loading times from slowing the program down
 * at runtime.
 */
public class CardTextures {

    private static EnumMap<EnumCard, BufferedImage> textures;

    /**
     * This block initalizes the textures on launch. It is executed once when
     * the class is loaded into the JVM (when the program is started) meaning
     * that this process won't take time during execution.
     */
    static {
        textures = new EnumMap<>(EnumCard.class);
        for (EnumCard card : EnumCard.values()) {
            BufferedImage texture = null;
            String fileName = "target" + File.separator + "classes" + File.separator + "textures" + File.separator
                    + card + ".png";
            try {
                File imgFile = new File(fileName);
                texture = ImageIO.read(imgFile);
            } catch (IOException ioe) {
                System.err.println("Could not load " + fileName);
            }
            textures.put(card, texture);
        }
    }

    /**
     * Find a texture for a card.
     *
     * @param card The cart in question.
     */
    public static BufferedImage getTexture(EnumCard card) {
        return textures.get(card);
    }

}
