package nl.rug.oop.grapheditor.model;

import lombok.Data;

@Data
public class CursorCoords {

    private int x;
    private int y;

    public CursorCoords() {
        this.x = 0;
        this.y = 0;
    }
}
