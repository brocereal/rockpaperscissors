package ui;

import exceptions.NegativeScoreException;
import model.Player;
import model.RpsGame;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

//CREDITS SPACEINVADERSSTARTER
// panel where game is rendered
public class GamePanel extends JPanel {

    private RpsGame game;

    //EFFECTS: sets up the size and background colour of panel
    public GamePanel(RpsGame g) {
        setPreferredSize(new Dimension(RpsGame.WIDTH, RpsGame.HEIGHT));
        setBackground(Color.LIGHT_GRAY);
        createButton();
        this.game = g;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPlayers(g);

        if (game.getGameOver()) {
            gameOver(g);
        }

    }

    //EFFECTS: draws game over string
    private void gameOver(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 40));

        if (game.getP1().getScore() >= game.winScore) {
            g.drawString("PLAYER 1 WINS", (game.WIDTH / 3), game.HEIGHT / 2);
        } else if (game.getP2().getScore() >= game.winScore) {
            g.drawString("PLAYER 2 WINS", (game.WIDTH / 3), game.HEIGHT / 2);
        }

    }

    //MODIFIES: g
    //EFFECTS: draws the players onto g
    private void drawPlayers(Graphics g) {
        for (Player p : game.getPlayers()) {
            drawPlayers(g, p);
        }
    }

    //MODIFIES: g
    //EFFECTS: draws the player p onto g
    private void drawPlayers(Graphics g, Player p) {
        g.drawImage(p.getImage(), p.getX(), p.getY(), this);
    }

    //EFFECTS: creates a pause button
    private void createButton() {
        JPanel pause = new JPanel();
        pause.setLayout(new GridLayout(0, 1));
        pause.setSize(new Dimension(0, 0));
        add(pause, BorderLayout.NORTH);

        PauseAction pausebutton = new PauseAction(this, pause);
    }

    public void setInGame(boolean inGame) {
        game.setInGame(inGame);
    }

    //CREDITS TELLERAPP
    //MODIFIES: RpsGame
    //EFFECTS: saves the score
    public void saveGame() {
        try {
            game.saveGame();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.out.println("cant save");
        }
    }

    //MODIFIES: RpsGame
    //EFFECTS: loads the score
    public void loadGame() {
        try {
            game.loadGame();
        } catch (IOException e) {
            System.out.println("cant load");
        }
    }


    public void setGameOver(boolean gameOver) {
        game.setGameOver(gameOver);
    }

    public void setInitialScore() {
        try {
            game.getP1().setScore(0);
            game.getP2().setScore(0);
        } catch (NegativeScoreException e) {
            System.out.println("Score cant be negative");
        }
    }
}
