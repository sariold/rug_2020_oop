package nl.rug.oop.cardgame.controller.clicker;

import nl.rug.oop.cardgame.model.MagicStoneGame;

import nl.rug.oop.cardgame.model.hero.Hero;
import nl.rug.oop.cardgame.view.panel.MagicStonePanel;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Clicker that lets the player discard a Card
 */
public class DiscardClicker extends MouseInputAdapter {

    private final MagicStoneGame magicStoneGame;
    private final MagicStonePanel magicStonePanel;
    private int x;
    private int y;
    private final int key;

    /**
     * Create a new Discard Clicker
     * @param magicStoneGame Game
     * @param magicStonePanel Panel
     * @param key Key
     */
    public DiscardClicker(MagicStoneGame magicStoneGame, MagicStonePanel magicStonePanel, int key) {
        this.magicStoneGame = magicStoneGame;
        this.magicStonePanel = magicStonePanel;
        this.key = key;
        magicStonePanel.addMouseListener(this);
    }

    /**
     * Discard the specified card
     * @param event Mouse Click
     */
    @Override
    public void mouseClicked(MouseEvent event) {
        x = event.getX();
        y= event.getY();
        if(x >= 10 && x <= 110 && y >= 360 && y <= 510) {
            Hero player = magicStoneGame.getBattlefield().getPlayer();
            player.getDeckHand().discardCard(player.getDiscardDeck(), key);
        }
        ((Component) event.getSource()).removeMouseListener(this);
    }

}

