package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;
import nl.rug.oop.cardgame.view.panel.MagicStonePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AttackPhaseAction extends AbstractAction {

    private MagicStoneGame magicStoneGame;
    private CardClicker clicker;

    public AttackPhaseAction(MagicStoneGame magicStoneGame, CardClicker clicker) {
        super("Attack");
        this.magicStoneGame = magicStoneGame;
        this.clicker = clicker;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(magicStoneGame.getBattlefield().getPlayer().untappedCreatures()) {
            magicStoneGame.getBattlefield().setAttackPhase(true);
            clicker.startAttackPhase();
        }
//        else magicStoneGame.getBattlefield().setPlayPhase(false);
        else magicStoneGame.getBattlefield().setPlayerTurn(false);
    }

}