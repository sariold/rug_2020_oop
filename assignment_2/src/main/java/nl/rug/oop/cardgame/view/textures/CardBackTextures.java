package nl.rug.oop.cardgame.view.textures;

import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;

@Data
public class CardBackTextures {

    private static EnumMap<CardBack, Image> textures;


    /**
     * This block initializes the textures on launch. It is executed once when
     * the class is loaded into the JVM (when the program is started) meaning
     * that this process won't take time during execution.
     */
    static {
        textures = new EnumMap<>(CardBack.class);
        for (CardBack back : CardBack.values()) {
            Image texture = null;
            try {
                Image loaded = ImageIO.read(CardTextures.class.getResource(File.separator + "textures" + File.separator
                        + back + ".png"));
                Image renderedImage = loaded.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
                texture = renderedImage;
            } catch (IOException ioe) {
                System.err.println("Could not load!");
            }
            textures.put(back, texture);
        }
    }

    /**
     * Find a texture for a card back.
     *
     * @param back The cart in question.
     */
    public static Image getTexture(CardBack back) {
        return textures.get(back);
    }

}