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
        String endpoint = "https://api.myanimelist.net/v2/anime/ranking?ranking_type=all&limit=20";
        String url = endpoint;
        String urlResponse = "";
        try {
            URI myUri = URI.create(url); // creates a URI object from the url string
            HttpRequest request = HttpRequest.newBuilder().header("X-MAL-CLIENT-ID", "0db950674f429006c3ee9393aae7cb43").uri(myUri).build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            urlResponse = response.body();
            System.out.println(urlResponse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        JSONObject jsonObj = new JSONObject(urlResponse);

        JSONArray jsonArr = jsonObj.getJSONArray("data");
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject animeObj = jsonArr.getJSONObject(i);
            JSONObject nodeObject = animeObj.getJSONObject("node");
            String animeTitle = nodeObject.getString("title");
            JSONObject pictureObject = nodeObject.getJSONObject("main_picture");
            String pictureLink = pictureObject.getString("large");
            System.out.println("Title: " + animeTitle + " Picture: " + pictureLink);
        }
    }
}
