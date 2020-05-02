package nl.rug.oop.rpg.interfaces;

public interface Attackable {

    public void checkStatusImpairments();

    public int getHitPoints();

    public void attack(Attackable attacked);

    public void reduceHitPoints(int value);

    public void increaseHitPoints(int value);

    public boolean isDead();
}
