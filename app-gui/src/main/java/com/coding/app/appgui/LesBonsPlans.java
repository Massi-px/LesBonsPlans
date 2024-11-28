package com.coding.app.appgui;

import com.coding.app.data.model.Annonce;
import com.coding.app.utils.SiteEnum;
import com.coding.app.wrappers.SBPWrapper;
import com.coding.app.wrappers.SiteWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

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

    private final SiteWrapper siteWrapper = new SBPWrapper( SiteEnum.LES_BONS_PLANS );

    @FXML
    protected void onStartSearchClick() {
        String keywords = keywordsField.getText();
        String selectedSite = siteComboBox.getValue();

        if ("LesBonsPlans".equals(selectedSite)) {
            fetchLesBonsPlansListings(keywords);
        }
    }

    private void fetchLesBonsPlansListings(String keywords) {
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