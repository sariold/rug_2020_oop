package nl.rug.oop.grapheditor.model;

import lombok.Data;

/**
 * Track the cursor's coordinates
 */
@Data
public class CursorCoords {

    private int x;
    private int y;

    /**
     * Create a new cursor coordinates object
     */
    public CursorCoords() {
        this.x = 0;
        this.y = 0;
    }
}
