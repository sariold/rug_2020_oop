package nl.rug.oop.cardgame.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.model.hero.Hero;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;

import java.util.Observable;

@EqualsAndHashCode(callSuper = true)
@Data
public class MagicStoneGame extends Observable {

    private Battlefield battlefield;
    private MagicStoneFrame frame;

    public MagicStoneGame() {
        this.battlefield = new Battlefield();
    }

    /**
     * Starts the actual turn based game
     *
     */
    public void startGame() {
        notifyUpdate();
        turnRotation();
    }

    /**
     * Rotates the turns
     *
     */
    public void turnRotation() {
        Hero player = battlefield.getPlayer();
        Hero ai = battlefield.getAi();
        boolean start = true;
        for (int i = 1; start; i++) {
            notifyUpdate();
            System.out.println();
            System.out.println("It's turn number " + ((i + (i % 2)) / 2));
            if (i % 2 == 1) {
                battlefield.setDamageBuff(player, false, 0);
                resetUsedCreatures(player);
                notifyUpdate();
                battlefield.incMana(player);
                player.setMana(player.getMaxMana());
                notifyUpdate();
                player.takeTurn(battlefield, this);
                notifyUpdate();
            } else {
                battlefield.setDamageBuff(ai, false, 0);
                resetUsedCreatures(ai);
                battlefield.incMana(ai);
                ai.setMana(ai.getMaxMana());
                ai.takeTurn(battlefield, this);
                battlefield.setPlayerTurn(true);
            }
            endGameCheck(battlefield);
        }
    }

    private void notifyUpdate() {
        setChanged();
        notifyObservers();
    }

    public void endPlayerTurn() {
       this.battlefield.setPlayerTurn(false);
    }

    /**
     * Checks whether either hero has died if so ends the game
     */
    public void endGameCheck(Battlefield battlefield) {
        if (battlefield.getPlayer().getHealth() <= 0) frame.gameOver(false);
        else if (battlefield.getAi().getHealth() <= 0) frame.gameOver(true);
    }

    /**
     * Resets the param used for each played creature
     *
     * @param hero Hero
     */
    public void resetUsedCreatures(Hero hero) {
        if (hero.getPlayedCreatures().size() == 0) return;
        for (int i = hero.getPlayedCreatures().size() - 1; i >= 0; i--) {
            if (hero.getPlayedCreatures().get(i) != null) hero.getPlayedCreatures().get(i).setUsed(false);
        }
    }

}
