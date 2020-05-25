package nl.rug.oop.grapheditor;

import lombok.Data;

@Data
public class Node {

    private String name;
    private int size;
    private int[] location;

    public Node(String name, int size, int[] location) {
        this.name = name;
        this.size = size;
        this.location = location;
    }
}
