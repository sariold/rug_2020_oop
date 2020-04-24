package nl.rug.oop.rpg;

public interface Attackable {

    public void attack(Attackable attacked);

    public void reduceHitPoints(int value);

    public boolean isDead();
}
