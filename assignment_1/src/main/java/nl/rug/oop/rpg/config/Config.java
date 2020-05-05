package nl.rug.oop.rpg.config;

import nl.rug.oop.rpg.GUI;
import nl.rug.oop.rpg.Game;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

public class Config {

    public static void setConfig() {
        Properties properties = new Properties();
        Scanner scanner = new Scanner(System.in);
        String name;
        int maxHealth, health, attack, gold;
        System.out.println("What name should the player have?");
        name = scanner.nextLine();
        properties.setProperty("playerName", name);
        while (true) {
            System.out.println("How much maximum health should the player have?");
            try{
                maxHealth = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                GUI.invalidInputMessage();
                System.out.println("Let's try again!");
                scanner.nextLine();
                continue;

            }
        }
        properties.setProperty("playerMaxHealth", Integer.toString(maxHealth));
        while (true) {
            System.out.println("How much health should the player have?");
            try{
                health = scanner.nextInt();
                if (health > maxHealth) {
                    System.out.println("You cannot have more health than the maximum!");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                GUI.invalidInputMessage();
                System.out.println("Let's try again!");
                scanner.nextLine();
                continue;

            }
        }
        properties.setProperty("playerHealth", Integer.toString(health));
        while (true) {
            System.out.println("How much attack should the player have?");
            try{
                attack = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                GUI.invalidInputMessage();
                System.out.println("Let's try again!");
                scanner.nextLine();
                continue;

            }
        }
        properties.setProperty("playerAttack", Integer.toString(attack));
        while (true) {
            System.out.println("How much gold should the player have?");
            try{
                gold = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                GUI.invalidInputMessage();
                System.out.println("Let's try again!");
                scanner.nextLine();
                continue;

            }
        }
        properties.setProperty("playerGold", Integer.toString(gold));
        File configDirectory = new File("config");
        configDirectory.mkdir();

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(configDirectory + File.separator + "playerConfig" + ".ini");
            properties.store(fileOutputStream, "Player configuration");
            System.out.println("Player configuration set to:\nName: " + name + "\nMax Health: " + maxHealth + "\nHealth: " + health + "\nAttack: " + attack + "\nGold: " + gold);
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found!");
        } catch (IOException e) {
            System.out.println("Could not write to file!");
        }
    }

    public static void loadConfig(Game game) {
        File configDirectory = new File("config");
        configDirectory.mkdir();
        try {
            FileInputStream fileInputStream = new FileInputStream(configDirectory + File.separator + "playerConfig" + ".ini");
            Properties properties = new Properties();
            properties.load(fileInputStream);
            game.getPlayer().setName(properties.getProperty("playerName"));
            game.getPlayer().setMaxHitPoints(Integer.parseInt(properties.getProperty("playerMaxHealth")));
            game.getPlayer().setHitPoints(Integer.parseInt(properties.getProperty("playerHealth")));
            game.getPlayer().setAttackPoints(Integer.parseInt(properties.getProperty("playerAttack")));
            game.getPlayer().setGold(Integer.parseInt(properties.getProperty("playerGold")));
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found!");
        } catch (IOException e) {
            System.out.println("Could not read from file!");
        }
    }

}
