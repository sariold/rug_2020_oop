package nl.rug.oop.cardgame.view.textures;

import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;

@Data
public class StatTextures {

    private static EnumMap<StatEnum, Image> textures;


    /**
     * This block initializes the textures on launch. It is executed once when
     * the class is loaded into the JVM (when the program is started) meaning
     * that this process won't take time during execution.
     */
    static {
        textures = new EnumMap<>(StatEnum.class);
        for (StatEnum stat : StatEnum.values()) {
            Image texture = null;
            try {
                Image loaded = ImageIO.read(CardTextures.class.getResource(File.separator + "textures" + File.separator
                        + stat + ".png"));
                Image renderedImage = loaded.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
                texture = renderedImage;
            } catch (IOException ioe) {
                System.err.println("Could not load!");
            }
            textures.put(stat, texture);
        }
    }

    /**
     * Find a texture for a stat.
     *
     * @param stat The stat in question.
     */
    public static Image getTexture(StatEnum stat) {
        return textures.get(stat);
    }

}
