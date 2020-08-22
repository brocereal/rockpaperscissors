package model;

import exceptions.NegativePositionException;
import exceptions.NegativeScoreException;
import persistence.Saveable;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;

// Represents a player with its speed, x and y positions and score
public class Player implements Saveable {
    protected int speed;
    private int xpos;
    private int ypos;
    private int score;
    private String state;
    private Image image;
    private int width;
    private int height;
    private int dx;
    private int dy;

    public static final int PAPERSPEED = 5;
    public static final int ROCKSPEED = 5;
    public static final int SCISSORSPEED = 5;

    // EFFECTS: initializes Player
    public Player(int x, int y) throws NegativePositionException {
        if (x < 0 || y < 0) {
            throw new NegativePositionException();
        }
        xpos = x;
        ypos = y;
        score = getScore();
        changePaper();
    }

    //MODIFIES: this
    //EFFECTS: loads the image of paper and gets its height and width
    private void loadImagePaper() {
        ImageIcon ii = new ImageIcon("data/images/paper.png");
        image = ii.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);

    }

    //MODIFIES: this
    //EFFECTS: changes speed, state and image of player to the properties of paper
    public void changePaper() {
        this.speed = PAPERSPEED;
        this.state = "PAPER";
        loadImagePaper();
    }

    //MODIFIES: this
    //EFFECTS: changes speed, state and image of player to the properties of rock
    public void changeRock() {
        this.speed = ROCKSPEED;
        this.state = "ROCK";
        loadImageRock();
    }

    //CREDITS http://zetcode.com/tutorials/javagamestutorial/
    //MODIFIES: this
    //EFFECTS: loads the image of rock and gets its height and width
    private void loadImageRock() {
        ImageIcon ii = new ImageIcon("data/images/rock.png");
        image = ii.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);

    }

    //MODIFIES: this
    //EFFECTS: changes speed, state and image of player to the properties of scissors
    public void changeScissors() {
        this.speed = SCISSORSPEED;
        this.state = "SCISSORS";
        loadImageScissors();
    }

    //MODIFIES: this
    //EFFECTS: loads the image of scissors and gets its height and width
    private void loadImageScissors() {
        ImageIcon ii = new ImageIcon("data/images/scissors.png");
        image = ii.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);

    }

    //MODIFIES: this
    //EFFECTS: add 1 to score
    public void addScore() {
        this.score = score + 1;
    }

    public int getX() {
        return xpos;
    }

    public int getY() {
        return ypos;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public int getScore() {
        return score;
    }

    public int getSpeed() {
        return speed;
    }

    public String getState() {
        return state;
    }

    public void setXpos(int xpos) throws NegativePositionException {
        if (xpos < 0) {
            throw new NegativePositionException();
        }
        this.xpos = xpos;
    }

    public void setYpos(int ypos) throws NegativePositionException {
        if (ypos < 0) {
            throw new NegativePositionException();
        }
        this.ypos = ypos;
    }

    public Image getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setDx(int x) {
        this.dx = x;
    }

    public void setDy(int y) {
        this.dy = y;
    }

    public Rectangle getBounds() {
        return new Rectangle(xpos, ypos, width, height);
    }

    public void setScore(int score) throws NegativeScoreException {
        if (score < 0) {
            throw new NegativeScoreException();
        }
        this.score = score;
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(score);
        printWriter.println();
    }
    //CREDITS TO TELLER APP

}
