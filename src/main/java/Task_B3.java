import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Task_B3 {

    public static void main(String[] args) throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("src\\main\\resources\\task_B3.json"));

        for (Object object : jsonArray) {
            JSONObject cryptocurrency = (JSONObject) object;
            String name = (String) cryptocurrency.get("name");
            String price_usd = (String) cryptocurrency.get("price_usd");

            System.out.println(name + ": " + price_usd);
        }
    }
}
