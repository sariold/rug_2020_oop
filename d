[33mcommit 93d92589aea021503c282715245afb0287e13dfe[m[33m ([m[1;35mrefs/stash[m[33m)[m
Merge: a094abb dee6bc6
Author: Diego Sariol <sarioldiego@yahoo.com>
Date:   Sat May 2 22:46:33 2020 +0200

    WIP on development: a094abb set up serialization class, set up all serialization uid for each class that extends serialization

[1mdiff --cc assignment_1/src/main/java/nl/rug/oop/rpg/Game.java[m
[1mindex c3d246a,c3d246a..c6a97e7[m
[1m--- a/assignment_1/src/main/java/nl/rug/oop/rpg/Game.java[m
[1m+++ b/assignment_1/src/main/java/nl/rug/oop/rpg/Game.java[m
[36m@@@ -61,6 -61,6 +61,8 @@@[m [mpublic class Game implements Serializab[m
          this.possibleMoves.add("Look for items");[m
          this.possibleMoves.add("Look in your inventory");[m
          this.possibleMoves.add("Look at your stats");[m
[32m++        this.possibleMoves.add("QuickSave");[m
[32m++        this.possibleMoves.add("QuickLoad");[m
  [m
          this.fightMoves.add("Run");[m
          this.fightMoves.add("Attack");[m
[36m@@@ -75,6 -75,6 +77,10 @@@[m
          }[m
      }[m
  [m
[32m++    public ArrayList<String> getPossibleMoves() {[m
[32m++        return new ArrayList<String>(possibleMoves);[m
[32m++    }[m
[32m++[m
      public ArrayList<String> getFightMoves() {[m
          return new ArrayList<String>(fightMoves);[m
      }[m
[36m@@@ -87,10 -87,10 +93,10 @@@[m
          return !player.isDead();[m
      }[m
  [m
[31m--    public void printOptions() {[m
[32m++    public void printOptions(ArrayList<String> arrayList) {[m
          System.out.println("What do you want to do?");[m
[31m--        for (int i = 0; i < this.possibleMoves.size(); i++) {[m
[31m--            System.out.println("\t(" + i + ") " + this.possibleMoves.get(i));[m
[32m++        for (int i = 0; i < arrayList.size(); i++) {[m
[32m++            System.out.println("\t(" + i + ") " + arrayList.get(i));[m
          }[m
      }[m
  [m
[1mdiff --cc assignment_1/src/main/java/nl/rug/oop/rpg/Main.java[m
[1mindex 8ec472f,8ec472f..632cee0[m
[1m--- a/assignment_1/src/main/java/nl/rug/oop/rpg/Main.java[m
[1m+++ b/assignment_1/src/main/java/nl/rug/oop/rpg/Main.java[m
[36m@@@ -4,6 -4,6 +4,6 @@@[m [mpublic class Main [m
  [m
      public static void main(String[] args) {[m
          StartGame startGame = new StartGame();[m
[31m--        startGame.initGame();[m
[32m++        startGame.startGameOption();[m
      }[m
  }[m
[1mdiff --cc assignment_1/src/main/java/nl/rug/oop/rpg/StartGame.java[m
[1mindex 6a05b62,6a05b62..d6e60ee[m
[1m--- a/assignment_1/src/main/java/nl/rug/oop/rpg/StartGame.java[m
[1m+++ b/assignment_1/src/main/java/nl/rug/oop/rpg/StartGame.java[m
[36m@@@ -1,5 -1,5 +1,9 @@@[m
  package nl.rug.oop.rpg;[m
  [m
[32m++import nl.rug.oop.rpg.io.Serializer;[m
[32m++[m
[32m++import java.io.IOException;[m
[32m++import java.util.ArrayList;[m
  import java.util.InputMismatchException;[m
  import java.util.Scanner;[m
  [m
[36m@@@ -8,7 -8,7 +12,62 @@@[m [mpublic class StartGame [m
      public static void main(String[] args) {[m
      }[m
  [m
[31m--    public void initGame() {[m
[32m++    public void startGameOption() {[m
[32m++        ArrayList<String> possibleMoves = new ArrayList<String>();[m
[32m++        possibleMoves.add("New Game");[m
[32m++        possibleMoves.add("Load Game");[m
[32m++        System.out.println("What do you want to do?");[m
[32m++        for (int i = 0; i < possibleMoves.size(); i++) {[m
[32m++            System.out.println("\t(" + i + ") " + possibleMoves.get(i));[m
[32m++        }[m
[32m++        Scanner scanner = new Scanner(System.in);[m
[32m++        int currentMove;[m
[32m++        String fileName = "";[m
[32m++        while(true) {[m
[32m++            try{[m
[32m++                currentMove = scanner.nextInt();[m
[32m++            } catch (Exception e) {[m
[32m++                System.out.println("That is not a valid input!");[m
[32m++                scanner.nextLine();[m
[32m++                continue;[m
[32m++            }[m
[32m++            switch (currentMove) {[m
[32m++                case 0:[m
[32m++                    System.out.println("What would you like to name this new game file?");[m
[32m++                    fileName = fileNamer();[m
[32m++                    initNewGame(fileName);[m
[32m++                    break;[m
[32m++                case 1:[m
[32m++                    System.out.println("Which file would you like to load from?");[m
[32m++                    fileName = fileNamer();[m
[32m++//                    initOldGame(fileName);[m
[32m++                    break;[m
[32m++            }[m
[32m++        }[m
[32m++    }[m
[32m++[m
[32m++    public String fileNamer() {[m
[32m++        String fileName = "";[m
[32m++        Scanner scanner = new Scanner(System.in);[m
[32m++        fileName = scanner.nextLine();[m
[32m++        return fileName;[m
[32m++    }[m
[32m++[m
[32m++    public void initOldGame(String fileName) {[m
[32m++        Game game;[m
[32m++        try {[m
[32m++            game = Serializer.loadGame(fileName);[m
[32m++            gameStart(game);[m
[32m++        } catch (IOException e) {[m
[32m++            System.out.println("Could not load from the file!");[m
[32m++            startGameOption();[m
[32m++        } catch (ClassNotFoundException e) {[m
[32m++            System.out.println("The savefile could not be used to load a game!");[m
[32m++            startGameOption();[m
[32m++        }[m
[32m++    }[m
[32m++[m
[32m++    public void initNewGame(String fileName) {[m
          Scanner scanner = new Scanner(System.in);[m
          String playerName = "";[m
          System.out.println("Please choose a name for your Hero:");[m
[36m@@@ -20,12 -20,12 +79,18 @@@[m
          }[m
          System.out.println(TextColor.ANSI_BLUE + "Slay both mini bosses and be forced to face the final boss!" +[m
                  TextColor.ANSI_RESET);[m
[32m++[m
          Game game = new Game(playerName);[m
[32m++        Serializer.saveGame(game, fileName);[m
[32m++        gameStart(game);[m
[32m++    }[m
  [m
[32m++    public void gameStart(Game game) {[m
[32m++        Scanner scanner = new Scanner(System.in);[m
          int currentMove;[m
  [m
          while (true) {[m
[31m--            game.printOptions();[m
[32m++            game.printOptions(game.getPossibleMoves());[m
              try {[m
                  currentMove = scanner.nextInt();[m
              } catch (InputMismatchException e) {[m
[1mdiff --cc assignment_1/src/main/java/nl/rug/oop/rpg/io/Serializer.java[m
[1mindex 5d96eed,5d96eed..33d0fc6[m
[1m--- a/assignment_1/src/main/java/nl/rug/oop/rpg/io/Serializer.java[m
[1m+++ b/assignment_1/src/main/java/nl/rug/oop/rpg/io/Serializer.java[m
[36m@@@ -1,12 -1,12 +1,15 @@@[m
  package nl.rug.oop.rpg.io;[m
  import nl.rug.oop.rpg.Game;[m
[32m++import nl.rug.oop.rpg.StartGame;[m
  [m
  import java.io.*;[m
[32m++import java.util.ArrayList;[m
[32m++import java.util.Scanner;[m
  [m
  public class Serializer {[m
  [m
      public static void saveGame(Game game, String fileName) {[m
[31m--        File saveDirectory = new File("saves");[m
[32m++        File saveDirectory = new File("assignment_1" + File.separator + "saves");[m
          saveDirectory.mkdir();[m
  [m
          try(FileOutputStream fileOutputStream = new FileOutputStream(saveDirectory + File.separator + fileName + ".ser");[m
[36m@@@ -21,11 -21,11 +24,9 @@@[m
      }[m
  [m
      public static Game loadGame(String fileName) throws IOException, ClassNotFoundException {[m
[31m--        File saveDirectory = new File("saves");[m
[31m--[m
[31m--        try(FileInputStream fileInputStream = new FileInputStream(saveDirectory + File.separator + fileName+ ".ser");[m
[31m--            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {[m
[31m--[m
[32m++        File saveDirectory = new File("assignment_1" + File.separator + "saves");[m
[32m++        try(FileInputStream fileInputStream = new FileInputStream(saveDirectory + File.separator + fileName + ".ser");[m
[32m++        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {[m
              Game game = (Game) objectInputStream.readObject();[m
              return game;[m
          }[m
