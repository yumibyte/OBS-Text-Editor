import jdk.nashorn.internal.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GameBase {

    public static class GameBaseFunctionality {

        private static FileWriter file;

        public static GamePanel gamePanel;
        public static String gameType = "";
        private int isSortByPlayerTypeSelected = 0;
        public static int numberOfPlayers = 0;
        public String[] availablePlayers = new String[numberOfPlayers];


        // sorting/search functions
        public ArrayList<String> parseRoster(String gameType) {
            //
            ArrayList<String> roster = new ArrayList<String>();

            return roster;
        }

        public void generateStream() {

            // determine title based on game type
            System.out.println("generated stream!");

        }

        public ArrayList<String> sortAlphabetically(ArrayList<String> availablePlayers) {
            ArrayList<String> x = new ArrayList<String>();

            return x;
        }

        public ArrayList<String> sortByPlayer(ArrayList<String> availablePlayers) {
            ArrayList<String> x = new ArrayList<String>();

            return x;
        }
    }


}
