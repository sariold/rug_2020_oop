package nl.rug.oop.cardgame.view.panel;

import nl.rug.oop.cardgame.controller.clicker.CardClicker;
import nl.rug.oop.cardgame.controller.button.AttackPhaseButton;
import nl.rug.oop.cardgame.controller.button.EndTurnButton;
import nl.rug.oop.cardgame.util.DefaultCoordinates;
import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.model.card.CreatureCard;
import nl.rug.oop.cardgame.model.card.EnumCard;
import nl.rug.oop.cardgame.model.card.SpellCard;
import nl.rug.oop.cardgame.model.deck.DeckHand;
import nl.rug.oop.cardgame.model.hero.Hero;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;
import nl.rug.oop.cardgame.view.textures.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MagicStonePanel extends JPanel implements Observer {

    private final MagicStoneGame magicStoneGame;
    private MagicStoneFrame frame;
    private static final Color BACKGROUND_COLOR = new Color(0xa3, 0xa3, 0xa3);

    public MagicStonePanel(MagicStoneGame magicStoneGame, MagicStoneFrame frame) {
        this.frame = frame;
        this.magicStoneGame = magicStoneGame;
        magicStoneGame.addObserver(this);
        magicStoneGame.getBattlefield().addObserver(this);
        magicStoneGame.getBattlefield().getPlayer().addObserver(this);
        magicStoneGame.getBattlefield().getAi().addObserver(this);

        CardClicker clicker = new CardClicker(magicStoneGame, this);
        EndTurnButton endTurnButton = new EndTurnButton(magicStoneGame);
        endTurnButton.setBounds(590, 510, 100, 30);
        AttackPhaseButton attackPhaseButton = new AttackPhaseButton(magicStoneGame, clicker);
        attackPhaseButton.setBounds(590, 108, 100, 30);
        this.add(attackPhaseButton);
        this.add(endTurnButton);

        setBackground(BACKGROUND_COLOR);
        setVisible(true);
        setOpaque(true);
        this.setLayout(null);
    }

    private void paintAreas(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString("Card 1", 117, 90);
        g.drawString("Card 2", 267, 90);
        g.drawString("Card 3", 417, 90);
        g.drawString("Card 4", 795, 90);
        g.drawString("Card 5", 945, 90);
        g.drawString("Card 6", 1097, 90);
        g.drawRect(10, 180, 100, 150); // Discard
        g.drawRect(1159, 180, 100, 150); // Deck
        g.drawRect(100, 10, 100, 150); // Card 1
        g.drawRect(250, 10, 100, 150); // Card 2
        g.drawRect(400, 10, 100, 150); // Card 3
        g.drawRect(780, 10, 100, 150); // Card 4
        g.drawRect(930, 10, 100, 150); // Card 5
        g.drawRect(1080, 10, 100, 150); // Card 6
        g.drawRect(140, 194, 90, 135); // Played Card 1
        g.drawRect(340, 194, 90, 135); // Played Card 2
        g.drawRect(540, 194, 90, 135); // Played Card 3
        g.drawRect(740, 194, 90, 135); // Played Card 4
        g.drawRect(940, 194, 90, 135); // Played Card 5

        g.setColor(Color.WHITE);
        g.drawString("Card 1", 117, 615);
        g.drawString("Card 2", 267, 615);
        g.drawString("Card 3", 417, 615);
        g.drawString("Card 4", 795, 615);
        g.drawString("Card 5", 945, 615);
        g.drawString("Card 6", 1097, 615);

        g.drawRect(10, 360, 100, 150); // Discard
        g.drawRect(1159, 360, 100, 150); // Deck
        g.drawRect(100, 530, 100, 150); // Card 1
        g.drawRect(250, 530, 100, 150); // Card 2
        g.drawRect(400, 530, 100, 150); // Card 3
        g.drawRect(780, 530, 100, 150); // Card 4
        g.drawRect(930, 530, 100, 150); // Card 5
        g.drawRect(1080, 530, 100, 150); // Card 6
        g.drawRect(140, 360, 90, 135); // Played Card 1
        g.drawRect(340, 360, 90, 135); // Played Card 2
        g.drawRect(540, 360, 90, 135); // Played Card 3
        g.drawRect(740, 360, 90, 135); // Played Card 4
        g.drawRect(940, 360, 90, 135); // Played Card 5
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (magicStoneGame.isLost()) frame.gameOver(false);
        if (magicStoneGame.isWon()) frame.gameOver(true);
        paintAreas(g);
        paintHeros(g);
        paintDiscardPile(g);
        paintDeck(g);
        paintHand(g);
        paintBattlefield(g);
        if(magicStoneGame.getBattlefield().isPlayerTurn() && ((magicStoneGame.getBattlefield().getSelectedCard() != null
        && magicStoneGame.getBattlefield().getSelectedCard() instanceof CreatureCard) || magicStoneGame.getBattlefield().isAttackPhase())) {
            paintPositions(g);
        }
        paintSelected(g);
    }

    /**
     * Paint the discard piles
     *
     * @param g Graphics
     */
    private void paintDiscardPile(Graphics g) {
        ArrayList<Card> playerDiscardPile = magicStoneGame.getBattlefield().getPlayer().getDiscardDeck().getDicardPile();
        ArrayList<Card> aiDiscardPile = magicStoneGame.getBattlefield().getAi().getDiscardDeck().getDicardPile();
        Card card;
        if (playerDiscardPile.size() > 0) {
            card = playerDiscardPile.get(playerDiscardPile.size()-1);
            card.getCardImage().setCoordinates(DefaultCoordinates.PLAYER_DISCARD_PILE);
            renderCard(card, g);
        }
        if (aiDiscardPile.size() > 0) {
            card = aiDiscardPile.get(aiDiscardPile.size()-1);
            card.getCardImage().setCoordinates(DefaultCoordinates.AI_DISCARD_PILE);
            renderCard(card, g);
        }
    }

    /**
     * Paints both heros on the battlefield
     *
     * @param g Grpahics
     */
    private void paintHeros(Graphics g) {
        Battlefield battlefield = magicStoneGame.getBattlefield();
        g.setColor(Color.RED);
        g.drawRect(0, 0, getWidth() - 1, getHeight() / 2); // Red Half
        g.fillArc(540, -100, 200, 200, 180, 180); // AI Arc
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.setColor(Color.BLACK);
        g.drawString("Discard", 23, 263);
        g.drawString("Deck", 1183, 263);
        g.drawString("Mana " + battlefield.getAi().getMana() + "/" + battlefield.getAi().getMaxMana(),
                (getWidth() / 2) - 40, 158);
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawImage(StatTextures.getTexture(StatEnum.HEART_BLUE), 570, 17, 55, 55, this);
        g.drawString(Integer.toString(battlefield.getAi().getHealth()),
                600 - g.getFontMetrics().stringWidth(Integer.toString(battlefield.getAi().getHealth())) / 2, 48);
        g.drawImage(StatTextures.getTexture(StatEnum.ATTACK_BLUE), 655, 5, 60, 67, this);
        g.drawString(Integer.toString(battlefield.getAi().getAttack()),
                685 - g.getFontMetrics().stringWidth(Integer.toString(battlefield.getAi().getAttack())) / 2, 50);

        g.setColor(Color.BLUE);
        g.drawRect(0, getHeight() / 2, getWidth() - 1, getHeight() / 2); // Blue Half
        g.fillArc(540, 590, 200, 200, 0, 180); // Player Arc
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.setColor(Color.BLACK);
        g.drawString("Discard", 23, 445);
        g.drawString("Deck", 1183, 445);
        g.drawString("Mana " + battlefield.getPlayer().getMana() + "/" + battlefield.getPlayer().getMaxMana(),
                (getWidth() / 2) - 40, 565);
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawImage(StatTextures.getTexture(StatEnum.HEART), 570, 630, 55, 55, this);
        g.drawString(Integer.toString(battlefield.getPlayer().getHealth()),
                600 - g.getFontMetrics().stringWidth(Integer.toString(battlefield.getPlayer().getHealth())) / 2, 665);
        g.drawImage(StatTextures.getTexture(StatEnum.ATTACK), 655, 625, 60, 67, this);
        g.drawString(Integer.toString(battlefield.getPlayer().getAttack()),
                685 - g.getFontMetrics().stringWidth(Integer.toString(battlefield.getPlayer().getAttack())) / 2, 669);
    }

    /**
     * paint both players battlefields
     *
     * @param g Grpahics
     */
    private void paintBattlefield(Graphics g) {
        ArrayList<CreatureCard> playerCreatures = magicStoneGame.getBattlefield().getPlayer().getPlayedCreatures();
        ArrayList<CreatureCard> aiCreatures = magicStoneGame.getBattlefield().getAi().getPlayedCreatures();
        int i = 0;
        for (CreatureCard c : playerCreatures) {
            if (c == null) {
                i++;
                continue;
            }
            c.getCardImage().setCoordinates(DefaultCoordinates.PLAYER_BATTLEFIELD[i]);
            renderCard(c, g);
            i++;
        }
        i = 0;
        for (CreatureCard c : aiCreatures) {
            if (c == null) {
                i++;
                continue;
            }
            c.getCardImage().setCoordinates(DefaultCoordinates.AI_BATTLEFIELD[i]);
            renderCard(c, g);
            i++;
        }
    }

    public void paintPositions(Graphics g) {
        boolean attack = magicStoneGame.getBattlefield().isAttackPhase();
        Hero player = magicStoneGame.getBattlefield().getPlayer();
        g.setColor(Color.GREEN);
        int xOffset = 200;
        for(int i = 0; i < 5; i++) {
            int[] coords = DefaultCoordinates.PLAYER_BATTLEFIELD[i];
            if(!attack) {
                if(player.getPlayedCreatures().get(i) == null) g.drawRect(coords[0], coords[1], coords[2], coords[3]);
            } else {
                System.out.println("ATTACK RED");
                g.setColor(Color.RED);
                if (player.getPlayedCreatures().get(i) != null) {
                    if(!player.getPlayedCreatures().get(i).isUsed()) {
                        System.out.println(player.getPlayedCreatures().get(i).toString());
                        g.drawRect(coords[0], coords[1], coords[2], coords[3]);
                    }
                }
            }
        }
    }

    public void paintSelected(Graphics g) {
        Card c = magicStoneGame.getBattlefield().getSelectedCard();
        if (c != null) {
            int[] coords = c.getCardImage().getCoordinates();
            g.setColor(Color.BLUE);
            g.drawRect(coords[0], coords[1], coords[2], coords[3]);
            coords = DefaultCoordinates.PLAYER_DISCARD_PILE;
            g.setColor(Color.RED);
            g.drawRect(coords[0], coords[1], coords[2], coords[3]);
        }
    }

    /**
     * Ppaint both players hands
     *
     * @param g Graphics
     */
    public void paintHand(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        DeckHand playerHand = magicStoneGame.getBattlefield().getPlayer().getDeckHand();
        DeckHand aiHand = magicStoneGame.getBattlefield().getAi().getDeckHand();
        CardBack redBack = CardBack.RED_BACK;
        int i = 0;
        for (Card c : playerHand.getDeckHand().values()) {
            c.getCardImage().setCoordinates(DefaultCoordinates.PLAYER_HAND[i]);
            Color color = (c.getCost() <= magicStoneGame.getBattlefield().getPlayer().getMana()?Color.GREEN:Color.RED);
            playerHand.getDeckHand().get(c.getCardNumber()).setHandPos(i);
            renderCard(c, g);
            if(magicStoneGame.getBattlefield().isAttackPhase()) color = Color.WHITE;
            g.setColor(color);
            g.drawRect(c.getCardImage().getCoordinates()[0], c.getCardImage().getCoordinates()[1],
                    c.getCardImage().getCoordinates()[2], c.getCardImage().getCoordinates()[3]);
            i++;
        }
        i = 0;
        for (Card c : aiHand.getDeckHand().values()) {
            int[] coords = DefaultCoordinates.AI_HAND[i];
            g.drawImage(CardBackTextures.getTexture(redBack), coords[0], coords[1], coords[2], coords[3], this);
            i++;
        }
    }

    /**
     * paints the deck for both players
     *
     * @param g Graphics
     */
    private void paintDeck(Graphics g) {
        int playerCards = magicStoneGame.getBattlefield().getPlayer().getDeck().getDeckList().size();
        int aiCards = magicStoneGame.getBattlefield().getAi().getDeck().getDeckList().size();
        CardBack redBack = CardBack.RED_BACK;
        CardBack blueBack = CardBack.BLUE_BACK;
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.setColor(Color.BLACK);
        if (playerCards > 0) {
            int[] coords = DefaultCoordinates.PLAYER_DECK;
            g.drawImage(CardBackTextures.getTexture(blueBack), coords[0], coords[1], coords[2], coords[3], this);
        }
        g.drawString(Integer.toString(playerCards), 1190, 440);
        if (aiCards > 0) {
            int[] coords = DefaultCoordinates.AI_DECK;
            g.drawImage(CardBackTextures.getTexture(redBack), coords[0], coords[1], coords[2], coords[3], this);
        }
        g.drawString(Integer.toString(aiCards), 1190, 260);
    }

    @Override
    public void update(Observable observed, Object message) {
        repaint();
    }

    private void renderCard(Card c, Graphics g) {
        int[] coords = c.getCardImage().getCoordinates();
        g.drawImage(c.getCardImage().getImage(), coords[0],coords[1],coords[2],coords[3], this);
        if (c.isDiscarded()) return;
        if (c.getEnumCard().getType() == EnumCard.Type.CREATURE) {
            CreatureCard creature = (CreatureCard)c;
            if (creature.getBattlePosition() == -1) {
                g.setColor(Color.BLACK);
                String attackAndHealth = (creature.getAttack() + "/" + (creature.getHealth()));
                g.drawString(attackAndHealth, coords[0] + 130 -
                        g.getFontMetrics().stringWidth(attackAndHealth), 670);
            } else if (creature.getBattlePosition() > -1){
                g.setColor(Color.WHITE);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                g.drawImage(StatTextures.getTexture(StatEnum.ATTACK), coords[0] + 103, coords[1] + 10, 50, 65, this);
                g.drawString(Integer.toString(creature.getAttack()), coords[0] + 120, coords[1] + 55);
                g.drawImage(StatTextures.getTexture(StatEnum.HEART), coords[0] + 104, coords[1] + 90, 45, 45, this);
                g.drawString(Integer.toString(creature.getHealth()), coords[0] + 122, coords[1] + 115);
            }
        } else {
            SpellCard spell = (SpellCard)c;
            g.setColor(Color.BLACK);
            String value = String.valueOf(spell.getEnumCard().getValue());
            g.drawString(value, spell.getCardImage().getCoordinates()[0] + 130 - g.getFontMetrics().stringWidth(value),
                    670);
        }
    }

}
