package nl.rug.oop.rpg;

public interface Attackable {

    public int getHitPoints();

    public void attack(Attackable attacked);

    public void reduceHitPoints(int value);

    public void increaseHitPoints(int value);

    public boolean isDead();
}
