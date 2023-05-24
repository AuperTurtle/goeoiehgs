//wow this class sucks
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
public class tempParser {
    public tempParser() {
    }

    public static void plswork() {
        //https://myanimelist.net/v1/oauth2/authorize?response_type=code&client_id=0db950674f429006c3ee9393aae7cb43&code_challenge=A1B2C3...&state=RequestID42
        String APIkey = "0db950674f429006c3ee9393aae7cb43"; // your personal API key on TheMovieDatabase
        String queryParameters = "?api_key=" + APIkey;
        String endpoint = "https://api.myanimelist.net/v2/anime/ranking";
        String url = endpoint + queryParameters;
        String urlResponse = "";
        try {
            URI myUri = URI.create(url); // creates a URI object from the url string
            HttpRequest request = HttpRequest.newBuilder().uri(myUri).build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            urlResponse = response.body();
            System.out.println(urlResponse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
