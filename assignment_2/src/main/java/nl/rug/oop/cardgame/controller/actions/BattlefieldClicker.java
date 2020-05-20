package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.model.card.CreatureCard;
import nl.rug.oop.cardgame.model.hero.Hero;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;
import nl.rug.oop.cardgame.view.panel.MagicStonePanel;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class BattlefieldClicker extends MouseInputAdapter {

    private MagicStoneGame magicStoneGame;
    private MagicStonePanel magicStonePanel;
    private int x;
    private int y;
    private Card card;
    private MagicStoneFrame frame;
    private ArrayList<Integer> freePos;
    private boolean attackPhase;
    private MouseEvent event;

    public BattlefieldClicker(MagicStoneGame magicStoneGame, MagicStonePanel magicStonePanel, Card card,
                              MagicStoneFrame frame, boolean attackPhase) {
        this.magicStoneGame = magicStoneGame;
        this.magicStonePanel = magicStonePanel;
        this.card = card;
        this.frame = frame;
        magicStonePanel.addMouseListener(this);
        this.freePos = magicStoneGame.getBattlefield().getFreePositions(magicStoneGame.getBattlefield().getPlayer());
        this.attackPhase = attackPhase;
        if(card instanceof CreatureCard && !attackPhase) magicStoneGame.getBattlefield().setPlayPhase(true);
        else if(attackPhase) magicStoneGame.getBattlefield().setAttackPhase(true);
    }

    private void placeCreature(int pos) {
        for(int i = 0; i < freePos.size(); i++) {
            if(freePos.get(i) == pos) {
                magicStoneGame.getBattlefield().getPlayer().playCard(magicStoneGame.getBattlefield(), frame, pos, card);
                freePos = magicStoneGame.getBattlefield().getFreePositions(magicStoneGame.getBattlefield().getPlayer());
                return;
            }
        }
    }

    private void playSpell() {
        magicStoneGame.getBattlefield().getPlayer().playCard(magicStoneGame.getBattlefield(), frame, 0, card);
        magicStoneGame.endGameCheck(magicStoneGame.getBattlefield());
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        this.event = event;
        x = event.getX();
        y = event.getY();
        if(!attackPhase) {
            switch (card.getEnumCard()) {
                case SPELL_DAMAGEBUFF:
                case SPELL_INSTANTDRAW:
                case SPELL_INSTANTHEALTH:
                    if (x >= 540 && x <= 740 && y >= 590 && y <= 790) playSpell();
                    break;
                case SPELL_HELLFIRE:
                case SPELL_INSTANTDAMAGE:
                    if (x >= 540 && x <= 740 && y >= 0 && y <= 200) playSpell();
                    break;
                case SPELL_COPYPASTE:
                    if(card.getEnumCard().getCost() <= magicStoneGame.getBattlefield().getPlayer().getMana() && freePos.size() > 0
                    && magicStoneGame.getBattlefield().playerHasBattlefieldCreature(magicStoneGame.getBattlefield().getPlayer()).size() > 0) {
                        new CopyClicker(magicStoneGame, magicStonePanel, card, frame);
                    }
                    break;
                default:
                    attackOrPlace(false);
                    break;
            }
        } else {
                attackOrPlace(true);
        }
        if(!attackPhase) ((Component) event.getSource()).removeMouseListener(this);
    }

    private void attackOrPlace(boolean attack) {
        int pos = -1;
        if (x >= 140 && x <= 230 && y >= 360 && y <= 495) pos = 0;
        else if (x >= 340 && x <= 430 && y >= 360 && y <= 495) pos = 1;
        else if (x >= 540 && x <= 630 && y >= 360 && y <= 495) pos = 2;
        else if (x >= 740 && x <= 830 && y >= 360 && y <= 495) pos = 3;
        else if (x >= 940 && x <= 1030 && y >= 360 && y <= 495) pos = 4;
        if(pos != -1) {
            if(attack) attack(pos);
            else placeCreature(pos);
        }
    }

    private void attack(int pos) {
        if(!magicStoneGame.getBattlefield().isPlayerTurn() || !magicStoneGame.getBattlefield().isAttackPhase()) {
            ((Component) event.getSource()).removeMouseListener(this);
            magicStoneGame.getBattlefield().setAttackPhase(false);
//            magicStoneGame.getBattlefield().setPlayPhase(true);
//            magicStoneGame.getBattlefield().setPlayerTurn(false);
            return;
        }
        Hero player = magicStoneGame.getBattlefield().getPlayer();
        CreatureCard card = getCard(magicStoneGame, pos);
        if(card != null) {
            if (!card.isUsed()) player.attackPhase(magicStoneGame.getBattlefield(), frame, pos, magicStoneGame, magicStonePanel);
        }
        if(!player.untappedCreatures()) {
//            magicStoneGame.getBattlefield().setAttackPhase(false);
//            magicStoneGame.getBattlefield().setPlayerTurn(false);
            magicStoneGame.getBattlefield().setPlayPhase(false);
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

