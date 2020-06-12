package nl.rug.oop.grapheditor.model;

import lombok.Data;

<<<<<<< HEAD
/**
 * Track the cursor's coordinates
 */
@Data
=======
>>>>>>> 9fb674e11454a90dec45a8f75a9d5cae919838bd
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
