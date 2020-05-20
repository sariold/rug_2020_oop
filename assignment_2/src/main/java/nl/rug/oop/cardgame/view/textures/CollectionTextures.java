package nl.rug.oop.cardgame.view.textures;

import nl.rug.oop.cardgame.model.card.EnumCard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;

public class CollectionTextures {

    private static final EnumMap<EnumCard, Image> textures;

    /**
     * This block initializes the textures on launch. It is executed once when
     * the class is loaded into the JVM (when the program is started) meaning
     * that this process won't take time during execution.
     */
    static {
        textures = new EnumMap<>(EnumCard.class);
        for (EnumCard card : EnumCard.values()) {
            Image texture = null;
            try {
                Image loaded = ImageIO.read(CardTextures.class.getResource(File.separator + "textures" + File.separator
                        + card + ".png"));
                Image renderedImage = loaded.getScaledInstance(300, 418, Image.SCALE_SMOOTH);
                texture = renderedImage;
                textures.put(card, texture);
            } catch (IOException ioe) {
                System.err.println("Could not load!");
            }
        }
    }

    /**
     * Find a texture for a card.
     *
     * @param card The cart in question.
     */
    public static Image getTexture(EnumCard card) {
        return textures.get(card);
    }
}
