package nl.rug.oop.cardgame.controller.clicker;

import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.model.deck.DeckHand;
import nl.rug.oop.cardgame.view.panel.MagicStonePanel;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Clicker that lets the player choose a creature from the battlefield
 */
public class CardClicker extends MouseInputAdapter {

    private final MagicStoneGame magicStoneGame;
    private final MagicStonePanel magicStonePanel;
    private Card card = null;

    private boolean selected;
    private int x;
    private int y;

    /**
     * Creates a new mouse input to choose a creature from the battlefield
     * @param magicStoneGame Game
     * @param magicStonePanel Panel
     */
    public CardClicker(MagicStoneGame magicStoneGame, MagicStonePanel magicStonePanel) {
        this.magicStoneGame = magicStoneGame;
        this.magicStonePanel = magicStonePanel;
        magicStonePanel.addMouseListener(this);
        selected = false;
    }

    /**
     * Checks if the selected position contains a card
     * @param i Position to check for a creature
     * @return selected card
     */
    private int selectCheck(int i) {
        int pos = -1;
        if(selected) {
            card = null;
            selected = false;
        } else {
            selected = true;
            card = getCard(magicStoneGame, i);
            if(card != null) pos = i;
        }
        return pos;
    }

    /**
     * Lets the player choose a position to place a creature
     * @param event Mouse Clicked
     */
    @Override
    public void mouseClicked(MouseEvent event) {
        ArrayList<Integer> freePositions = magicStoneGame.getBattlefield().getFreePositions(magicStoneGame.getBattlefield().getPlayer());
        if(!magicStoneGame.getBattlefield().isAttackPhase()) {
            int pos = -1;
            x = event.getX();
            y = event.getY();
            if (x >= 100 && x <= 200 && y >= 530 && y <= 680) pos = selectCheck(0);
            else if (x >= 250 && x <= 350 && y >= 530 && y <= 680) pos = selectCheck(1);
            else if (x >= 400 && x <= 500 && y >= 530 && y <= 680) pos = selectCheck(2);
            else if (x >= 780 && x <= 880 && y >= 530 && y <= 680) pos = selectCheck(3);
            else if (x >= 930 && x <= 1030 && y >= 530 && y <= 680) pos = selectCheck(4);
            else if (x >= 1080 && x <= 1180 && y >= 530 && y <= 680) pos = selectCheck(5);
            if (card == null) magicStoneGame.getBattlefield().setSelectedCard(null);
            if (card != null) pos = card.getHandPos();
            if (pos != -1) {
                magicStoneGame.getBattlefield().setSelectedCard(getCard(magicStoneGame, pos));
                new DiscardClicker(magicStoneGame, magicStonePanel, card.getCardNumber());
                if (card.getCost() <= magicStoneGame.getBattlefield().getPlayer().getMana()) {
                    new BattlefieldClicker(magicStoneGame, magicStonePanel, card, false);
                }
                card = null;
            }
        }
    }

    /**
     * Initiates the mouse listener for an attack phase
     */
    public void startAttackPhase() {
        new BattlefieldClicker(magicStoneGame, magicStonePanel, null, true);
    }

    /**
     * Returns the card in the players hand at the spcified position
     * @param magicStoneGame Game
     * @param pos Position
     * @return Card
     */
    private Card getCard(MagicStoneGame magicStoneGame, int pos) {
        DeckHand playerHand = magicStoneGame.getBattlefield().getPlayer().getDeckHand();
        Card card = null;
        for(Card c : playerHand.getDeckHand().values()) {
            if(c.getHandPos() == pos) card = c;
        }
        return card;
    }

}
