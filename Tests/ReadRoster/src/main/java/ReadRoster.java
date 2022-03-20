import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadRoster {

    public static void main(String[] args) {

        ArrayList<String[]> rosterData = new ArrayList<>(); //initializing a new ArrayList out of String[]'s
        File rosterFile = new File("/Users/ashleyraigosa/Desktop/Programming/OBSTextEditor/Tests/ReadRoster/src/main/java/MainRosterDatabaseTracker.tsv");

        try (BufferedReader TSVReader = new BufferedReader(new FileReader(rosterFile))) {

            // store each row of roster onto an arraylist
            String line = "";
            while ((line = TSVReader.readLine()) != null) {
                String[] lineItems = line.split("\t"); //splitting the line and adding its items in String[]
                rosterData.add(lineItems); //adding the splitted line array to the ArrayList
            }

            // sort by game type

            for (String[] row: rosterData) {
                System.out.println(row[3]);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
