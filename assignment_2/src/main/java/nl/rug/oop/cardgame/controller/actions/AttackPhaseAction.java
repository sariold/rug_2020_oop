package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.view.MagicStoneFrame;
import nl.rug.oop.cardgame.view.MagicStonePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AttackPhaseAction extends AbstractAction {

    private MagicStoneGame magicStoneGame;
    private MagicStoneFrame frame;
    private MagicStonePanel panel;
    private CardClicker clicker;

    public AttackPhaseAction(MagicStoneGame magicStoneGame, MagicStoneFrame frame, MagicStonePanel panel, CardClicker clicker) {
        super("Attack");
        this.magicStoneGame = magicStoneGame;
        this.frame = frame;
        this.panel = panel;
        this.clicker = clicker;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(magicStoneGame.getBattlefield().getPlayer().untappedCreatures()) {
            magicStoneGame.getBattlefield().setAttackPhase(true);
            clicker.startAttackPhase();
        } else magicStoneGame.getBattlefield().setPlayerTurn(false);
    }

}