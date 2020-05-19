package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.model.deck.DeckHand;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;
import nl.rug.oop.cardgame.view.panel.MagicStonePanel;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CardClicker extends MouseInputAdapter {

    private MagicStoneGame magicStoneGame;
    private MagicStonePanel magicStonePanel;
    private MagicStoneFrame magicStoneFrame;
    private Card card = null;

    private boolean selected;
    private int x;
    private int y;

    public CardClicker(MagicStoneGame magicStoneGame, MagicStonePanel magicStonePanel, MagicStoneFrame magicStoneFrame) {
        this.magicStoneGame = magicStoneGame;
        this.magicStonePanel = magicStonePanel;
        this.magicStoneFrame = magicStoneFrame;
        magicStonePanel.addMouseListener(this);
        selected = false;
    }

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
                System.out.println("pos:" + pos);
                magicStoneGame.getBattlefield().setSelectedCard(getCard(magicStoneGame, pos));
                System.out.println("Card number " + card.getCardNumber());
                new DiscardClicker(magicStoneGame, magicStonePanel, card.getCardNumber(), magicStoneFrame);
                if (card.getCost() <= magicStoneGame.getBattlefield().getPlayer().getMana()) {
                    new BattlefieldClicker(magicStoneGame, magicStonePanel, card, magicStoneFrame, false);
                }
                card = null;
            }
        }
    }

    public void startAttackPhase() {
        new BattlefieldClicker(magicStoneGame, magicStonePanel, null, magicStoneFrame, true);
    }

    private Card getCard(MagicStoneGame magicStoneGame, int pos) {
        DeckHand playerHand = magicStoneGame.getBattlefield().getPlayer().getDeckHand();
        Card card = null;
        for(Card c : playerHand.getDeckHand().values()) {
            if(c.getHandPos() == pos) card = c;
        }
        return card;
    }

}
