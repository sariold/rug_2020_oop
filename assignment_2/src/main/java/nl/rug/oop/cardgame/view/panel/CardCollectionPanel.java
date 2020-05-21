package nl.rug.oop.cardgame.view.panel;

import nl.rug.oop.cardgame.controller.button.MainMenuButton;
import nl.rug.oop.cardgame.controller.button.PageButton;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.model.card.EnumCard;
import nl.rug.oop.cardgame.model.deck.CollectionDeck;
import nl.rug.oop.cardgame.model.menu.MainMenu;
import nl.rug.oop.cardgame.util.CardDescriptions;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;
import nl.rug.oop.cardgame.view.textures.CardTextures;
import nl.rug.oop.cardgame.view.textures.CollectionTextures;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class CardCollectionPanel extends JPanel implements Observer {

    private MainMenu mainMenu;
    private MagicStoneFrame frame;
    private JTextArea textField1;
    private JTextArea textField2;
    private JTextArea textField3;
    private JTextArea textField4;

    public CardCollectionPanel(MainMenu mainMenu, MagicStoneFrame frame) {
        this.frame = frame;
        this.mainMenu = mainMenu;
        MainMenuButton backButton = new MainMenuButton(mainMenu);
        backButton.setBounds(20, 630, 200, 50);
        PageButton nextPageButton = new PageButton(mainMenu, "Next Page");
        PageButton prevPageButton = new PageButton(mainMenu, "Previous Page");
        nextPageButton.setBounds(1050, 630, 200, 50);
        prevPageButton.setBounds(850, 630, 200, 50);
        textField1 = new JTextArea();
        textField2 = new JTextArea();
        textField3 = new JTextArea();
        textField4 = new JTextArea();
        textField1.setBounds(10, 430, 300, 200);
        textField2.setBounds(315, 430, 300, 200);
        textField3.setBounds(620, 430, 300, 200);
        textField4.setBounds(925, 430, 300, 200);

        this.add(textField1);
        this.add(textField2);
        this.add(textField3);
        this.add(textField4);
        this.add(backButton);
        this.add(nextPageButton);
        this.add(prevPageButton);
        setBackground(Color.GRAY);
        setVisible(true);
        setOpaque(true);
        this.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!mainMenu.isInCollection()) frame.changeToMainMenuPanel();
        paintCurrentCard(g);
    }

    private void paintCurrentCard(Graphics g) {
        CollectionDeck deck = mainMenu.getCollectionDeck();
        int count = 0;
        for (int i = deck.getStartingCard(); i < deck.getDeckList().size() && i < deck.getStartingCard()+4; i++) {
            EnumCard card = deck.getDeckList().get(i).getEnumCard();
            System.out.println(card + "  " + i);
            switch (count) {
                case 0:
                    textField1.setText(CardDescriptions.CARDS[i]);
                    break;
                case 1:
                    textField2.setText(CardDescriptions.CARDS[i]);
                    break;
                case 2:
                    textField3.setText(CardDescriptions.CARDS[i]);
                    break;
                case 3:
                    textField4.setText(CardDescriptions.CARDS[i]);
                    break;
            }
            g.drawImage(CollectionTextures.getTexture(card), 10+count*305, 10, 300, 418, this);
            count++;
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
