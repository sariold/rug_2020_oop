package nl.rug.oop.cardgame.view.panel;

import nl.rug.oop.cardgame.model.MagicStoneGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

public class MainMenuPanel extends JPanel implements Observer {

    public MainMenuPanel() {
        setBackground(Color.GRAY);
        setVisible(true);
        setOpaque(true);
        this.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g){
        paintLogo(g);
    }

    private void paintLogo(Graphics g) {
        Image logo = null;
        try {
            logo = ImageIO.read(this.getClass().getResource(File.separator + "textures" + File.separator +
                    "MAGICSTONE_LOGO.png"));
        } catch (IOException e){
            System.out.println("Could not read File");
        }
        try {
            Font magicStoneFont = Font.createFont(Font.TRUETYPE_FONT, new File(File.separator + "Dragonlands-KeZ7.ttf"));
//            magicStoneFont = magicStoneFont.deriveFont(Font.BOLD, 40);
//            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//            ge.registerFont(magicStoneFont);
//            g.setFont(magicStoneFont);
        } catch (IOException|FontFormatException e) {
            System.out.println("FONT NOT FOUND");
        }
        g.drawImage(logo,(getWidth()/2)-150, 100, 300, 300, this);
        g.drawString("MAGIC STONE", 300, 40);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
