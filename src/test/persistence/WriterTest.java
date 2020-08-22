package persistence;

import exceptions.NegativePositionException;
import exceptions.NegativeScoreException;
import model.Player;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {

    Player p1;
    Player p2;

    @Test
    void testParsePlayersFile() {
        try {
            List<Integer> players = Reader.readGame(new File("./data/testPlayers.txt"));
            p1 = new Player(10, 39);
            p1.setScore(players.get(0));
            assertEquals(3, p1.getScore());

            p2 = new Player(30, 44);
            p1.setScore(players.get(1));
            assertEquals(0, p2.getScore());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        } catch (NegativePositionException | NegativeScoreException e) {
            fail();
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readGame(new File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException e) {
        }
    }
}
