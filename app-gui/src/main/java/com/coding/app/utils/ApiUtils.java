package com.coding.app.utils;

import com.coding.app.data.model.Annonce;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableList;

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

    public static String jsonResponseAPI(
            String url,
            String keyword
    ) {
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

            return content.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of().toString();
    }


        private static List<Map<String, String>> bodyToMap(String body, ObservableList<Annonce> obs) {

        try {
            List<Map<String, String>> list = new ArrayList<>();
            ArrayList mappingTab = new ObjectMapper().readValue(body, ArrayList.class);
            for (Object o : mappingTab) {
                Map<String, String> map = (Map<String, String>) o;
                Annonce a = new Annonce();
                a.setTitle( map.get( "title" ) );
                a.setPath( map.get( "path" ) );
                //a.setImage( map.get( "image" ) );
                a.setSite( map.get( "site" ) );
                a.setCreatedAt( new Timestamp(System.currentTimeMillis()) );
                displayListings(a, obs);
                list.add(map);
            }
            return list;
        } catch (JsonProcessingException e) {
            throw new RuntimeException( e );
        }

    }

    public static void displayListings(Annonce a, ObservableList<Annonce> obs) {
      obs.addFirst(a );
    }



    public static String getListLeBonCoin(
            String url,
            String keyword
    ) {
        try {

            String encodedKeywords = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
            String apiUrl = url + encodedKeywords;
            URL u = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();

            os.flush();
            os.close();
            int responseCode = con.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in       = new BufferedReader( new InputStreamReader( con.getInputStream() ) );
                String         inputLine;
                StringBuilder  response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append( inputLine );
                }
                in.close();
                return response.toString();
            }else {
                System.out.println("POST request did not work.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of().toString();
    }


}
