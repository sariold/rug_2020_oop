package nl.rug.oop.cardgame;

import nl.rug.oop.cardgame.card.Card;

import java.util.Scanner;

/**
 * Start Game
 */
public class StartGame {

    /**
     * Creates a new Battlefield and starts the game
     */
    public StartGame() {
        Battlefield battlefield = new Battlefield();
        startGame(battlefield);
    }

    /**
     * Starts the actual turn based game
     * @param battlefield Playing board
     */
    public void startGame(Battlefield battlefield) {
       turnRotation(battlefield);
    }

    /**
     * Present the player with game options
     * @param battlefield Playing board
     * @param player Hero player
     */
    public void gameOptions(Battlefield battlefield, Hero player) {
        Scanner scanner = new Scanner(System.in);
        boolean start = true;
        int currentMove = 0;
        System.out.println("YOUR TURN!");
        while (start) {
            System.out.println("Current Mana: " + player.getMana());
            System.out.println("0) Draw a Card");
            System.out.println("1) Check Hand");
            System.out.println("2) Play a Card");
            System.out.println("3) Discard a Card");
            System.out.println("4) End turn");
            try {
                currentMove = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("NOT VALID INPUT!");
            }
            switch (currentMove) {
                case 0:
                    Card card = player.getDeck().drawCard();
                    System.out.println("Drawing card: " + card.getName());
                    player.getDeckHand().addCard(card);
                    break;
                case 1:
                    player.getDeckHand().viewHand();
                    break;
                case 2:
                    player.playCard();
                    battlefield.setPlayerTurn(false);
                    start = false;
                    break;
                case 3:
                    player.getDeckHand().discardCard();
                    break;
                case 4:
                    battlefield.setPlayerTurn(false);
                    start = false;
                    break;
            }
        }
    }

    /**
     * Rotates the turns
     * @param battlefield Playing board
     */
    public void turnRotation(Battlefield battlefield) {
        Hero player = battlefield.getPlayer();
        Hero ai = battlefield.getAi();
        boolean start = true;
        while(start) {
            if(battlefield.isPlayerTurn())  {
                battlefield.incMana(player);
                gameOptions(battlefield, player);
            }
            else {
                battlefield.incMana(ai);
                System.out.println("AI PLAYS ITS TURN!");
                battlefield.setPlayerTurn(true);
            }
        }
    }
}
