package nl.rug.oop.rpg;

public class MiniBossDoor extends Door {

    private String wizardColor;

    public MiniBossDoor(String description, Room from, Room to, String wizardColor) {
        super(DefaultStats.MINI_BOSS_DOOR, from, to);
        this.wizardColor = wizardColor;
    }

    public String getWizardColor() {
        return this.wizardColor;
    }
}
