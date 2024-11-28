package com.coding.app.utils;


import com.coding.app.data.model.Annonce;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ApiUtils {

    public static List<Map<String, String>> jsonResponseAPI(String url, String keyword) {
        try {
            String encodedKeywords = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
            String apiUrl = url + encodedKeywords;
            URL u = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in      = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder  content = new StringBuilder();
            content.append(in.readLine());
            in.close();
            conn.disconnect();

            return bodyToMap(content.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    private static List<Map<String, String>> bodyToMap(String body) {

        try {
            List<Map<String, String>> list = new ArrayList<>();
            ArrayList mappingTab = new ObjectMapper().readValue(body, ArrayList.class);
            for (Object o : mappingTab) {
                Map<String, String> map = (Map<String, String>) o;
                list.add(map);
            }
            return list;
        } catch (JsonProcessingException e) {
            throw new RuntimeException( e );
        }

    }

    public static List<Annonce> getListLeBonCoin(String url, String keyword) {
        try {

            List<Annonce> annonces = new ArrayList<>();

            URL obj = new URL("https://www.leboncoin.fr/recherche?text=" + keyword);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();

            os.flush();
            os.close();
            int responseCode = con.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                Document doc        = Jsoup.parse(response.toString());
                Elements adListCard = doc.select("div.mb-lg");
                Elements adCards    = adListCard.select("div.styles_adCard__JzKik");

                for (Element adCard : adCards) {
                    String title = adCard.select("p[data-qa-id=aditem_title]").text();
                    String date = adCard.select("p[aria-label^=Date]").text();
                    String lienannonce = adCard.select("a[data-qa-id=aditem_container]").attr("href");
                    String location = adCard.select("p[aria-label^=Située]").text();
                    String imageUrl = adCard.select("img").attr("src");

                    Annonce annonce = new Annonce(
                            title,
                            "Leboncoin",
                            "https://www.leboncoin.fr"+ lienannonce,
                            new Timestamp(System.currentTimeMillis())

                    );
                    annonces.add(annonce);

                }
                return annonces;
            } else {
                System.out.println("POST request did not work.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return List.of();
    }


}
