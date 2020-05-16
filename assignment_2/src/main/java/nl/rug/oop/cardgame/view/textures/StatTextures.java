package nl.rug.oop.cardgame.view.textures;

import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;

@Data
public class StatTextures {

    private static EnumMap<StatEnum, BufferedImage> textures;


    /**
     * This block initializes the textures on launch. It is executed once when
     * the class is loaded into the JVM (when the program is started) meaning
     * that this process won't take time during execution.
     */
    static {
        textures = new EnumMap<>(StatEnum.class);
        for (StatEnum stat : StatEnum.values()) {
            BufferedImage texture = null;
            String fileName = "target/classes/textures/" + stat + ".png";
            try {
                File imgFile = new File(fileName);
                texture = ImageIO.read(imgFile);
            } catch (IOException ioe) {
                System.err.println("Could not load " + fileName);
            }
            textures.put(stat, texture);
        }
    }

    /**
     * Find a texture for a stat.
     *
     * @param stat The stat in question.
     */
    public static BufferedImage getTexture(StatEnum stat) {
        return textures.get(stat);
    }

}
