package nl.rug.oop.grapheditor.controller.settings;

import java.awt.*;

public class Settings {

    private Color backgroundColor;
    private Color highlightColor;
    private Color nodeColor;
    private Color edgeColor;
    private Color textColor;
    private Font nodeFont;

    public Settings() {
        this.backgroundColor = Color.GRAY;
        this.highlightColor = Color.GREEN;
        this.nodeColor = Color.BLACK;
        this.edgeColor = Color.BLACK;
        this.textColor = Color.WHITE;
        this.nodeFont = new Font("Times New Roman", Font.PLAIN, 12);
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public Color getNodeColor() {
        return nodeColor;
    }

    public void setNodeColor(Color nodeColor) {
        this.nodeColor = nodeColor;
    }

    public Color getEdgeColor() {
        return edgeColor;
    }

    public void setEdgeColor(Color edgeColor) {
        this.edgeColor = edgeColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getHighlightColor() {
        return highlightColor;
    }

    public void setHighlightColor(Color highlightColor) {
        this.highlightColor = highlightColor;
    }

    public Font getNodeFont() {
        return nodeFont;
    }

    public void setNodeFont(Font nodeFont) {
        this.nodeFont = nodeFont;
    }
}
