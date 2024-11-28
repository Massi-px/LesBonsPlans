package com.coding.app.wrappers;

import com.coding.app.data.model.Annonce;
import com.coding.app.utils.SiteEnum;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class LeBonCoinWrapper extends SiteWrapper {

    public LeBonCoinWrapper(SiteEnum siteEnum) {
        super( siteEnum.getUrl(), "" );
    }

    public LeBonCoinWrapper(SiteEnum siteEnum, String params) {
        super( siteEnum.getUrl(), params );
    }

    @Override
    public List<Annonce> search() {

        return List.of();
    }

    public static void main(String[] args) throws IOException {

        URL obj = new URL("https://www.leboncoin.fr/recherche?text=anglais&locations=paris");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();

        os.flush();
        os.close();
        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            Document doc = Jsoup.parse(response.toString());
        } else {
            System.out.println("POST request did not work.");
        }

    }

}
