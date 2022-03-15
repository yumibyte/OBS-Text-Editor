import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;


public class test {

    public static String path = "/Users/ashleyraigosa/Library/Application Support/obs-studio/basic/scenes/EsportsTest.json";
    public static void main(String[] args) throws JSONException, IOException
    {
        // create object mapper instance

        ObjectMapper mapper = new ObjectMapper();

        // convert JSON file to map
        Map<String, ArrayList<LinkedHashMap<String, LinkedHashMap<String, String>>>> map = mapper.readValue(Paths.get(path).toFile(), Map.class);
        ArrayList<LinkedHashMap<String, LinkedHashMap<String, String>>> sources = map.get("sources");

        // change text to new text
        sources.get(1).get("settings").put("text", "new");
        map.put("sources", sources);

        JSONObject jo = new JSONObject(map);

        //Write into the file
        try (FileWriter file = new FileWriter(path))
            {
                file.write(jo.toString());
                System.out.println("Successfully updated json object to file...!!");
            }
    }
}