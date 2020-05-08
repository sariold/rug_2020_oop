package nl.rug.oop.rpg.config;

import nl.rug.oop.rpg.gui.GUIMessages;
import nl.rug.oop.rpg.game.Game;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

/**
 * Creates a config class that allows the user to change the java properties of a game
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class Config {

    /**
     * Allows the user to set a custom config for custom java properties and save custom config file under custom name
     * @param configName ConfigName
     */
    public static void setConfig(String configName) {
        Properties properties = new Properties();
        Scanner scanner = new Scanner(System.in);
        String name;
        int maxHealth, health, attack, gold;
        while (true) {
            System.out.println("What name should the player have?");
            name = scanner.nextLine();
            if (name.length() > 0 && name.length() < 16) {
                break;
            }
            System.out.println("Your name can only have 15 characters.");
        }

        properties.setProperty("playerName", name);
        while (true) {
            System.out.println("How much maximum health should the player have?");
            try{
                maxHealth = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                GUIMessages.invalidInputMessage();
                System.out.println("Let's try again!");
                scanner.nextLine();
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
                GUIMessages.invalidInputMessage();
                System.out.println("Let's try again!");
                scanner.nextLine();
            }
        }
        properties.setProperty("playerHealth", Integer.toString(health));
        while (true) {
            System.out.println("How much attack should the player have?");
            try{
                attack = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                GUIMessages.invalidInputMessage();
                System.out.println("Let's try again!");
                scanner.nextLine();
            }
        }
        properties.setProperty("playerAttack", Integer.toString(attack));
        while (true) {
            System.out.println("How much gold should the player have?");
            try{
                gold = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                GUIMessages.invalidInputMessage();
                System.out.println("Let's try again!");
                scanner.nextLine();
            }
        }
        properties.setProperty("playerGold", Integer.toString(gold));
        File configDirectory = new File("config");
        configDirectory.mkdir();

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(configDirectory + File.separator
                    + configName + ".ini");
            properties.store(fileOutputStream, "Player configuration");
            System.out.println("Player configuration set to:\nName: " + name + "\nMax Health: " + maxHealth
                    + "\nHealth: " + health + "\nAttack: " + attack + "\nGold: " + gold);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found!");
        } catch (IOException e) {
            System.out.println("Could not write to file!");
        }
    }

    /**
     * Allows the user to load from a custom config file with custom java properties
     * @param game Game
     * @param configName ConfigName
     */
    public static void loadConfig(Game game, String configName) {
        File configDirectory = new File("config");
        configDirectory.mkdir();
        try {
            FileInputStream fileInputStream = new FileInputStream(configDirectory + File.separator + configName
                    + ".ini");
            Properties properties = new Properties();
            properties.load(fileInputStream);
            game.getPlayer().setName(properties.getProperty("playerName"));
            game.getPlayer().setMaxHitPoints(Integer.parseInt(properties.getProperty("playerMaxHealth")));
            game.getPlayer().setHitPoints(Integer.parseInt(properties.getProperty("playerHealth")));
            game.getPlayer().setAttackPoints(Integer.parseInt(properties.getProperty("playerAttack")));
            game.getPlayer().setGold(Integer.parseInt(properties.getProperty("playerGold")));
            System.out.println("The " + configName + " config was loaded successfully!");
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found!");
        } catch (IOException e) {
            System.out.println("Could not read from file!");
        }
    }

}
