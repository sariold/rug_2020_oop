package nl.rug.oop.cardgame.model;

import lombok.Data;
import nl.rug.oop.cardgame.model.card.MoveableCard;
import nl.rug.oop.cardgame.model.deck.DiscardDeck;
import nl.rug.oop.cardgame.model.hero.Hero;
import nl.rug.oop.cardgame.view.MagicStoneFrame;

import java.util.Observable;
import java.util.Observer;

@Data
public class MagicStoneGame extends Observable implements Observer {

    Battlefield battlefield;
    private MoveableCard movable;
    private DiscardDeck discardPile;

    public MagicStoneGame() {
        this.battlefield = new Battlefield();
//        startGame(this.battlefield);
    }

    /**
     * Starts the actual turn based game
     *
     * @param battlefield Playing board
     */
    public void startGame(Battlefield battlefield, MagicStoneFrame frame) {
        turnRotation(battlefield, frame);
    }

    /**
     * Rotates the turns
     *
     * @param battlefield Playing board
     */
    public void turnRotation(Battlefield battlefield, MagicStoneFrame frame) {
        Hero player = battlefield.getPlayer();
        Hero ai = battlefield.getAi();
        boolean start = true;
        for (int i = 1; start; i++) {
            frame.update(frame.getGraphics());
            System.out.println();
            System.out.println("It's turn number " + ((i + (i % 2)) / 2));
            if (i % 2 == 1) {
                resetUsedCreatures(player);
                frame.update(frame.getGraphics());
                battlefield.showBattlefield();
                battlefield.incMana(player);
                player.setMana(player.getMaxMana());
                frame.update(frame.getGraphics());
                player.takeTurn(battlefield, frame);
                frame.update(frame.getGraphics());
            } else {
                resetUsedCreatures(ai);
                battlefield.incMana(ai);
                ai.setMana(ai.getMaxMana());
                ai.takeTurn(battlefield, frame);
                battlefield.setPlayerTurn(true);
            }
            endGameCheck(battlefield);
        }
    }

    /**
     * Checks whether either hero has died if so ends the game
     *
     * @param battlefield Battlefield
     */
    private void endGameCheck(Battlefield battlefield) {
        if (battlefield.getPlayer().getHealth() <= 0) loseGame();
        else if (battlefield.getAi().getHealth() <= 0) winGame();
    }

    /**
     * Ends the game player has won
     */
    private void winGame() {
        System.out.println("You have won against the perfection of artificial intelligence");
        System.exit(0);
    }

    /**
     * Ends the game player has lost
     */
    private void loseGame() {
        System.out.println("Lost against a lousy machine you N00B!");
        System.exit(0);
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

    @Override
    public void update(Observable observable, Object message) {
        setChanged();
        notifyObservers();
    }

//    /**
//     * Creates a movable card and adds an Observer to it
//     */
//    private void createMovableCard() {
//        if (movable != null) {
//            movable.deleteObserver(this);
//            movable = null;
//        }
//        if (!deck.isEmpty()) {
//            movable = new MovableCard(deck.draw());
//            movable.addObserver(this);
//        }
//    }

//    /**
//     * Create a new Draw with all 54 different cards in the deck once
//     */
//    public DrawGame() {
//        deck = makeDeck();
//        discardPile = new DiscardPile();
//        createMovableCard();
//    }
//
//    /**
//     * Getter for deck so it may be looked at without being changed
//     */
//    public AbstractDeck getDeck() {
//        return deck;
//    }

    /**
     * Observe the state of the discard pile without allowing other classes
     * access
     */
    public DiscardDeck getDiscardPile() {
        return discardPile;
    }

    /**
     * Look at which card is movable
     */
    public MoveableCard getMovableCard() {
        return movable;
    }

    /**
     * Draw a card and put it on the discard pile
     */
//    public void move() {
//        if (movable != null) {
//            discardPile.discard(movable.getCard());
//        }
//        createMoveableCard();
//        setChanged();
//        notifyObservers();
//    }

}
