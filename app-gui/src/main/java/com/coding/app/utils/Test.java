package com.coding.app.utils;


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

public class Test {

    public static void main(String[] args) {
        try {
            URL obj = new URL("https://www.leboncoin.fr/recherche?text=anglais+chien");
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

                Document doc = Jsoup.parse(response.toString());
                Elements adListCard = doc.select("div.mb-lg");
                Elements adCards = adListCard.select("div.styles_adCard__JzKik");

                for (Element adCard : adCards) {
                    String title = adCard.select("p[data-qa-id=aditem_title]").text();
                    String price = adCard.select("span[data-qa-id=aditem_price]").text();
                    String category = adCard.select("p[aria-label^=Catégorie]").text();
                    String location = adCard.select("p[aria-label^=Située]").text();
                    String date = adCard.select("p[aria-label^=Date]").text();
                    String imageUrl = adCard.select("img").attr("src");
                    String lienannonce = adCard.select("a[data-qa-id=aditem_container]").attr("href");

                    System.out.println("Title: " + title);
                    System.out.println("Price: " + price);
                    System.out.println("Category: " + category);
                    System.out.println("Location: " + location);
                    System.out.println("Date: " + date);
                    System.out.println("Image URL: " + imageUrl);
                    System.out.println("Lien annonce: " + "https://www.leboncoin.fr"+ lienannonce);
                    System.out.println("------");
                }
            } else {
                System.out.println("POST request did not work.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
