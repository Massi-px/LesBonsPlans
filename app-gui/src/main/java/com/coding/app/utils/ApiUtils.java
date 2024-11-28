package com.coding.app.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
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

//    public static void main(String[] args) throws IOException, InterruptedException {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri( URI.create("http://localhost:8080/site_annonce_war/api/announcement?keyword="))
//                .GET()
//                .build();
//        HttpClient client = HttpClient.newHttpClient();
//
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//        System.out.println(response.body());
//
//        var map = bodyToMap(response.body());
//
//        System.out.println(map);
//
//    }

//    private static Map<String,String> bodyToMap(String body) {
//
//
//        try {
//            ArrayList mappingTab = new ObjectMapper().readValue(body, ArrayList.class);
//
//
//            //HashMap<Object, Object> mapping = new ObjectMapper().readValue(body, HashMap.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException( e );
//        }
//
//        return null;
//    }

}
