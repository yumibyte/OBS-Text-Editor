import jdk.nashorn.internal.parser.JSONParser;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

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
            File rosterFile = new File("/Users/ashleyraigosa/Desktop/Programming/OBSTextEditor/Project/src/main/java/Main Roster Database - Tracker.tsv");

            try (BufferedReader TSVReader = new BufferedReader(new FileReader(rosterFile))) {

                // store each row of roster onto an arraylist
                String line = "";
                while ((line = TSVReader.readLine()) != null) {
                    String[] lineItems = line.split("\t"); //splitting the line and adding its items in String[]
                    rosterData.add(lineItems); //adding the splitted line array to the ArrayList
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            determineAvailablePlayers();
        }

        // create list of available players depending upon gameType
        public void determineAvailablePlayers() {

            availablePlayerTypes.clear();
            availablePlayers.clear();
            
            // determine available player types
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

            // determine available players


            for (int i = 0; i < rosterData.size(); i ++) {
                if (rosterData.get(i)[3].equals(gameType)) {
                    availablePlayers.add(rosterData.get(i)[0]);
                    System.out.println("added players");
                }
            }

            // refresh comboboxes

            GUI.gamePanel.playerTypeDropdown.setModel( new DefaultComboBoxModel(availablePlayerTypes.toArray()));
            GUI.gamePanel.playerDropdown.setModel( new DefaultComboBoxModel(availablePlayers.toArray()));




        }

        // sorting/search functions
        public ArrayList<String> parseRoster(String gameType) {
            //
            ArrayList<String> roster = new ArrayList<String>();

            return roster;
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
