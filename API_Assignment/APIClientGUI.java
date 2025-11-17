import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.swing.*;

public class APIClientGUI {

    public static void main(String[] args) {

        Database.initDatabase(); // <-- IMPORTANT

        HttpClient client = HttpClient.newHttpClient();
        String apiUrl = "https://catfact.ninja/fact";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() == 200) {

                String json = response.body();

                // Extract two fields
                String fact = extractValue(json, "fact");
                int length = Integer.parseInt(extractValue(json, "length"));

                // Save in DB
                Database.saveFact(fact, length);

                // GUI popup
                JOptionPane.showMessageDialog(
                        null,
                        fact,
                        "Cat Fact",
                        JOptionPane.INFORMATION_MESSAGE
                );

                // Print all stored records
                System.out.println("\n--- STORED FACTS ---");
                Database.printFacts();

            } else {
                JOptionPane.showMessageDialog(null,
                        "API failed: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Simple JSON value extractor
    private static String extractValue(String json, String key) {
        String search = "\"" + key + "\":";
        int start = json.indexOf(search);
        if (start == -1) return "0";

        start += search.length();

        // If value is a string
        if (json.charAt(start) == '"') {
            start++;
            int end = json.indexOf("\"", start);
            return json.substring(start, end);
        }

        // If value is numeric
        int end = json.indexOf(",", start);
        if (end == -1) end = json.indexOf("}", start);

        return json.substring(start, end).trim();
    }
}
