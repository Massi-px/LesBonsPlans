package com.coding.app.appgui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ManageListingsViewController {

    private final ObservableList<String> savedListings = FXCollections.observableArrayList();

    @FXML
    private ListView<String> savedListingsListView;
    @FXML
    protected void onDeleteSelectedClick() {
        String selectedListing = savedListingsListView.getSelectionModel().getSelectedItem();
        if (selectedListing != null) {
            savedListings.remove(selectedListing);
            savedListingsListView.setItems(savedListings);
        }
    }
}
