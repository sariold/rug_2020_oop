package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.model.card.CreatureCard;
import nl.rug.oop.cardgame.model.hero.Hero;
import nl.rug.oop.cardgame.view.MagicStoneFrame;
import nl.rug.oop.cardgame.view.MagicStonePanel;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class CopyClicker extends MouseInputAdapter {

    private MagicStoneGame magicStoneGame;
    private MagicStonePanel magicStonePanel;
    private int x;
    private int y;
    private Card card;
    private MagicStoneFrame frame;
    private ArrayList<Integer> freePos;
    private boolean selected;

    public CopyClicker(MagicStoneGame magicStoneGame, MagicStonePanel magicStonePanel, Card card,
                              MagicStoneFrame frame) {
        this.magicStoneGame = magicStoneGame;
        this.magicStonePanel = magicStonePanel;
        this.card = card;
        this.frame = frame;
        magicStonePanel.addMouseListener(this);
        this.freePos = magicStoneGame.getBattlefield().getFreePositions(magicStoneGame.getBattlefield().getPlayer());
        this.selected = false;
//        if(card instanceof CreatureCard && !attackPhase) magicStonePanel.paintPositions(frame.getGraphics(), Color.GREEN, false);
//        else if(attackPhase) magicStonePanel.paintPositions(frame.getGraphics(), Color.RED, true);
    }

//    private void placeCreature(int pos) {
//        for(int i = 0; i < freePos.size(); i++) {
//            if(freePos.get(i) == pos) {
//                magicStoneGame.getBattlefield().getPlayer().playCard(magicStoneGame.getBattlefield(), frame, pos, card);
//                freePos = magicStoneGame.getBattlefield().getFreePositions(magicStoneGame.getBattlefield().getPlayer());
//                return;
//            }
//        }
//    }

    private void playSpell() {
        magicStoneGame.getBattlefield().getPlayer().playCard(magicStoneGame.getBattlefield(), frame, 0, card);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        x = event.getX();
        y= event.getY();
        if(!selected) {
            if (x >= 140 && x <= 230 && y >= 360 && y <= 495) {
                System.out.println("CARD 1");
                selected = true;
            }
            else if (x >= 340 && x <= 430 && y >= 360 && y <= 495) {
                System.out.println("CARD 2");
                selected = true;
            }
            else if (x >= 540 && x <= 630 && y >= 360 && y <= 495) System.out.println("CARD 3");
            else if (x >= 740 && x <= 830 && y >= 360 && y <= 495) System.out.println("CARD 4");
            else if (x >= 940 && x <= 1030 && y >= 360 && y <= 495) System.out.println("CARD 5");
            else if(!selected) ((Component) event.getSource()).removeMouseListener(this);
        } else {
            if (x >= 140 && x <= 230 && y >= 360 && y <= 495) System.out.println("CARD 1");
            else if (x >= 340 && x <= 430 && y >= 360 && y <= 495) System.out.println("CARD 2");
            else if (x >= 540 && x <= 630 && y >= 360 && y <= 495) System.out.println("CARD 3");
            else if (x >= 740 && x <= 830 && y >= 360 && y <= 495) System.out.println("CARD 4");
            else if (x >= 940 && x <= 1030 && y >= 360 && y <= 495) System.out.println("CARD 5");
            ((Component) event.getSource()).removeMouseListener(this);
        }
    }


    private CreatureCard getCard(MagicStoneGame magicStoneGame, int pos) {
        ArrayList<CreatureCard> played = magicStoneGame.getBattlefield().getPlayer().getPlayedCreatures();
        CreatureCard card = null;
        for(CreatureCard c : played) {
            if(c != null) {
                if (c.getBattlePosition() == pos) card = c;
            }
        }
        return card;
    }


}

