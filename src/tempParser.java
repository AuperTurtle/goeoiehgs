//wow this class sucks
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class tempParser {
    public static ArrayList<String> animeList;
    public static ArrayList<String> animePicture;

    public tempParser() {
        this.animeList = new ArrayList<String>();
        this.animePicture = new ArrayList<String>();
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
            int animeID = nodeObject.getInt("id");

            String endpoint2 = "https://api.myanimelist.net/v2/anime/" + animeID + "?fields=id,title,main_picture,alternative_titles,start_date,end_date,synopsis,mean,rank,popularity,num_list_users,num_scoring_users,nsfw,created_at,updated_at,media_type,status,genres,my_list_status,num_episodes,start_season,broadcast,source,average_episode_duration,rating,pictures,background,related_anime,related_manga,recommendations,studios,statistics";
            String url2 = endpoint2;
            String urlResponse2 = "";
            try {
                URI myUri = URI.create(url2); // creates a URI object from the url string
                HttpRequest request = HttpRequest.newBuilder().header("X-MAL-CLIENT-ID", "0db950674f429006c3ee9393aae7cb43").uri(myUri).build();
                HttpClient client = HttpClient.newHttpClient();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                urlResponse2 = response.body();
                System.out.println(urlResponse2);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            JSONObject jsonObj2 = new JSONObject(urlResponse);
            System.out.println(jsonObj2.getInt("rank"));
            //wtf is parsing

//            JSONObject alternativeTitle = nodeObject.getJSONObject("alternative_title");
//            String enTitle = alternativeTitle.getString("en");
//            String jpTitle = alternativeTitle.getString("ja");
//            System.out.println(enTitle + " ; " + jpTitle);
            //System.out.println("Title: " + animeTitle + " Picture: " + pictureLink);
            animeList.add(animeTitle);
            animePicture.add(pictureLink);
            //put image code from load weather and make 2 arrays zzz
            /*
            private void loadWeather(String zip) {
                weather = WeatherNetworking.getWeatherForZip(zip);
                locationLabel.setText("Location: " + weather.getLocation());
                if (showCelsiusCheckBox.isSelected()) {
                    tempLabel.setText("Current temp: " + weather.getTempC() + "°C");
                } else {
                    tempLabel.setText("Current temp: " + weather.getTempF() + "°F");
                }
                conditionLabel.setText("Current condition: " + weather.getCondition());
                try {
                    URL imageURL = new URL(weather.getIconURL());
                    BufferedImage image = ImageIO.read(imageURL);
                    ImageIcon icon = new ImageIcon(image);
                    conditionIcon.setIcon(icon);
                } catch (IOException e) { }
            }

            private void clear() {
                conditionIcon.setIcon(new ImageIcon("src/Question_mark_(black).svg.png"));
            }
            */
        }
    }
}
