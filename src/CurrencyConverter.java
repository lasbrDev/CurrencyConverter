import com.sun.net.httpserver.Request;
import netscape.javascript.JSObject;
import org.json.JSONMLParserConfiguration;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

    public class CurrencyConverter {
        public static void main(String[] args) {
            HashMap<Integer, String> currencyCodes = new HashMap<Integer, String>();

            currencyCodes.put(1, "USD");
            currencyCodes.put(2, "BRL");
            currencyCodes.put(3, "EUR");
            currencyCodes.put(4, "ARS");
            currencyCodes.put(5, "AUD");
            currencyCodes.put(6, "JPY");
            currencyCodes.put(7, "CNY");

            String fromcode, toCode;
            double amount;

            Scanner sc = new Scanner(System.in);

            System.out.println("Welcome to the Currency Converter! ");

            System.out.println("Currencu converting FROM?");
            System.out.println("1:USD (US Dollar)\t 2:BRL (Brazilian Real)\t 3:EUR (Euro)\t 4:ARS (Argentinian Peso)\t 5:AUD (Australian Dollar)\t 6:JPY (Japanese Yen)\t 7:CNY (Chinese Yuan)");
            fromcode = currencyCodes.get(sc.nextInt());

            System.out.println("Currencu converting TO?");
            System.out.println("1:USD (US Dollar)\t 2:BRL (Brazilian Real)\t 3:EUR (Euro)\t 4:ARS (Argentinian Peso)\t 5:AUD (Australian Dollar)\t 6:JPY (Japanese Yen)\t 7:CNY (Chinese Yuan)");
            toCode = currencyCodes.get(sc.nextInt());

            System.out.println("Amount you wish to convert?");
            amount = sc.nextFloat();

            //sendHttpGETRequest(fromcode, toCode, amount);

            System.out.println("Thank you for using the currency conveter!");

        }
        private static void sendHttpGETRequest(String fromCode, String toCode, double amount) throws IOException {
           String GET_URL = "https://api.exchangeratesapi.io/vi/latest?PKKvZ17dTKeHkc7H4NacJ1Pkaw9Gu3Rd" + toCode + "&symbols=" + fromCode;
           URL url = new URL(GET_URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();


            if (responseCode == httpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                } in.close();
                JSONObject obj = new JSONObject(response.toString());
                Double exchangeRate = obj.getJSONObject("rates").getDouble(fromCode);
                System.out.println(obj.getJSONObject("rates"));
                System.out.println(exchangeRate);
                System.out.println();
                System.out.println(amount + fromCode + " = " + amount/exchangeRate + toCode);
            } else {
                System.out.println("GET request failed");
            }
        }

    }