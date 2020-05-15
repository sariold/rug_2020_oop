package nl.rug.oop.cardgame.view;

import lombok.Data;
import nl.rug.oop.cardgame.model.MagicStoneGame;

import javax.swing.*;
import java.awt.*;

@Data
public class MagicStoneFrame extends JFrame {

    private MagicStonePanel panel;

    public MagicStoneFrame(MagicStoneGame magicStoneGame) {
        super("Magic Stone");
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
