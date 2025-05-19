import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ConversorApp {
    public static <JSONObject> void main(String[] args) throws MalformedURLException, RuntimeException {
//        int post = 1;

        try {
            URL url = new URL("https://v6.exchangerate-api.com/v6/2d8e0c4915085f5e7a6e2bbb/latest/usd");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200 ){
                throw new RuntimeException("HttpResponseCode "+ responseCode );
            } else {
                System.out.println("Response code: " + responseCode);
                StringBuilder response = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                while(scanner.hasNext()){
                    response.append(scanner.nextLine());
                }
                scanner.close();
//                System.out.println(response.toString());

                JsonParser parser = new JsonParser();
                JsonObject json = parser.parse(response.toString()).getAsJsonObject();

                System.out.println(json.keySet());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
