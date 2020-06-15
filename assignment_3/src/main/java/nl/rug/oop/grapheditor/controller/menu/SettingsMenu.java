package nl.rug.oop.grapheditor.controller.menu;

import nl.rug.oop.grapheditor.controller.settings.Settings;
import nl.rug.oop.grapheditor.view.panel.GraphPanel;
import org.intellij.lang.annotations.Flow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsMenu extends JFrame {
    
    private Settings settings;
    private JButton node, edge, highlight, background;
    
    public SettingsMenu(Settings settings) {
        super("Settings");
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        setPreferredSize(new Dimension(400, 600));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        this.settings = settings;
        node = new JButton("Node Color");
        edge = new JButton("Edge Color");
        highlight = new JButton("Highlight Color");
        background = new JButton("Background Color");
        c.add(node);
        c.add(edge);
        c.add(highlight);
        c.add(background);
        changeSettings();
    }

    private void changeSettings() {
        node.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color c = JColorChooser.showDialog(null , "Node Color",settings.getNodeColor());
                settings.setNodeColor(c);
            }
        });
        edge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color c = JColorChooser.showDialog(null , "Edge Color",settings.getNodeColor());
                settings.setEdgeColor(c);
            }
        });
        highlight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color c = JColorChooser.showDialog(null , "Highlight Color",settings.getNodeColor());
                settings.setHighlightColor(c);
            }
        });
        background.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color c = JColorChooser.showDialog(null , "Background Color",settings.getNodeColor());
                settings.setBackgroundColor(c);
            }
        });
    }

}
