import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ConversorApp {
    public static <JSONObject> void main(String[] args) throws MalformedURLException, RuntimeException {

        int opcion = 0;
        int cantidad;
//        cantidad = 0;
        double USD = 1.5;
//        int post = 1;
        System.out.println("********************************");

        String menuOpciones = """
                1 - Dollar a peso argentino
                2 - peso argentino a dollar
                3 - dolar a real brasileño
                4 - real brasileño a dollar
                5 - Dollar a peso colombiano
                6 - Peso colombiano a dollar
                7 - salir
                """;

        System.out.println("********************************");
        System.out.println("Elija una opcion válida");
        Scanner teclado = new Scanner(System.in);

        while(opcion != 7){
            System.out.println(menuOpciones);
            opcion = teclado.nextInt();
            switch (opcion){
                case 1:
                    System.out.println("Dollar a peso argentino");
                    cantidad = teclado.nextInt();
                    System.out.println(USD * cantidad + " pesos argentinos");
                    break;

                case 2:
                    System.out.println("peso argentino a dollar");
                    cantidad = teclado.nextInt();
                    System.out.println(USD / cantidad + " Dolares");
                    break;

                case 3:
                    System.out.println("dollar a real brasileño");
                    cantidad = teclado.nextInt();
                    System.out.println(USD * cantidad + " Reales brasileños");
                    break;

                case 4:
                    System.out.println("Real brasileño a dollar");
                    cantidad = teclado.nextInt();
                    System.out.println(USD / cantidad + " Dolares");
                    break;

                case 5:
                    System.out.println("dollar a peso comlombiano");
                    cantidad = teclado.nextInt();
                    System.out.println(USD * cantidad );
                    break;

                case 6:
                    System.out.println("peso colombiano a dollar");
                    cantidad = teclado.nextInt();
                    System.out.println(USD / cantidad);
                    break;

                case 7:
                    System.out.println("gracias por usar nuestra App");
                    break;
                default:
                    System.out.println("opcion no valida");
            }
        }


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
