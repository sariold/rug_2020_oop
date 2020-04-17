package nl.rug.oop.introduction;

public class Animal {

    private String name;
    private int energy;

    /**
     * Constructor
     * Note that the constructor is public: we want to be able to make an
     * instance of this class from other classes
     *
     * @param name   The name we want the animal to have
     * @param energy The amount of initial energy we want the animal to have
     */
    public Animal(String name, int energy) {
        this.name = name;
        this.energy = energy;
    }

    public Animal(String name) {
        this(name, 5);
    }

    /**
     * Makes the animal run
     * Note that the method is public: we want to be able to call this method
     * from other classes
     */
    public void run() {
        if (energy > 0) {
            System.out.println(name + ": Running!");
            energy--;
        } else {
            System.out.println(name + ": Out of energy!");
        }
    }

    /**
     * Makes the animal eat
     * Note that the method is public: we want to be able to call this method
     * from other classes
     */
    public void eat() {
        System.out.println(name + ": Eating!");
        energy++;
    }

    /**
     * Prints the hunger level
     * Note that the method is public: we want to be able to call this method
     * from other classes
     */
    public void printHungerLevel() {
        System.out.println(name + " energy level: " + energy);
    }


}
