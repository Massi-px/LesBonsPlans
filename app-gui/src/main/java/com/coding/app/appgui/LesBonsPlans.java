package com.coding.app.appgui;

import com.coding.app.data.model.Annonce;
import com.coding.app.dispacher.Dispacher;
import com.coding.app.utils.SiteEnum;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.controlsfx.control.CheckComboBox;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class LesBonsPlans {
    @FXML
    private TextField keywordsField;

    @FXML
    private CheckComboBox<String> siteCheckComboBox;

    @FXML
    private TextField refreshFrequencyField;

    @FXML
    private ListView<String> listingsListView;

    @FXML
    private ListView<String> savedListingsListView;

    private final ObservableList<String> savedListings = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        siteCheckComboBox.getItems().addAll("LesBonsPlans", "AnotherSite");
    }

    private final Dispacher dispacher = new Dispacher();

    @FXML
    protected void onStartSearchClick() {
        String keywords = keywordsField.getText();
        String selectedSite = siteCheckComboBox.getCheckModel().getCheckedItems().get(0);

        if ("LesBonsPlans".equals(selectedSite)) {
            fetchLesBonsPlansListings(keywords);
        }
    }

    private void fetchLesBonsPlansListings(String keywords) {
        var siteWrapper = dispacher.dispach( SiteEnum.LES_BONS_PLANS );
         siteWrapper.setParams( keywords );
        var liste = siteWrapper.search();

        displayListings(liste );

    }

    private void displayListings(List<Annonce> annonces) {
        ObservableList<String> listings = FXCollections.observableArrayList();
        for (Annonce a : annonces) {

            listings.add("Title: " + a.getTitle() + "\nLink: " + a.getSite() + "\nImage: " + a.getPath() + "\nDate: " + a.getCreatedAt());
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