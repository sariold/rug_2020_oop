package nl.rug.oop.grapheditor.util.printer;

import java.awt.event.MouseEvent;

/**
 * Static class to print Information about the location of interactions with the mouse
 */
public class PrintMouseInfo {

    /**
     * Print the location where the mouse is released
     * @param e Mouse Released Event
     */
    public static void MouseReleased(MouseEvent e) {
        System.out.println("Mouse Released:\n" +
                "\tX:" + e.getX() + "\n" +
                "\tY" + e.getY() + "\n");
    }

    /**
     * Print the location where the mouse is clicked
     * @param e Mouse Clicked Event
     */
    public static void MouseClicked(MouseEvent e) {
        System.out.println("Mouse Clicked:\n" +
                "\tX:" + e.getX() + "\n" +
                "\tY" + e.getY() + "\n");
    }

    /**
     * Print the location where the mouse is pressed
     * @param e Mouse Pressed Event
     */
    public static void MousePressed(MouseEvent e) {
        System.out.println("Mouse Pressed:\n" +
                "\tX:" + e.getX() + "\n" +
                "\tY" + e.getY() + "\n");
    }

    /**
     * Print the location where the mouse is dragged
     * @param e Mouse Dragged Event
     */
    public static void MouseDragged(MouseEvent e) {
        System.out.println("Mouse Dragged:\n" +
                "\tX:" + e.getX() + "\n" +
                "\tY" + e.getY() + "\n");
    }

    /**
     * Print the location where the mouse is moved
     * @param e Mouse Moved Event
     */
    public static void MouseMoved(MouseEvent e) {
        System.out.println("Mouse Moved:\n" +
                "\tX:" + e.getX() + "\n" +
                "\tY" + e.getY() + "\n");
    }
}
