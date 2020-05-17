package nl.rug.oop.cardgame.view;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.rug.oop.cardgame.controller.actions.CardClicker;
import nl.rug.oop.cardgame.controller.button.EndTurnButton;
import nl.rug.oop.cardgame.model.MagicStoneGame;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.concurrent.TimeUnit;

@EqualsAndHashCode(callSuper = true)
@Data
public class MagicStoneFrame extends JFrame {

    private MagicStonePanel panel;
    private CardClicker clicker;
    private JLabel gifLabel;
    private MagicStoneGame magicStoneGame;


    public MagicStoneFrame(MagicStoneGame magicStoneGame, EndTurnButton endTurnButton) {
        super("Magic Stone");
        this.magicStoneGame = magicStoneGame;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new MagicStonePanel(magicStoneGame);
        panel.setLayout(null);
        endTurnButton.setBounds(590, 510, 100, 30);
        panel.add(endTurnButton);
//        setJMenuBar(new ButtonBar(magicStoneGame));
//        new CardDragger(magicStoneGame, panel);
        add(panel);
        clicker = new CardClicker(magicStoneGame, panel, this);
        System.out.println(this);
        setPreferredSize(new Dimension(1280, 720));
        gifLabel = new JLabel();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void playGif(String gifName) {
        System.out.println(this);
        Icon gif = new ImageIcon(this.getClass().getResource(File.separator + "textures"
                + File.separator + gifName + ".gif"));
        gifLabel.setIcon(gif);
        gifLabel.setBounds(500, 100, 339, 399);
        panel.add(gifLabel);
//        this.add(gifLabel);
//        this.add(panel);
        pack();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gifLabel.setIcon(null);
    }

    public void gameOver(boolean win) {
        remove(panel);
        GameOverPanel overPanel = new GameOverPanel(win);
        add(overPanel);
        setPreferredSize(new Dimension(300, 200));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}
