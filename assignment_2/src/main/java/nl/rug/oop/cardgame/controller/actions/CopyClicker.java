package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;
import nl.rug.oop.cardgame.view.panel.MagicStonePanel;

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
    private ArrayList<Integer> freePos;

    public CopyClicker(MagicStoneGame magicStoneGame, MagicStonePanel magicStonePanel, Card card) {
        this.magicStoneGame = magicStoneGame;
        this.magicStonePanel = magicStonePanel;
        this.card = card;
        this.magicStonePanel.addMouseListener(this);
        this.freePos = magicStoneGame.getBattlefield().getFreePositions(magicStoneGame.getBattlefield().getPlayer());
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        x = event.getX();
        y= event.getY();
        int pos = -1;
        if (x >= 140 && x <= 230 && y >= 360 && y <= 495) pos = 0;
        else if (x >= 340 && x <= 430 && y >= 360 && y <= 495) pos = 1;
        else if (x >= 540 && x <= 630 && y >= 360 && y <= 495) pos = 2;
        else if (x >= 740 && x <= 830 && y >= 360 && y <= 495) pos = 3;
        else if (x >= 940 && x <= 1030 && y >= 360 && y <= 495) pos = 4;
        if(pos != -1) {
            magicStoneGame.getBattlefield().getPlayer().playCard(magicStoneGame.getBattlefield(), pos, card);
        }
        ((Component) event.getSource()).removeMouseListener(this);
    }

}

