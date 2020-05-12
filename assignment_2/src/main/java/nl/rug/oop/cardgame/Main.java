package nl.rug.oop.cardgame;

import nl.rug.oop.cardgame.card.Card;
import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.view.MagicStoneFrame;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        MagicStoneGame magicStoneGame = new MagicStoneGame();
//        new MagicStoneFrame(magicStoneGame);

        Hero player = new Hero("Diego", 10, 1);
        Scanner scanner = new Scanner(System.in);
        boolean start = true;
        int currentMove = 0;
        while(start) {
            System.out.println("0) Draw a Card");
            System.out.println("1) Check Hand");
            System.out.println("2) Play a Card");
            System.out.println("3) Discard a Card");
            System.out.println("4) Exit");

            try {
                currentMove = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("NOT VALID INPUT!");
            }
            switch(currentMove) {
                case 0:
                    Card card = player.getDeck().drawCard();
                    System.out.println("Drawing card: " + card.getName());
                    player.getDeckHand().addCard(card);
                    break;
                case 1:
                    player.getDeckHand().viewHand();
                    break;
                case 2:
                    player.getDeckHand().playCard();
                    break;
                case 3:
                    player.getDeckHand().discardCard();
                    break;
                case 4:
                    start = false;
                    break;
            }
        }


    }
}
