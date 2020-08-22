package ui;

import model.RpsGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// represents the main window in which the game is played
public class RockPaperScissors extends JFrame {

    private static final int INTERVAL = 10;
    private RpsGame game;
    private GamePanel gp;
    private ScorePanel sp;

    //EFFECTS; sets up the window in which the game will be played
    public RockPaperScissors() {
        setTitle("ROCK PAPER SCISSORS");

        ImageIcon icon = new ImageIcon("data/images/icon.png");


        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);

        game = new RpsGame();
        gp = new GamePanel(game);
        sp = new ScorePanel(game);

        add(gp);
        add(sp, BorderLayout.NORTH);
        addKeyListener(new KeyHandler());
        pack();
        centreOnScreen();
        setVisible(true);
        setResizable(false);
        addTimer();
    }

    //EFFECTS: responds to user input
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            game.keyPressed(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            game.keyReleased(e.getKeyCode());
        }
    }

    // MODIFIES: this
    //EFFECTS: sets up the window so its centred on the desktop
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    //EFFECTS: initializes a timer that updates the game per INTERVAL milliseconds
    private void addTimer() {
        Timer t = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (game.getInGame()) {
                    game.move();
                    game.update();
                    gp.repaint();
                    sp.update();
                }
                sp.update();
            }
        });

        t.start();
    }

    // plays the game
    public static void main(String[] args) {
        new RockPaperScissors();
    }

    //CREDITS TO SPACEINVADERSTARTER
}
