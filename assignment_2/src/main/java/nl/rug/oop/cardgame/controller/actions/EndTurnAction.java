package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.MagicStoneGame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action performed by End Turn Button
 */
public class EndTurnAction extends AbstractAction {

    private final MagicStoneGame magicStoneGame;

    /**
     * Create new End Turn Action
     * @param magicStoneGame Game
     */
    public EndTurnAction(MagicStoneGame magicStoneGame) {
        super("End Turn");
        this.magicStoneGame = magicStoneGame;
    }

    /**
     * End the players turn
     * @param e Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        magicStoneGame.endPlayerTurn();
    }

}