package nl.rug.oop.rpg;

public class Spider extends Enemy{

    public Spider(String description, String name) {
        super(description, name, DefaultStats.SPIDER_HIT_POINTS , DefaultStats.SPIDER_ATTACK_POINTS, DefaultStats.SPIDER_GOLD_VALUE);
    }

    public Spider(String name) {
        this("*loud clicking*!!", name);
    }

    @Override
    public String getSpecies() {
        return "Spider";
    }
}
