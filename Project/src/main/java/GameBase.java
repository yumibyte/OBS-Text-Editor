import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;


import org.json.JSONException;
import org.json.JSONObject;

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
        public String title = "";
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

        public void generateStream(String gameType) {

            try {
                String content = new String(Files.readAllBytes(Paths.get("/Users/ashleyraigosa/Desktop/Programming/OBSTextEditor/Project/src/main/java/EsportsTest.json")));


                JSONObject j = new JSONObject(content);

                String k = j.getString("sources");
                k = k.replace("sample text", "test");

                j.put("sources", k);


                System.out.println(j.getString("sources"));


            } catch
            (FileNotFoundException e) {e.printStackTrace();} catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


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
