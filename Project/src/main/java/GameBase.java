import java.util.ArrayList;

public class GameBase {

    public static class GameBaseFunctionality {

        public static GamePanel gamePanel;
        public String title = "";
        public String gameType = "";
        private int isSortByPlayerTypeSelected = 0;
        public int numberOfPlayers = 0;
        public String[] availablePlayers = new String[numberOfPlayers];



        // sorting/search functions
        public ArrayList<String> parseRoster(String gameType) {
            //
            ArrayList<String> roster = new ArrayList<String>();

            return roster;
        }

        public String generateTitle(String gameType) {
            return "";
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
