package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.controller.button.EndTurnButton;
import nl.rug.oop.cardgame.model.MagicStoneGame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EndTurnAction extends AbstractAction {

    private MagicStoneGame magicStoneGame;

    public EndTurnAction(MagicStoneGame magicStoneGame) {
        super("End Turn");
        this.magicStoneGame = magicStoneGame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        magicStoneGame.endPlayerTurn();
    }

}