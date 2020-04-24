package nl.rug.oop.rpg;

import java.lang.reflect.Array;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Room startingRoom = new Room("A rather dusty room full with computers and two doors.");
        Player player = new Player("P1", startingRoom);
        Scanner scanner = new Scanner(System.in);
        String[] possibleMoves;
        int currentMove;


        while (true) {
            System.out.println("What do you want to do?");
            possibleMoves = player.getPossibleMoves();
            for (int i = 0; i < possibleMoves.length; i++) {
                System.out.println("\t(" + i + ") " + possibleMoves[i]);
            }
            currentMove = scanner.nextInt();
            switch (currentMove) {
                // Look Around
                case 0:
                    System.out.print("You see: ");
                    player.getCurrentRoom().inspect();
                    break;
            }
        }
    }
}