package nl.rug.oop.rpg.objects;

import nl.rug.oop.rpg.DefaultStats;

import java.io.Serializable;

public class MiniBossDoor extends Door implements Serializable {

    private static final long serialVersionUID = 7L;

    private String wizardColor;
    private boolean defeated;

    public MiniBossDoor(String description, Room from, Room to, String wizardColor, boolean defeated) {
        super(DefaultStats.MINI_BOSS_DOOR, from, to);
        this.wizardColor = wizardColor;
        this.defeated = defeated;
    }

    public String getWizardColor() {
        return this.wizardColor;
    }

    public boolean isDefeated() {
        return defeated;
    }

    public void defeated() {
        this.defeated = true;
    }
}
