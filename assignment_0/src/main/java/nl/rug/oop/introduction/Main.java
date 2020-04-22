package nl.rug.oop.introduction;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Animal[] animals;
        if (args.length == 0) {
            animals = initAnimals();
        } else {
            animals = initAnimals(args);
        }

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            for (Animal animal : animals) {
                if (input.equals("eat")) {
                    /* Calls the eat() method of the object Animal */
                    animal.eat();
                } else if (input.equals("run")) {
                    /* Calls the run() method of the object Animal */
                    animal.run();
                }
                /* Print the hunger level to verify whether something changed */
                animal.printHungerLevel();
            }

        }
    }

    /**
     * Initialises an array of animals hardcoded in the program
     *
     * @return An array of animals
     */
    private static Animal[] initAnimals() {
        /* Create a few different animals*/
        /* you can be very creative here */
        Animal dog = new Animal("Dog", 8);
        Animal ant = new Animal("Ant", 2);
        Animal lion = new Animal("Mufasa", 600);

        /* Put the animals in an array and return this array */
        Animal[] animals = {dog, ant, lion};
        return animals;
    }

    /**
     * Overloaded method initAnimals
     * Will initialise an array of Animals with the names provided in the arguments
     *
     * @param args The command-line arguments. Contains the names of the animals we
     *             want to initialise
     * @return An array of animals with names specified in args
     */
    private static Animal[] initAnimals(String[] args) {
        Animal[] animals = new Animal[args.length];
        for (int i = 0; i < args.length; i++) {
            animals[i] = new Animal(args[i]);
        }
        return animals;
    }


}