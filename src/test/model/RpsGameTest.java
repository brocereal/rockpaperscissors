package model;

import exceptions.NegativePositionException;
import exceptions.NegativeScoreException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class RpsGameTest {
    RpsGame g1;
    private int INITIALX1 = 200;
    private int INITIALX2 = 650;
    private int INITIALY = 150;

    @BeforeEach
    void runBefore() {
        g1 = new RpsGame();
    }

    @Test
    void testConstructor() {
        Player p1 = g1.getP1();
        Player p2 = g1.getP2();
        assertEquals(INITIALX1, p1.getX());
        assertEquals(INITIALX2, p2.getX());
        assertEquals(INITIALY, p1.getY());
        assertEquals(INITIALY, p2.getY());

        ArrayList<Player> players = g1.getPlayers();
        assertEquals(2, players.size());
    }

    @Test
    void testMovingPlayers() {
        Player p1 = g1.getP1();
        Player p2 = g1.getP2();
        g1.keyPressed(KeyEvent.VK_D);
        g1.move();
        g1.keyReleased(KeyEvent.VK_D);
        assertEquals(INITIALX1 + p1.getSpeed(), p1.getX());

        g1.keyPressed(KeyEvent.VK_W);
        g1.move();
        g1.keyReleased(KeyEvent.VK_W);
        assertEquals(INITIALY - p1.getSpeed(), p1.getY());

        g1.keyPressed(KeyEvent.VK_A);
        g1.move();
        g1.keyReleased(KeyEvent.VK_A);
        assertEquals(INITIALX1, p1.getX());

        g1.keyPressed(KeyEvent.VK_S);
        g1.move();
        g1.keyReleased(KeyEvent.VK_S);
        assertEquals(INITIALY, p1.getY());

        //MOVING FOR PLAYER 2
        g1.keyPressed(KeyEvent.VK_RIGHT);
        g1.move();
        g1.keyReleased(KeyEvent.VK_RIGHT);
        assertEquals(INITIALX2 + p2.getSpeed(), p2.getX());

        g1.keyPressed(KeyEvent.VK_UP);
        g1.move();
        g1.keyReleased(KeyEvent.VK_UP);
        assertEquals(INITIALY - p2.getSpeed(), p2.getY());

        g1.keyPressed(KeyEvent.VK_LEFT);
        g1.move();
        g1.keyReleased(KeyEvent.VK_LEFT);
        assertEquals(INITIALX2, p2.getX());

        g1.keyPressed(KeyEvent.VK_DOWN);
        g1.move();
        g1.keyReleased(KeyEvent.VK_DOWN);
        assertEquals(INITIALY, p2.getY());

    }

    @Test
    void testChangingState() {
        Player p1 = g1.getP1();
        Player p2 = g1.getP2();
        g1.keyPressed(KeyEvent.VK_C);
        assertEquals(Player.ROCKSPEED, p1.getSpeed());
        g1.keyPressed(KeyEvent.VK_V);
        assertEquals(Player.PAPERSPEED, p1.getSpeed());
        g1.keyPressed(KeyEvent.VK_B);
        assertEquals(Player.SCISSORSPEED, p1.getSpeed());

        g1.keyPressed(KeyEvent.VK_COMMA);
        assertEquals(Player.ROCKSPEED, p2.getSpeed());
        g1.keyPressed(KeyEvent.VK_PERIOD);
        assertEquals(Player.PAPERSPEED, p2.getSpeed());
        g1.keyPressed(KeyEvent.VK_SLASH);
        assertEquals(Player.SCISSORSPEED, p2.getSpeed());

    }

    @Test
    void testBoundaries() {
        try {
            Player p1 = g1.getP1();
            Player p2 = g1.getP2();

            p1.setXpos(0);
            g1.keyPressed(KeyEvent.VK_A);
            g1.move();
            g1.keyReleased(KeyEvent.VK_A);
            assertEquals(0, p1.getX());

            p2.setYpos(0);
            g1.keyPressed(KeyEvent.VK_UP);
            g1.move();
            g1.keyReleased(KeyEvent.VK_UP);
            assertEquals(0, p2.getY());

            p1.setXpos(g1.WIDTH - p1.getWidth());
            g1.keyPressed(KeyEvent.VK_D);
            g1.move();
            g1.keyReleased(KeyEvent.VK_D);
            assertEquals(g1.WIDTH - p1.getWidth(), p1.getX());

            p2.setYpos(g1.HEIGHT - p2.getHeight());
            g1.keyPressed(KeyEvent.VK_DOWN);
            g1.move();
            g1.keyReleased(KeyEvent.VK_DOWN);
            assertEquals(g1.HEIGHT - p2.getHeight(), p2.getY());
        } catch (NegativePositionException e) {
            fail();
        }
    }

    @Test
    void testTie() {
        try {
            Player p1 = g1.getP1();
            Player p2 = g1.getP2();

            p1.setXpos(0);
            p1.setYpos(0);
            p2.setXpos(0);
            p2.setYpos(0);
            g1.update();

            assertEquals("0", g1.getScore1());
            assertEquals("0", g1.getScore2());
            assertEquals(INITIALX1, p1.getX());
            assertEquals(INITIALX2, p2.getX());
            assertEquals(INITIALY, p1.getY());
            assertEquals(INITIALY, p2.getY());
        } catch (NegativePositionException e) {
            fail();
        }
    }

    @Test
    void testWhoWon() {
        try {
            Player p1 = g1.getP1();
            Player p2 = g1.getP2();

            assertEquals(0, p1.getScore());
            assertEquals(0, p2.getScore());

            //p1: paper p2: scissors
            p2.changeScissors();
            p1.setXpos(0);
            p1.setYpos(0);
            p2.setXpos(0);
            p2.setYpos(0);
            g1.update();
            assertEquals(1, p2.getScore());

            //p1: paper p2: rock
            p2.changeRock();
            p1.setXpos(0);
            p1.setYpos(0);
            p2.setXpos(0);
            p2.setYpos(0);
            g1.update();
            assertEquals(1, p1.getScore());

            //p1: scissors p2: paper
            p1.changeScissors();
            p2.changePaper();
            p1.setXpos(0);
            p1.setYpos(0);
            p2.setXpos(0);
            p2.setYpos(0);
            g1.update();
            assertEquals(2, p1.getScore());

            //p1: scissors p2: rock
            p2.changeRock();
            p1.setXpos(0);
            p1.setYpos(0);
            p2.setXpos(0);
            p2.setYpos(0);
            g1.update();
            assertEquals(2, p2.getScore());

            //p1: rock p2: paper
            p1.changeRock();
            p2.changePaper();
            p1.setXpos(0);
            p1.setYpos(0);
            p2.setXpos(0);
            p2.setYpos(0);
            g1.update();
            assertEquals(3, p2.getScore());

            //p1: rock p2: scissors
            p2.changeScissors();
            p1.setXpos(0);
            p1.setYpos(0);
            p2.setXpos(0);
            p2.setYpos(0);
            g1.update();
            assertEquals(3, p1.getScore());

            assertEquals(INITIALX1, p1.getX());
            assertEquals(INITIALX2, p2.getX());
            assertEquals(INITIALY, p1.getY());
            assertEquals(INITIALY, p2.getY());
        } catch (NegativePositionException e) {
            fail();
        }
    }

    @Test
    void testGameOver() {
        try {
            Player p1 = g1.getP1();
            Player p2 = g1.getP2();

            p1.setScore(5);
            g1.update();
            assertFalse(g1.getInGame());
            assertTrue(g1.getGameOver());

            g1.setInGame(true);
            assertTrue(g1.getInGame());
            p1.setScore(0);
            g1.update();

            p2.setScore(5);
            g1.update();
            assertFalse(g1.getInGame());
            assertTrue(g1.getGameOver());

            g1.setGameOver(false);
            assertFalse(g1.getGameOver());
        } catch (NegativeScoreException e) {
            fail();
        }
    }

    @Test
    void testLoadAndSave() {
        try {
            g1.saveGame();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            fail();
        }
        try {
            g1.loadGame();
        } catch (IOException e) {
            fail();
        }
    }
}
