package persistence;

import model.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//read score data from a file
public class Reader {
    public static final String COMMA = ",";

    //EFFECT: return a list of Players from file
    public static List<Integer> readGame(File file) throws IOException {
        List<String> filecontent = readPlayers(file);
        return parsedContent(filecontent);
    }

    //EFFECT: returns content of file as a list of string each containing one row
    private static List<String> readPlayers(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    //EFFECT: returns a list of Players from list of string where each string contains data for one Player
    private static List<Integer> parsedContent(List<String> fileContent) {
        List<Integer> scores = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            scores.add(parsePlayer(lineComponents));
        }
        return scores;
    }

    //EFFECT: returns a list of string from splitting line on COMMA
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(COMMA);
        return new ArrayList<>(Arrays.asList(splits));
    }

    //REQUIRES: components has size 3 where element 0 represents xpos of player,
    //element 1 represents ypos of player, element 2 represents its score,
    //element 3 represents speed of player
    //EFFECTS: returns an account constructed from components
    private static int parsePlayer(List<String> components) {
        int score = Integer.parseInt(components.get(0));

        return score;
    }
}

//EVERYTHING CREDITS TO TELLERAPP
