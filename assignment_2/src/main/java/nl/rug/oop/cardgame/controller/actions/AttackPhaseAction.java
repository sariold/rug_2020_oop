package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.controller.clicker.CardClicker;
import nl.rug.oop.cardgame.model.MagicStoneGame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * The action executed by the Attack phase button
 */
public class AttackPhaseAction extends AbstractAction {

    private final MagicStoneGame magicStoneGame;
    private final CardClicker clicker;

    /**
     * Creates a new Attack phase action
     * @param magicStoneGame Game
     * @param clicker Clicker
     */
    public AttackPhaseAction(MagicStoneGame magicStoneGame, CardClicker clicker) {
        super("Attack");
        this.magicStoneGame = magicStoneGame;
        this.clicker = clicker;
    }

    /**
     * Lets the player attack with creatures if he has any untapped ones
     * @param e Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(magicStoneGame.getBattlefield().getPlayer().untappedCreatures()) {
            magicStoneGame.getBattlefield().setAttackPhase(true);
            clicker.startAttackPhase();
        }
        else magicStoneGame.getBattlefield().setPlayerTurn(false);
    }

}