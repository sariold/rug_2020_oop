package nl.rug.oop.cardgame.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.model.hero.Hero;

import java.util.Observable;

/**
 * A game environment
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MagicStoneGame extends Observable {

    private Battlefield battlefield;
    private boolean lost;
    private boolean won;

    /**
     * Create a new game
     */
    public MagicStoneGame() {
        won = lost = false;
        this.battlefield = new Battlefield();
    }

    /**
     * Starts the actual turn based game
     */
    public void startGame() {
        turnRotation();
    }

    /**
     * Rotates the turns
     */
    public void turnRotation() {
        Hero player = battlefield.getPlayer();
        Hero ai = battlefield.getAi();
        boolean start = true;
        for (int i = 0; i < 2; i++) {
            Card c = player.getDeck().drawCard();
            player.getDeckHand().addCard(player.getDiscardDeck(), c);
            c = ai.getDeck().drawCard();
            ai.getDeckHand().addCard(ai.getDiscardDeck(), c);
        }
        for (int i = 1; start; i++) {
            notifyUpdate();
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

    /**
     * notifies observers of change
     */
    private void notifyUpdate() {
        setChanged();
        notifyObservers();
    }

    /**
     * End the players turn
     */
    public void endPlayerTurn() {
       this.battlefield.setPlayerTurn(false);
    }

    /**
     * Checks whether either hero has died if so ends the game
     */
    public void endGameCheck(Battlefield battlefield) {
        if (battlefield.getPlayer().getHealth() <= 0) lost = true;
        else if (battlefield.getAi().getHealth() <= 0) won = true;
    }

    /**
     * Resets the param used for each played creature
     * @param hero Hero
     */
    public void resetUsedCreatures(Hero hero) {
        if (hero.getPlayedCreatures().size() == 0) return;
        for (int i = hero.getPlayedCreatures().size() - 1; i >= 0; i--) {
            if (hero.getPlayedCreatures().get(i) != null) hero.getPlayedCreatures().get(i).setUsed(false);
        }
    }

}
