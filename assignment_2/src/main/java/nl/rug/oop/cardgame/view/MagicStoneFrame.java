package nl.rug.oop.cardgame.view;

import nl.rug.oop.cardgame.model.MagicStoneGame;

import javax.swing.*;
import java.awt.*;

public class MagicStoneFrame extends JFrame {

    MagicStonePanel panel;
    MagicStoneGame magicStoneGame;

    public MagicStoneFrame(MagicStoneGame magicStoneGame) {
        super("Magic Stone");
        this.magicStoneGame = magicStoneGame;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setJMenuBar(new ButtonBar(magicStoneGame));
        this.panel = new MagicStonePanel(magicStoneGame);
//        new CardDragger(magicStoneGame, panel);
        add(panel);
        setPreferredSize(new Dimension(1280, 720));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}
