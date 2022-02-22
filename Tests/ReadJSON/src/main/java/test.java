import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;

public class test {

    public static void main(String[] args) throws IOException, ParseException {

        try {
            String content = new String(Files.readAllBytes(Paths.get("/Users/ashleyraigosa/Desktop/Programming/OBSTextEditor/Tests/ReadJSON/src/main/java/Untitled.json")));


            JSONObject j = new JSONObject(content);
            j.getJSONObject("sources").put("text", "test");
            System.out.println(j.get("AuxAudioDevice1"));

        } catch
            (FileNotFoundException e) {e.printStackTrace();} catch (JSONException e) {
            e.printStackTrace();
        }
//
    }
}
