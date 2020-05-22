package nl.rug.oop.cardgame.controller.clicker;

import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.model.card.CreatureCard;
import nl.rug.oop.cardgame.model.hero.Hero;
import nl.rug.oop.cardgame.view.panel.MagicStonePanel;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * A card clicker that lets the player interact with the battlefield
 */
public class BattlefieldClicker extends MouseInputAdapter {

    private final MagicStoneGame magicStoneGame;
    private final MagicStonePanel magicStonePanel;
    private int x;
    private int y;
    private final Card card;
    private ArrayList<Integer> freePos;
    private final boolean attackPhase;
    private MouseEvent event;

    /**
     * Creates a new mouse input to interact with the battlefield
     * @param magicStoneGame Game
     * @param magicStonePanel Panel
     * @param card Card
     * @param attackPhase Player is in attack phase
     */
    public BattlefieldClicker(MagicStoneGame magicStoneGame, MagicStonePanel magicStonePanel, Card card,
                              boolean attackPhase) {
        this.magicStoneGame = magicStoneGame;
        this.magicStonePanel = magicStonePanel;
        this.card = card;
        magicStonePanel.addMouseListener(this);
        this.freePos = magicStoneGame.getBattlefield().getFreePositions(magicStoneGame.getBattlefield().getPlayer());
        this.attackPhase = attackPhase;
        if(card instanceof CreatureCard && !attackPhase) magicStoneGame.getBattlefield().setPlayPhase(true);
        else if(attackPhase) magicStoneGame.getBattlefield().setAttackPhase(true);
    }

    /**
     * Lets the player place a creature on a free position on the battlefield
     * Adjusts the free positions on the battlefield
     * @param pos Position where the creature is placed
     */
    private void placeCreature(int pos) {
        for(int i = 0; i < freePos.size(); i++) {
            if(freePos.get(i) == pos) {
                magicStoneGame.getBattlefield().getPlayer().playCard(magicStoneGame.getBattlefield(), pos, card);
                freePos = magicStoneGame.getBattlefield().getFreePositions(magicStoneGame.getBattlefield().getPlayer());
                return;
            }
        }
    }

    /**
     * Lets the player play a spell
     */
    private void playSpell() {
        magicStoneGame.getBattlefield().getPlayer().playCard(magicStoneGame.getBattlefield(), 0, card);
        magicStoneGame.endGameCheck(magicStoneGame.getBattlefield());
    }

    /**
     * Defines what happens when the maóuse is clicked
     * @param event Mouse Click
     */
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
                        new CopyClicker(magicStoneGame, magicStonePanel, card);
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

    /**
     * Place or attack with the creature that was at the position clicked on
     * @param attack attack
     */
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

    /**
     * Ends the players turn if the attack phase is over
     * Attacks with an untapped creature if it has been clicked
     * @param pos Position of the clicked creature
     */
    private void attack(int pos) {
        if(!magicStoneGame.getBattlefield().isPlayerTurn() || !magicStoneGame.getBattlefield().isAttackPhase()) {
            ((Component) event.getSource()).removeMouseListener(this);
            magicStoneGame.getBattlefield().setAttackPhase(false);
            return;
        }
        Hero player = magicStoneGame.getBattlefield().getPlayer();
        CreatureCard card = getCard(magicStoneGame, pos);
        if(card != null) {
            if (!card.isUsed()) player.attackPhase(magicStoneGame.getBattlefield(), pos, magicStoneGame);
        }
        if(!player.untappedCreatures()) {
            magicStoneGame.getBattlefield().setPlayPhase(false);
        }
    }

    /**
     * Returns the players creature at the specified position
     * @param magicStoneGame Game
     * @param pos Position
     * @return CreatureCard
     */
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

