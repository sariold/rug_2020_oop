package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.MagicStoneGame;

import nl.rug.oop.cardgame.model.hero.Hero;
import nl.rug.oop.cardgame.view.MagicStoneFrame;
import nl.rug.oop.cardgame.view.MagicStonePanel;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;


public class DiscardClicker extends MouseInputAdapter {

    private MagicStoneGame magicStoneGame;
    private MagicStonePanel magicStonePanel;
    private int x;
    private int y;
    private int key;
    private MagicStoneFrame frame;

    public DiscardClicker(MagicStoneGame magicStoneGame, MagicStonePanel magicStonePanel, int key, MagicStoneFrame frame) {
        this.magicStoneGame = magicStoneGame;
        this.magicStonePanel = magicStonePanel;
        this.key = key;
        this.frame = frame;
        magicStonePanel.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        x = event.getX();
        y= event.getY();
        if(x >= 10 && x <= 110 && y >= 360 && y <= 510) {
//            magicStoneGame.getBattlefield().setSelectedCard();
//            magicStonePanel.paintSelected(magicStonePanel.getGraphics(), 6, Color.RED);
            Hero player = magicStoneGame.getBattlefield().getPlayer();
            player.getDeckHand().discardCard(player.getDiscardDeck(), key);
//            frame.update(frame.getGraphics());
        }
        ((Component) event.getSource()).removeMouseListener(this);
    }

}

