package nl.rug.oop.cardgame.view.panel;

import nl.rug.oop.cardgame.controller.button.MainMenuButton;
import nl.rug.oop.cardgame.model.menu.MainMenu;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class TutorialPanel extends JPanel implements Observer {

    private MainMenu mainMenu;
    private MagicStoneFrame frame;
    private JTextArea textArea;

    public TutorialPanel(MainMenu mainMenu, MagicStoneFrame frame) {
        this.mainMenu = mainMenu;
        this.mainMenu.addObserver(this);
        this.frame = frame;
        Font logoFont;
        textArea = new JTextArea();
        MainMenuButton backButton = new MainMenuButton(mainMenu);
        backButton.setBounds(20, 630, 200, 50);
        textArea.setText("GENERAL:\n\n" +
                "Your goal is to reduced the enemies health to 0.\n\n" +
                "Your deck consists of creatures and spells.\n" +
                "You draw a card at the beginning of each turn and you can discard cards by clicking them and then " +
                "clicking on your discard pile.\n\n" +
                "Your mana increases by 1 every turn until a maximum of 10 mana has been reached.\n" +
                "Each card costs mana according to the cost written on them.\n\n" +
                "Creatures have a health and an attack value. You can click them in the hand and then click on " +
                "a free spot on the battlefield to play them.\n\nYou can also play spells that have an effect on creatures " +
                "or the heros.\nFor more information on how to play spells go to the Card Collection.\n\n" +
                "ATTACK PHASE:\n\n"+
                "When you are done playing cards from your hand you can enter the attack phase by clicking the attack" +
                "button.\nAny creatures that have not been played or have attacked yet this turn can now attack.\n" +
                "Creatures always attack the opposite enemy. If there is an enemy creature opposed to them they will" +
                " attack that creature.\n" +
                "If there is no opposing creature they will attack the enemy hero. Creatures lose health according to the " +
                "attack value of who they attack permanently. If the health drops to 0 they die.\n\n" +
                "END OF TURN:\n\n" +
                "After your attack phase your turn ends. Your turn also end when you hit the end turn button or have" +
                " no more actions to perform.\n\n" +
                        "Please check out the card collection before starting a game.\n\n" +
                        "Have fun - Felix and Diego!");
        textArea.setBounds(20, 20, 1200, 600);
        this.add(backButton);
        this.add(textArea);
        setBackground(Color.GRAY);
        setVisible(true);
        setOpaque(true);
        this.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!mainMenu.isInTutorial()) frame.changeToMainMenuPanel();
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
