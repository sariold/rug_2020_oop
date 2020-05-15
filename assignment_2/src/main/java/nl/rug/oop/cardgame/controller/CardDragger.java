package nl.rug.oop.cardgame.controller;

import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.view.MagicStonePanel;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

/**
 * Implements the ability to drag the top card of the deck to the discard
 * area of a drawpanel
 */
public class CardDragger extends MouseInputAdapter {

    private final MagicStoneGame magicStoneGame;
    private final MagicStonePanel magicStonePanel;

    /**
     * Boolean denoting whether a card is selected.
     */
    private final boolean selected;

    /**
     * Starting x position of a mousePressed. Used for dragging.
     */
    private int startX;

    /**
     * Starting y position of a mousePressed. Used for dragging.
     */
    private int startY;

    /**
     * Create a new card dragger that receives mouse events from the DrawPanel
     * supplied to this constructor
     *
     * @param magicStoneGame  The actual DrawGame
     * @param magicStonePanel DrawPanel needed to receive mouse events from
     */
    public CardDragger(MagicStoneGame magicStoneGame, MagicStonePanel magicStonePanel) {
        this.magicStoneGame = magicStoneGame;
        this.magicStonePanel = magicStonePanel;
        magicStonePanel.addMouseListener(this);
        magicStonePanel.addMouseMotionListener(this);
        selected = false;
    }

    /**
     * If the mouse button is pressed in the area where the top card is
     * drawn (obviously a lack of drawable cards makes this impossible)
     * that card is 'selected' so it can be dragged.
     *
     * @param event The MouseEvent needed to locate the position of the cursor
     */
//    @Override
//    public void mousePressed(MouseEvent event) {
//        if (magicStoneGame.getMovableCard() != null) {
//            if (event.getX() > magicStonePanel.getMovableX() &&
//                    event.getX() < magicStonePanel.getMovableX() + magicStonePanel.cardWidth() &&
//                    event.getY() > magicStonePanel.getMovableY() &&
//                    event.getY() < magicStonePanel.getMovableY() + magicStonePanel.cardHeight()
//            ) {
//                selected = true;
//                startX = event.getX();
//                startY = event.getY();
//            }
//        }
//    }

    /**
     * When the top card is released with the mouse in the discard square,
     * the card is moved.
     *
     * @param event The MouseEvent needed to locate the position of the cursor
     */
//    @Override
//    public void mouseReleased(MouseEvent event) {
//        if (selected) {
//            if (magicStonePanel.inDiscardArea(event.getPoint()))
//                magicStoneGame.move();
//            else {
//                magicStoneGame.getMovableCard().setRelativeX(0);
//                magicStoneGame.getMovableCard().setRelativeY(0);
//            }
//        }
//        selected = false;
//    }

    /**
     * If a card is selected it is moved relative to the positions the mouse
     * was first pressed.
     *
     * @param event The MouseEvent needed to locate the position of the cursor
     */
    @Override
    public void mouseDragged(MouseEvent event) {
        if (selected) {
            magicStoneGame.getMovableCard().setRelativeX(event.getX() - startX);
            magicStoneGame.getMovableCard().setRelativeY(event.getY() - startY);
        }
    }

}
