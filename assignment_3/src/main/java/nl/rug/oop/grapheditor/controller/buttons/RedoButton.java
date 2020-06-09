package nl.rug.oop.grapheditor.controller.buttons;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class RedoButton extends JButton {
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
    public RedoButton() {
        super((Action) null);
        setButtonProperties();
    }
}
