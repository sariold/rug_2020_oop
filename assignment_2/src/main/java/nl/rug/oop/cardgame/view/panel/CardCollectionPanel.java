package nl.rug.oop.cardgame.view.panel;

import nl.rug.oop.cardgame.controller.button.MainMenuButton;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.model.card.EnumCard;
import nl.rug.oop.cardgame.model.deck.CollectionDeck;
import nl.rug.oop.cardgame.model.deck.Deck;
import nl.rug.oop.cardgame.util.CardDescriptions;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;
import nl.rug.oop.cardgame.view.textures.CardTextures;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

public class CardCollectionPanel extends JPanel implements Observer {

    private CollectionDeck deck;
    private MagicStoneFrame frame;
    private JTextField textField;
    private int currentCard;

    public CardCollectionPanel(MagicStoneFrame frame) {
        this.frame = frame;
        this.deck = new CollectionDeck();
        MainMenuButton backButton = new MainMenuButton(frame);
        backButton.setBounds(20, 630, 200, 50);
        JButton nextPageButton = new JButton("Next Page");
        JButton prevPageButton = new JButton("Previous Page");
        nextPageButton.setBounds(1050, 630, 200, 50);
        prevPageButton.setBounds(850, 630, 200, 50);
        textField = new JTextField();
        textField.setBounds(10, 430, 300, 200);
        this.add(textField);
        this.add(backButton);
        this.add(nextPageButton);
        this.add(prevPageButton);
        setBackground(Color.GRAY);
        setVisible(true);
        setOpaque(true);
        this.setLayout(null);
        currentCard = 0;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintCurrentCard(g);
    }

    private void paintCurrentCard(Graphics g) {
        for (int i = currentCard; i < this.deck.getDeckList().size() && i < currentCard+4; i++) {
            Card c = deck.getDeckList().get(i);
            EnumCard card = c.getEnumCard();
            textField.setText(CardDescriptions.CARDS[i]);
            Image loaded = null;
            try {
                loaded = ImageIO.read(CardTextures.class.getResource(File.separator + "textures" + File.separator
                        + card + ".png"));
                loaded = loaded.getScaledInstance(300, 418, Image.SCALE_SMOOTH);
            } catch (IOException ioe) {
                System.err.println("Could not load!");
            }
            g.drawImage(loaded , 10+i*305, 10, 300, 418, this);
        }

    }


    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
