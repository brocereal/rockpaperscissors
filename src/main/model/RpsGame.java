package model;

import exceptions.NegativePositionException;
import exceptions.NegativeScoreException;
import persistence.Reader;
import persistence.Writer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

//represents a rock paper scissors game
public class RpsGame {

    public static final int HEIGHT = 500;
    public static final int WIDTH = 1000;
    public int winScore = 5;

    private ArrayList<Player> players;
    private Player p1;
    private Player p2;
    private static boolean inGame;
    private static boolean gameOver;
    private static final String GAME_FILE = "./data/score.txt";

    private static final int P1X = 200;
    private static final int PY = 150;
    private static final int P2X = 650;

    private static final int P2UP = KeyEvent.VK_UP;
    private static final int P2DOWN = KeyEvent.VK_DOWN;
    private static final int P2LEFT = KeyEvent.VK_LEFT;
    private static final int P2RIGHT = KeyEvent.VK_RIGHT;
    private static final int P1UP = KeyEvent.VK_W;
    private static final int P1DOWN = KeyEvent.VK_S;
    private static final int P1LEFT = KeyEvent.VK_A;
    private static final int P1RIGHT = KeyEvent.VK_D;

    //EFFECTS: creates an empty list of Players
    public RpsGame() {
        players = new ArrayList<>();
        setUp();
    }

    //MODIFIES: this
    //EFFECTS: creates 2 new players and adds them to the list
    private void setUp() {
        try {
            p1 = new Player(P1X, PY);
            p2 = new Player(P2X, PY);
            players.add(p1);
            players.add(p2);
            inGame = true;
            gameOver = false;
        } catch (NegativePositionException e) {
            System.out.println("Position cant be negative");
        }
    }

    //EFFECTS: checks for collision and game over
    public void update() {
        checkCollision();
        checkGameOver();
    }

    //CREDITS http://zetcode.com/tutorials/javagamestutorial/
    //EFFECTS: checks for if players intersect
    public void checkCollision() {
        Rectangle p1r = p1.getBounds();
        Rectangle p2r = p2.getBounds();

        if (p1r.intersects(p2r)) {
            whoWon();
            initialState();
        }
    }

    //MODIFIES: this
    //EFFECTS: checks if any player has >= winScore
    private void checkGameOver() {
        if (p1.getScore() >= winScore || p2.getScore() >= winScore) {
            gameOver = true;
            inGame = false;
        }
    }

    //MODIFIES: Player
    //EFFECTS: sets players back to the starting position of the game
    private void initialState() {
        try {
            p1.setXpos(P1X);
            p1.setYpos(PY);
            p2.setXpos(P2X);
            p2.setYpos(PY);
        } catch (NegativePositionException e) {
            System.out.println("Position can't be negative");
        }
    }

    //EFFECTS: determines if it a tie or what state player is in
    private void whoWon() {
        if (p1.getState().equals(p2.getState())) {
            //TODO7
        } else if (p1.getState().equals("ROCK")) {
            playerAsRock();
        } else if (p1.getState().equals("PAPER")) {
            playerAsPaper();
        } else if (p1.getState().equals("SCISSORS")) {
            playerAsScissors();
        }
    }

    //MODIFIES: Player
    //EFFECTS: determines who wins if player 1 is rock
    private void playerAsRock() {
        if (p1.getState().equals("ROCK")) {
            if (p2.getState().equals("PAPER")) {
                p2.addScore();
            } else {
                p1.addScore();
            }
        }
    }

    //MODIFIES: Player
    //EFFECTS: determines who wins if player 1 is paper
    private void playerAsPaper() {
        if (p1.getState().equals("PAPER")) {
            if (p2.getState().equals("SCISSORS")) {
                p2.addScore();
            } else {
                p1.addScore();
            }
        }
    }

    //MODIFIES: Player
    //EFFECTS: determines who wins if player 1 is scissors
    private void playerAsScissors() {
        if (p1.getState().equals("SCISSORS")) {
            if (p2.getState().equals("ROCK")) {
                p2.addScore();
            } else {
                p1.addScore();
            }
        }
    }


    // EFFECTS: moves players or changes players in response to given key pressed
    public void keyPressed(int keyCode) {
        movePlayer1(keyCode);
        movePlayer2(keyCode);
        changePlayer1(keyCode);
        changePlayer2(keyCode);
    }


    //MODIFIES: Player
    //EFFECTS: loads game from GAME_FILE if it doesnt exist then continue
    public void loadGame() throws IOException {
        try {
            List<Integer> player = Reader.readGame(new File(GAME_FILE));
            p1.setScore(player.get(0));
            p2.setScore(player.get(1));
        } catch (NegativeScoreException e) {
            System.out.println("Score cant be negative");
        }
    }
    //CREDITS TO TELLERAPP

    //EFFECTS: saves the current x and y position of Player and its score to GAME_FILE
    public void saveGame() throws FileNotFoundException, UnsupportedEncodingException {
        Writer writer = new Writer(new File(GAME_FILE));
        writer.write(p1);
        writer.write(p2);
        writer.close();
        System.out.println("game saved to " + GAME_FILE);

    }

    // MODIFIES: Player
    // EFFECTS: changes player 1's states based on user input
    private void changePlayer1(int keyCode) {
        if (keyCode == KeyEvent.VK_C) {
            p1.changeRock();
        } else if (keyCode == KeyEvent.VK_V) {
            p1.changePaper();
        } else if (keyCode == KeyEvent.VK_B) {
            p1.changeScissors();
        }
    }

    // MODIFIES: Player
    // EFFECTS: changes player 2's states based on user input
    private void changePlayer2(int keyCode) {
        if (keyCode == KeyEvent.VK_COMMA) {
            p2.changeRock();
        } else if (keyCode == KeyEvent.VK_PERIOD) {
            p2.changePaper();
        } else if (keyCode == KeyEvent.VK_SLASH) {
            p2.changeScissors();
        }
    }

    // MODIFIES: Player
    // EFFECTS: moves player 1 based on user input
    private void movePlayer1(int keyCode) {
        if (keyCode == P1UP) {
            p1.setDy(-p1.getSpeed());
        } else if (keyCode == P1LEFT) {
            p1.setDx(-p1.getSpeed());
        } else if (keyCode == P1DOWN) {
            p1.setDy(p1.getSpeed());
        } else if (keyCode == P1RIGHT) {
            p1.setDx(p1.getSpeed());
        }
    }

    // MODIFIES: Player
    // EFFECTS: moves player 2 based on user input
    private void movePlayer2(int keyCode) {
        if (keyCode == P2UP) {
            p2.setDy(-p2.getSpeed());
        } else if (keyCode == P2LEFT) {
            p2.setDx(-p2.getSpeed());
        } else if (keyCode == P2DOWN) {
            p2.setDy(p2.getSpeed());
        } else if (keyCode == P2RIGHT) {
            p2.setDx(p2.getSpeed());
        }
    }

    // MODIFIES: Player
    // EFFECTS: stops player 1 based on user input
    private void stopPlayer1(int keyCode) {
        if (keyCode == P1UP) {
            p1.setDy(0);
        } else if (keyCode == P1LEFT) {
            p1.setDx(0);
        } else if (keyCode == P1DOWN) {
            p1.setDy(0);
        } else if (keyCode == P1RIGHT) {
            p1.setDx(0);
        }
    }

    // MODIFIES: Player
    // EFFECTS: stops player 2 based on user input
    private void stopPlayer2(int keyCode) {
        if (keyCode == P2UP) {
            p2.setDy(0);
        } else if (keyCode == P2LEFT) {
            p2.setDx(0);
        } else if (keyCode == P2DOWN) {
            p2.setDy(0);
        } else if (keyCode == P2RIGHT) {
            p2.setDx(0);
        }
    }

    // EFFECTS: stops players based on given key released
    public void keyReleased(int keyCode) {
        stopPlayer1(keyCode);
        stopPlayer2(keyCode);
    }

    //MODIFIES: Player
    //EFFECTS: moves players based on its speed if its within the bounds of the window, otherwise keep it at the same
    //         position
    public void move() {
        try {
            for (Player p : players) {
                if (inBounds(p)) {
                    p.setXpos(p.getX() + p.getDx());
                    p.setYpos(p.getY() + p.getDy());
                } else {
                    p.setXpos(p.getX());
                    p.setYpos(p.getY());
                }
            }
        } catch (NegativePositionException e) {
            System.out.println("Position can't be negative");
        }
    }


    //EFFECTS: true if player is within the bounds of the window
    private boolean inBounds(Player p) {
        return ((p.getX() + p.getDx() >= 0 && p.getX() + p.getDx() <= (WIDTH - p.getWidth()))
                && (p.getY() + p.getDy() >= 0 && p.getY() + p.getDy() <= (HEIGHT - p.getHeight())));
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public String getScore1() {
        return Integer.toString(p1.getScore());
    }

    public String getScore2() {
        return Integer.toString(p2.getScore());
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public boolean getInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

}
