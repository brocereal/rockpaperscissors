package ui;

import model.RpsGame;

import javax.swing.*;
import java.awt.*;

//represents a panel in which the score is rendered
public class ScorePanel extends JPanel {

    private RpsGame game;
    private static final String PLAYER1 = "PLAYER 1 : ";
    private static final String PLAYER2 = "PLAYER 2 : ";
    private static final int LB_WIDTH = 150;
    private static final int LB_HEIGHT = 50;
    private JLabel player1Lb;
    private JLabel player2Lb;
    private Font font = new Font("", Font.BOLD, 18);

    //EFFECTS: sets up the background colour and the text of the score of the game
    public ScorePanel(RpsGame g) {
        game = g;
        setBackground(Color.LIGHT_GRAY);
        player1Lb = new JLabel(PLAYER1 + game.getScore1());
        player1Lb.setPreferredSize(new Dimension(LB_WIDTH, LB_HEIGHT));
        player1Lb.setFont(font);
        player2Lb = new JLabel(PLAYER2 + game.getScore2());
        player2Lb.setPreferredSize(new Dimension(LB_WIDTH, LB_HEIGHT));
        player2Lb.setFont(font);
        add(player1Lb);
        add(Box.createHorizontalStrut(200));
        add(player2Lb);
    }

    //MODIFIES: this
    //EFFECTS: updates the score of each player
    public void update() {
        player1Lb.setText(PLAYER1 + game.getScore1());
        player2Lb.setText(PLAYER2 + game.getScore2());
        repaint();
    }

    //CREDITS TO SPACEINVADERSTARTER
}
