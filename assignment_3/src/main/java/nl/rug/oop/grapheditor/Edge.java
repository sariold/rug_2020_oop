package nl.rug.oop.grapheditor;

import lombok.Data;

@Data
public class Edge {

    private Node start;
    private Node end;

    public Edge(Node start, Node end) {
        this.start = start;
        this.end = end;
    }
}
