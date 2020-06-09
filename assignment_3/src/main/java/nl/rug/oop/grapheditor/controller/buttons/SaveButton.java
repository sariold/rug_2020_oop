package nl.rug.oop.grapheditor.controller.buttons;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class SaveButton extends JButton {
    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_S);
    }

    /**
     * Create new add node button
     */
    public SaveButton() {
        super((Action) null);
        setButtonProperties();
    }
}
