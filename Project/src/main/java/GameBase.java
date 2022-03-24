import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.internal.tool.Main;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.JSONObject;
import sun.misc.ClassLoaderUtil;

import javax.annotation.Resources;
import javax.swing.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class GameBase {

    public static class GameBaseFunctionality {



        public static String gameType = "";
        private static int isSortByPlayerTypeSelected = 0;
        public static int numberOfPlayers = 0;
        public static ArrayList<String> availablePlayers = new ArrayList<String>();
        public static ArrayList<String> availablePlayerTypes = new ArrayList<String>();

        public static ArrayList<String[]> rosterData = new ArrayList<>();

        // ensure gameBase is initialized with roster data
        { readRoster(); }

        // read roster and initialize variables
        public void readRoster() {
            // The class loader that loaded the class
            try (InputStream inputStream = getClass().getResourceAsStream("/MainRosterDatabase-Tracker.tsv");
                BufferedReader TSVReader = new BufferedReader(new InputStreamReader(inputStream))) {

                    String line = "";
                    while ((line = TSVReader.readLine()) != null) {
                        String[] lineItems = line.split("\t"); //splitting the line and adding its items in String[]
                        rosterData.add(lineItems); //adding the splitted line array to the ArrayList
                    }

            } catch (IOException e) {
                e.printStackTrace();
            }

            ArrayList<String> noFilter = new ArrayList<String>();
            noFilter.add("None");
            determineAvailablePlayers(noFilter);
        }

        // create list of available players depending upon gameType
        public void determineAvailablePlayers(ArrayList<String> filteringType) {


            availablePlayerTypes.clear();
            availablePlayers.clear();

            for (String[] player: rosterData) {
                availablePlayers.add(player[0]);
            }
            availablePlayers.remove(0);

            switch (gameType) {
                case "LoL (Berserkers)":
                    availablePlayerTypes.add("Assassin");
                    availablePlayerTypes.add("Fighter");
                    availablePlayerTypes.add("Mage");
                    availablePlayerTypes.add("Marksman");
                    availablePlayerTypes.add("Support");
                    availablePlayerTypes.add("Tank");
                    break;
                case "OW (Fenrir)":
                    availablePlayerTypes.add("Offense");
                    availablePlayerTypes.add("Defense");
                    availablePlayerTypes.add("Support");
                    availablePlayerTypes.add("Tank");
                    break;
            }

            // Filter by player types
            if (filteringType.contains("Team Assignment")) {

                for (int i = 0; i < rosterData.size(); i ++) {
                    if (!rosterData.get(i)[3].equals(gameType)) {
                        if (availablePlayers.contains(rosterData.get(i)[0])) {
                            availablePlayers.remove(rosterData.get(i)[0]);
                        }
                    }
                }
            }

            // Filter alphabetically
            if (filteringType.contains("Alphabetically")) {
                Collections.sort(availablePlayers);
            }

            // refresh comboboxes

            GUI.gamePanel.playerTypeDropdown.setModel( new DefaultComboBoxModel(availablePlayerTypes.toArray()));
            GUI.gamePanel.playerDropdown.setModel( new DefaultComboBoxModel(availablePlayers.toArray()));




        }

        public void generateStream() throws IOException {

            // determine title based on game type
            System.out.println("generated stream!");

            // create object mapper instance

            ObjectMapper mapper = new ObjectMapper();

            // convert JSON file to map
            String contents = "";
            try (InputStream inputStream = getClass().getResourceAsStream("/EsportsTest.json")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                contents = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
            Map<String, ArrayList<LinkedHashMap<String, LinkedHashMap<String, String>>>> map = mapper.readValue(contents, Map.class);
            ArrayList<LinkedHashMap<String, LinkedHashMap<String, String>>> sources = map.get("sources");

            // change text to new text
            sources.get(1).get("settings").put("monitor", "4");
            map.put("sources", sources);

            JSONObject jo = new JSONObject(map);

            //Write into the file
            FileOutputStream fos = new FileOutputStream("EsportsTest.json");
            DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
            outStream.writeUTF(jo.toString());
            outStream.close();
            System.out.println("Successfully updated json object to file...!!");

//            try (FileWriter file = new FileWriter("/EsportsTest.json"))
//            {
//                file.write(jo.toString());
//                System.out.println("Successfully updated json object to file...!!");
//            }


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
