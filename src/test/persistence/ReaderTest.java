package persistence;

import exceptions.NegativePositionException;
import exceptions.NegativeScoreException;
import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {
    private static final String GAME_FILE = "./data/score.txt";
    private Writer testWriter;
    private Player p1;
    private Player p2;

    Reader r = new Reader();

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
       try {
           testWriter = new Writer(new File(GAME_FILE));
           p1 = new Player(0, 10);
           p2 = new Player(4, 8);
       } catch (NegativePositionException e) {
           fail();
       }

    }

    @Test
    void testWritePlayers() {
        p1.changeScissors();
        p1.addScore();

        testWriter.write(p1);
        testWriter.write(p2);
        testWriter.close();

        try {
            List<Integer> players = Reader.readGame(new File(GAME_FILE));
            p1.setScore(players.get(0));
            assertEquals(1, p1.getScore());

            p2.setScore(players.get(1));
            assertEquals(0, p2.getScore());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        } catch (NegativeScoreException e) {
            fail();
        }
    }

}
