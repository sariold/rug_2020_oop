package nl.rug.oop.cardgame.view.textures;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;

public class CombatTextures {
    private static EnumMap<CombatEnum, BufferedImage> textures;


    /**
     * This block initializes the textures on launch. It is executed once when
     * the class is loaded into the JVM (when the program is started) meaning
     * that this process won't take time during execution.
     */
    static {
        textures = new EnumMap<>(CombatEnum.class);
        for (CombatEnum back : CombatEnum.values()) {
            BufferedImage texture = null;
            String fileName = "target/classes/textures/" + back + ".png";
            try {
                File imgFile = new File(fileName);
                texture = ImageIO.read(imgFile);
            } catch (IOException ioe) {
                System.err.println("Could not load " + fileName);
            }
            textures.put(back, texture);
        }
    }

    /**
     * Find a texture for a card back.
     * @param back The cart in question.
     */
    public static BufferedImage getTexture(CombatEnum back) {
        return textures.get(back);
    }
}
