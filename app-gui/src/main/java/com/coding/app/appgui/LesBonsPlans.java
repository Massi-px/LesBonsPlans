package com.coding.app.appgui;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class LesBonsPlans {
    @FXML
    private TextField keywordsField;

    @FXML
    private ComboBox<String> siteComboBox;

    @FXML
    private TextField refreshFrequencyField;

    @FXML
    private ListView<String> listingsListView;

    @FXML
    private ListView<String> savedListingsListView;

    private final ObservableList<String> savedListings = FXCollections.observableArrayList();

    @FXML
    protected void onStartSearchClick() {
        String keywords = keywordsField.getText();
        String selectedSite = siteComboBox.getValue();

        if ("LesBonsPlans".equals(selectedSite)) {
            fetchLesBonsPlansListings(keywords);
        }
    }

    private void fetchLesBonsPlansListings(String keywords) {
        try {
            String encodedKeywords = URLEncoder.encode(keywords, StandardCharsets.UTF_8);
            String apiUrl = "http://localhost:8080/site_annonce_war/api/announcement?keyword=" + encodedKeywords;
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder content = new StringBuilder();
            content.append(in.readLine());
            in.close();
            conn.disconnect();

            parseAndDisplayListings(content.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseAndDisplayListings(String jsonResponse) {
        ObservableList<String> listings = FXCollections.observableArrayList();
        JSONArray jsonArray = new JSONArray(jsonResponse);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String title = jsonObject.optString("title", "No title");
            String link = jsonObject.optString("link", "No link");
            String image = jsonObject.optString("image", "No image");
            String date = jsonObject.optString("date", "No date");

            listings.add("Title: " + title + "\nLink: " + link + "\nImage: " + image + "\nDate: " + date);
        }

        listingsListView.setItems(listings);
    }

    @FXML
    protected void onDeleteSelectedClick() {
        String selectedListing = savedListingsListView.getSelectionModel().getSelectedItem();
        if (selectedListing != null) {
            savedListings.remove(selectedListing);
            savedListingsListView.setItems(savedListings);
        }
    }
}