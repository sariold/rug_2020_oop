package nl.rug.oop.cardgame.view.panel;

import nl.rug.oop.cardgame.controller.button.CardCollectionButton;
import nl.rug.oop.cardgame.controller.button.StartGameButton;
import nl.rug.oop.cardgame.controller.button.TutorialButton;
import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.model.menu.MainMenu;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

public class MainMenuPanel extends JPanel implements Observer {

    MagicStoneFrame frame;
    MainMenu mainMenu;

    public MainMenuPanel(MainMenu mainMenu, MagicStoneFrame frame) {
        setBackground(Color.GRAY);
        this.frame = frame;
        this.mainMenu = mainMenu;

        StartGameButton startGameButton = new StartGameButton(mainMenu);
        startGameButton.setBounds(540, 400, 200, 80);

        TutorialButton tutorialButton = new TutorialButton(mainMenu);
        tutorialButton.setBounds(540, 480, 200, 80);

        CardCollectionButton cardCollectionButton = new CardCollectionButton(mainMenu);
        cardCollectionButton.setBounds(540, 560, 200, 80);

        this.add(startGameButton);
        this.add(tutorialButton);
        this.add(cardCollectionButton);
        mainMenu.addObserver(this);

        setVisible(true);
        setOpaque(true);
        this.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (mainMenu.isInGame()) {
            frame.changeToGamePanel();
//            mainMenu.deleteObserver(this);
        }
        if (mainMenu.isInCollection()) {
            frame.changeToCardCollectionPanel();
        }
        if (mainMenu.isInTutorial()) {
            frame.changeToTutorialPanel();
        }
        paintLogo(g);
    }

    private void paintLogo(Graphics g) {
        Font logoFont;
        Image logo = null;
        try {
            logo = ImageIO.read(this.getClass().getResource(File.separator + "textures" + File.separator +
                    "MAGICSTONE_LOGO.png"));
        } catch (IOException e){
            System.out.println("Could not read File");
        }
        try {
            InputStream fontStream = this.getClass().getResourceAsStream(File.separator + "fonts" + File.separator + "Dragonlands.ttf");
            logoFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            logoFont = logoFont.deriveFont(Font.BOLD, 80);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(logoFont);
            g.setFont(logoFont);
        } catch (IOException|FontFormatException e) {
            System.out.println("FONT NOT FOUND");
        }
        g.drawImage(logo,(getWidth()/2)-150, 100, 300, 300, this);
        g.drawString("MAGIC STONE", 300, 80);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
