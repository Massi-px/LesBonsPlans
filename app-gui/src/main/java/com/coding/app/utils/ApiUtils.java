package com.coding.app.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ApiUtils {

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri( URI.create("http://localhost:8080/site_annonce_war/api/announcement?keyword="))
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        var map = bodyToMap(response.body());


    }

    private static Map<String,String> bodyToMap(String body) {


        try {
            ArrayList mappingTab = new ObjectMapper().readValue(body, ArrayList.class);


            //HashMap<Object, Object> mapping = new ObjectMapper().readValue(body, HashMap.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException( e );
        }

        return null;
    }

}
