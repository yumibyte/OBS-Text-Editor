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

        public static String fileName = "C:\\Users\\ashra\\AppData\\Roaming\\obs-studio\\basic\\scenes\\Esports2021.json";
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

            // Retrieve JSON file if we don't know the path

            updateOBSTitle();
            updateRoster();

            if (gameType.equals("LoL (Berserkers)")) {
                updateMobalytics();
            }

            System.out.println("generated stream!");


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
//            for (int i = 0; i < 5; i++) {

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
//                }
            }
            JSONObject jo = new JSONObject(map);

            try (FileWriter file = new FileWriter(fileName))
            {
                file.write(jo.toString());
                System.out.println("Successfully updated json object to file...!!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void updateRoster() throws IOException {
            // UPDATE OBS
            // create object mapper instance

            // choose file
//            JFileChooser jfc = new JFileChooser();
//            fileName = jfc.getSelectedFile().getAbsolutePath();

            ObjectMapper mapper = new ObjectMapper();

            Map<String, ArrayList<LinkedHashMap<String, LinkedHashMap<String, String>>>> map = mapper.readValue(Paths.get(fileName).toFile(), Map.class);
            ArrayList<LinkedHashMap<String, LinkedHashMap<String, String>>> sources = map.get("sources");

            // change text to new text

            // increment y for each player on the roster
//            if (gameType.equals("RL (Ragnarok)")) {
//                int y = 0;
//                for (int x = 96; x > 91; x --) {
//                    sources.get(x).get("settings").put("text", GUI.gamePanel.finalRoster.get(y));
//                    y ++;
//
//                    map.put("sources", sources);
//                }
//            }

            if (gameType.equals("OW (Fenrir)") || gameType.equals("RL (Ragnarok)")) {

                // locations of roster text fields
                ArrayList<Integer> rosterTextNumbers = new ArrayList<Integer>();
                rosterTextNumbers.add(93);
                rosterTextNumbers.add(94);
                rosterTextNumbers.add(95);
                rosterTextNumbers.add(96);
                rosterTextNumbers.add(97);
                rosterTextNumbers.add(104);

                // fill in text fields to number of roster slots based on the game

                ArrayList<String> keys = new ArrayList<String>();
                keys = (ArrayList<String>) GUI.gamePanel.finalRoster.keySet();
                for (int i = 0; i < numberOfPlayers; i ++) {
                    sources.get(rosterTextNumbers.get(i)).get("settings").put("text", keys.get(i));
                    map.put("sources", sources);
                }

                // remove slots used
                for (int k = 0; k < numberOfPlayers; k ++) {
                    rosterTextNumbers.remove(0);
                }

                // replace remaining text fields with nothing
                if (!(rosterTextNumbers.size() == 0)) {
                    for (int j = 0; j < rosterTextNumbers.size(); j ++) {

                        sources.get(rosterTextNumbers.get(j)).get("settings").put("text","");

                        map.put("sources", sources);
                    }
                }

//                for (String player: GUI.gamePanel.finalRoster) {
//
//
//                }
            }

            JSONObject jo = new JSONObject(map);

            try (FileWriter file = new FileWriter(fileName))
            {
                file.write(jo.toString());
                System.out.println("Successfully updated json object to file...!!");
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        public void updateOBSTitle() throws IOException {

            if (fileName.equals("")) {
                JFileChooser jfc = new JFileChooser();
                jfc.showDialog(null,"Please select your OBS configuration file (JSON). This can be found in %appdata%/obs-studio folder/basic/scenes");
                jfc.setVisible(true);
                fileName = jfc.getSelectedFile().getAbsolutePath();
            }
            // UPDATE OBS
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();

            Map<String, ArrayList<LinkedHashMap<String, LinkedHashMap<String, String>>>> map = mapper.readValue(Paths.get(fileName).toFile(), Map.class);
            ArrayList<LinkedHashMap<String, LinkedHashMap<String, String>>> sources = map.get("sources");

            // change text to new text
            sources.get(34).get("settings").put("text", GUI.gamePanel.title);
            System.out.println(GUI.gamePanel.title);
            map.put("sources", sources);

            JSONObject jo = new JSONObject(map);

            try (FileWriter file = new FileWriter(fileName))
            {
                file.write(jo.toString());
                System.out.println("Successfully updated json object to file...!!");
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
