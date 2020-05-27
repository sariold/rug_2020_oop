package nl.rug.oop.cardgame.view.panel;

import nl.rug.oop.cardgame.controller.button.MainMenuButton;
import nl.rug.oop.cardgame.model.menu.MainMenu;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Tutorial panel
 */
public class TutorialPanel extends JPanel implements Observer {

    private final MainMenu mainMenu;
    private final MagicStoneFrame frame;
    private final JTextArea textArea;

    /**
     * Tutorial panel constructor
     * @param mainMenu main menu class
     * @param frame frame
     */
    public TutorialPanel(MainMenu mainMenu, MagicStoneFrame frame) {
        this.mainMenu = mainMenu;
        this.mainMenu.addObserver(this);
        this.frame = frame;
        textArea = new JTextArea();
        MainMenuButton backButton = new MainMenuButton(mainMenu);
        backButton.setBounds(20, 630, 200, 50);
        textArea.setEditable(false);
        textArea.setText("GENERAL:\n" +
                "\n" +
                "Your goal is to reduce the enemy’s health to 0. \n" +
                "\n" +
                "Your deck consists of creatures and spells.\n" +
                "After each turn a new card from the deck will be drawn. \n" +
                "\n" +
                "Cards can be used depending on the amount of mana you have. Mana increases by 1 after every turn until a maximum of 10 mana has been reached.\n" +
                "Each card costs a certain amount of mana, which can be found in the bottom half of the card.\n" +
                "\n" +
                "When a card is playable, the card will have a green outline. These cards can then be played by clicking them and then clicking one of the free spots on the battlefield. \n" +
                "\n" +
                "Unwanted cards can be discarded by clicking the card and clicking the discard pile. Used cards will also be placed in this location.\n" +
                "\n" +
                "As mentioned previously, there are two types of cards, creatures and spells. Creatures have a health and an attack value; spells only have an attack value that can harm the enemy or the enemy’s creatures. \n" +
                "\n" +
                "For more information on how to play spells go to the Card Collection.\n" +
                "\n" +
                "ATTACK PHASE:\n" +
                "\n" +
                "After you are done placing your cards on your battlefield, you can enter the attack phase by clicking the attack button.\n" +
                "\n" +
                "Creatures are only allowed to attack the enemy and their creatures, if your creatures were not placed during this turn and have not been used to attack during this turn.  \n" +
                "\n" +
                "Creatures will attack the enemy creature that is found in the slot across from them. If there is no opposing creature, they will attack the enemy hero.\n" +
                "\n" +
                " Creatures permanently lose health according to the attack value of who they attack. If the health drops to 0 they die.\n" +
                "\n" +
                "END OF TURN:\n" +
                "\n" +
                "After your attack phase ends, your turn ends.\n" +
                " Your turn can also end when you hit the end turn button or do not have any more actions to perform.\n" +
                "\n" +
                "Please check out the card collection before starting a game.\n" +
                "\n" +
                "Have fun - Felix and Diego!");
        textArea.setBounds(20, 20, 1200, 600);
        this.add(backButton);
        this.add(textArea);
        setBackground(Color.GRAY);
        setVisible(true);
        setOpaque(true);
        this.setLayout(null);
    }

    /**
     * Paint components
     * @param g graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!mainMenu.isInTutorial()) frame.changeToMainMenuPanel();
    }

    /**
     * Update function
     * @param o observerable
     * @param arg arguments
     */
    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
