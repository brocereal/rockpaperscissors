package model;

import exceptions.NegativePositionException;
import exceptions.NegativeScoreException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player p1;
    Player p2;

    @BeforeEach
    void runBefore() {
        try {
            p1 = new Player(0, 0);
        } catch (NegativePositionException e) {
            fail();
        }
    }


    @Test
    void testScore() {
        assertEquals(0, p1.getScore());
        p1.addScore();
        assertEquals(1, p1.getScore());
    }

    @Test
    void testChangeChar() {
        p1.changeScissors();
        assertEquals(p1.SCISSORSPEED, p1.getSpeed());

        p1.changePaper();
        assertEquals(p1.PAPERSPEED, p1.getSpeed());

        p1.changeRock();
        assertEquals(p1.ROCKSPEED, p1.getSpeed());
    }

    @Test
    void testSetters() {
        try {
            assertEquals(0, p1.getY());
            p1.setYpos(40);
            assertEquals(40, p1.getY());
            assertEquals("PAPER", p1.getState());
            p1.getImage();
        } catch (NegativePositionException e) {
            fail();
        }
    }

    @Test
    void testNegativeXPositionException() {
        try {
            p2 = new Player(-1, 2);
            fail();
        } catch (NegativePositionException e) {
            //expected
        }
    }

    @Test
    void testNegativeYPositionException() {
        try {
            p2 = new Player(33, -32);
            fail();
        } catch (NegativePositionException e) {
            //expected
        }
    }

    @Test
    void testNegativeYpos() {
        try {
            p1.setYpos(-3);
            fail();
        } catch (NegativePositionException e) {
            //expected
        }
    }

    @Test
    void testNegativeXpos() {
        try {
            p1.setXpos(-3);
            fail();
        } catch (NegativePositionException e) {
            //expected
        }
    }

    @Test
    void testNegativeScore() {
        try {
            p1.setScore(-44);
            fail();
        } catch (NegativeScoreException e) {
            //expected
        }
    }
}