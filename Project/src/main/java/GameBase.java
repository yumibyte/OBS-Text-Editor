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
        public static Map<String, String> mobalyticsData = new HashMap<String, String>();

        public static String fileName = "";
//        public static String fileName = "C:\\Users\\ashra\\AppData\\Roaming\\obs-studio\\basic\\scenes\\Esports2021.json";
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
                    availablePlayerTypes.add("Top");
                    availablePlayerTypes.add("Jungle");
                    availablePlayerTypes.add("Mid");
                    availablePlayerTypes.add("ADC");
                    availablePlayerTypes.add("Support");
                    break;
                case "OW (Fenrir)":
                    availablePlayerTypes.add("DPS 1");
                    availablePlayerTypes.add("DPS 2");
                    availablePlayerTypes.add("Support 1");
                    availablePlayerTypes.add("Support 2");
                    availablePlayerTypes.add("Tank 1");
                    availablePlayerTypes.add("Tank 2");

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

            // Retrieve JSON file if we don't know the path
            if (fileName.equals("")) {
                String path = JOptionPane.showInputDialog(GUI.frame, "What is the absolute location of your OBS configuration file? Please include the .json file in the path name and use backslashes (Windows)", null);
                path = path.replace("\\", "\\\\");
                System.out.println(path);
                fileName = path;
            }

            // update OBS
            updateOBSTitle();
            updateRoster();

            if (gameType.equals("LoL (Berserkers)")) {
                updateMobalytics();
            }

        }

        public void updateMobalytics() throws IOException {

            // create list of Mobalytics data
            try (InputStream inputStream = getClass().getResourceAsStream("/MobalyticsRoster - Sheet1.tsv");
                 BufferedReader TSVReader = new BufferedReader(new InputStreamReader(inputStream))) {

                String line = "";
                while ((line = TSVReader.readLine()) != null) {
                    String[] lineItems = line.split("\t"); //splitting the line and adding its items in String[]
                    mobalyticsData.put(lineItems[0], lineItems[4]); //adding the splitted line array to the ArrayList
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            ArrayList<Integer> availableMobalyticsSlots = new ArrayList<Integer>();
            availableMobalyticsSlots.add(6);        // top laner
            availableMobalyticsSlots.add(4);        // mid laner
            availableMobalyticsSlots.add(83);       // ADC
            availableMobalyticsSlots.add(5);        // jungle
            availableMobalyticsSlots.add(84);       // support

            // Update OBS
            ObjectMapper mapper = new ObjectMapper();

            Map<String, ArrayList<LinkedHashMap<String, LinkedHashMap<String, String>>>> map = mapper.readValue(Paths.get(fileName).toFile(), Map.class);
            ArrayList<LinkedHashMap<String, LinkedHashMap<String, String>>> sources = map.get("sources");


            // 5 players in LoL
            for (String player : GUI.gamePanel.selectedPlayers) {

                if (mobalyticsData.containsKey(player)) {

                    switch (GUI.gamePanel.finalRoster.get(player)) {
                        case "Top":
                            sources.get(6).get("settings").put("url", mobalyticsData.get(player));
                            map.put("sources", sources);
                            break;
                        case "Mid":
                            sources.get(4).get("settings").put("url", mobalyticsData.get(player));
                            map.put("sources", sources);
                            break;
                        case "Support":
                            sources.get(84).get("settings").put("url", mobalyticsData.get(player));
                            map.put("sources", sources);
                            break;
                        case "ADC":
                            sources.get(83).get("settings").put("url", mobalyticsData.get(player));
                            map.put("sources", sources);
                            break;
                        case "Jungle":
                            sources.get(5).get("settings").put("url", mobalyticsData.get(player));
                            map.put("sources", sources);
                            break;
                    }


                }
            }
            JSONObject jo = new JSONObject(map);

            try (FileWriter file = new FileWriter(fileName))
            {
                file.write(jo.toString());
                System.out.println("Successfully updated mobalytics data to json!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void updateRoster() throws IOException {
            // UPDATE OBS
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();

            Map<String, ArrayList<LinkedHashMap<String, LinkedHashMap<String, String>>>> map = mapper.readValue(Paths.get(fileName).toFile(), Map.class);
            ArrayList<LinkedHashMap<String, LinkedHashMap<String, String>>> sources = map.get("sources");

            if (gameType.equals("OW (Fenrir)") || gameType.equals("RL (Ragnarok)")) {

                // locations of roster text fields
                ArrayList<Integer> rosterTextNumbers = new ArrayList<Integer>();
                rosterTextNumbers.add(91);
                rosterTextNumbers.add(90);
                rosterTextNumbers.add(92);
                rosterTextNumbers.add(88);
                rosterTextNumbers.add(89);
                rosterTextNumbers.add(98);

                // fill in text fields to number of roster slots based on the game

                Object[] keys = GUI.gamePanel.finalRoster.keySet().toArray();

                for (int i = 0; i < keys.length; i ++) {

                    String newText = (String) keys[i];

                    if (gameType.equals("OW (Fenrir)")) {
                        newText = keys[i] + " | " + GUI.gamePanel.finalRoster.get(keys[i]);

                    }
                    sources.get(rosterTextNumbers.get(i)).get("settings").put("text", newText);
                    map.put("sources", sources);
                }

                // remove slots used
                for (int k = 0; k < keys.length; k ++) {
                    rosterTextNumbers.remove(0);
                }

                // replace remaining text fields with nothing
                if (!(rosterTextNumbers.size() == 0)) {
                    for (int j = 0; j < rosterTextNumbers.size(); j ++) {

                        sources.get(rosterTextNumbers.get(j)).get("settings").put("text","");

                        map.put("sources", sources);
                    }
                }

            }

            JSONObject jo = new JSONObject(map);

            try (FileWriter file = new FileWriter(fileName))
            {
                file.write(jo.toString());
                System.out.println("Successfully updated roster to json!");
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        public void updateOBSTitle() throws IOException {

            // UPDATE OBS
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();

            Map<String, ArrayList<LinkedHashMap<String, LinkedHashMap<String, String>>>> map = mapper.readValue(Paths.get(fileName).toFile(), Map.class);
            ArrayList<LinkedHashMap<String, LinkedHashMap<String, String>>> sources = map.get("sources");

            // change text to new text
            sources.get(29).get("settings").put("text", GUI.gamePanel.title);
            map.put("sources", sources);

            JSONObject jo = new JSONObject(map);

            try (FileWriter file = new FileWriter(fileName))
            {
                file.write(jo.toString());
                System.out.println("Successfully updated title to json!");
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
