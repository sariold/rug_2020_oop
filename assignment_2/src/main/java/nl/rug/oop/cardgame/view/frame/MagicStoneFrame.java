package nl.rug.oop.cardgame.view.frame;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.rug.oop.cardgame.controller.actions.CardClicker;
import nl.rug.oop.cardgame.controller.button.AttackPhaseButton;
import nl.rug.oop.cardgame.controller.button.EndTurnButton;
import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.view.panel.GameOverPanel;
import nl.rug.oop.cardgame.view.panel.MagicStonePanel;
import nl.rug.oop.cardgame.view.panel.MainMenuPanel;
import nl.rug.oop.cardgame.view.textures.CardTextures;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@EqualsAndHashCode(callSuper = true)
@Data
public class MagicStoneFrame extends JFrame {

    private MagicStonePanel gamePanel;
    private MainMenuPanel mainMenuPanel;
    private CardClicker clicker;
    private JLabel gifLabel;
    private Image loadedLogo;

    public MagicStoneFrame(MagicStoneGame magicStoneGame) {
        super("Magic Stone");
        try {
            this.loadedLogo = ImageIO.read(CardTextures.class.getResource(File.separator + "textures"
                    + File.separator + "MAGICSTONE_LOGO.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setIconImage(loadedLogo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel = new MagicStonePanel(magicStoneGame);
        gamePanel.setLayout(null);

        mainMenuPanel = new MainMenuPanel(magicStoneGame, this);
        mainMenuPanel.setLayout(null);

        add(mainMenuPanel);
        setPreferredSize(new Dimension(1280, 720));
        gifLabel = new JLabel();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void playGif(String gifName, int[] coords) {
        ImageIcon gif = new ImageIcon(this.getClass().getResource(File.separator + "textures"
                + File.separator + gifName + ".gif"));
        gif.setImage(gif.getImage().getScaledInstance(coords[2],coords[2], Image.SCALE_DEFAULT));
        gifLabel.setIcon(gif);
        gifLabel.setBounds(coords[0], coords[1], coords[2], coords[2]);
        gamePanel.add(gifLabel);
        pack();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gifLabel.setIcon(null);
        return;
    }

    /**
     * changes the current panel to the game
     */
    public void changeToGamePanel() {
        System.out.println("CHANGING");
        remove(mainMenuPanel);
        add(gamePanel);
        setPreferredSize(new Dimension(1280, 720));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void gameOver(boolean win) {
        remove(gamePanel);
        GameOverPanel overPanel = new GameOverPanel(win);
        add(overPanel);
        setPreferredSize(new Dimension(300, 200));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}
