import jdk.jfr.internal.tool.Main;
import jdk.nashorn.internal.parser.JSONParser;
import sun.misc.ClassLoaderUtil;

import javax.annotation.Resources;
import javax.swing.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;
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

        public void generateStream() {

            // determine title based on game type
            System.out.println("generated stream!");

//            for (String[] value: rosterData) {
//                for (String val: value) {
//                    System.out.println(val);
//                }
//            }
            // update stream title

            // update roster


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
