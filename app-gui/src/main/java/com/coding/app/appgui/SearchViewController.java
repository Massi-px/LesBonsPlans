package com.coding.app.appgui;

import com.coding.app.dispacher.Dispatcher;
import com.coding.app.utils.SiteEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import org.controlsfx.control.CheckComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class SearchViewController {
    @FXML
    private TextField keywordsField;
    @FXML
    private CheckComboBox<String> siteCheckComboBox;
    @FXML
    private TextField refreshFrequencyField;
    @FXML
    private ListView<String> listingsListView;

    ObservableList<String> observabled = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        siteCheckComboBox.getItems().addAll("Les Bons Plans", "Le Bon Coin");
        listingsListView.setItems( observabled );

        // Create context menu
        ContextMenu contextMenu = new ContextMenu();
        MenuItem    menuItem   = new MenuItem("save");
        //MenuItem    menuItem2   = new MenuItem("Option 2");
        contextMenu.getItems().addAll(menuItem/*, menuItem2*/);

        // Set context menu to the ListView
        listingsListView.setContextMenu(contextMenu);
        // Set action event handler for menuItem1
        menuItem.setOnAction(event -> handleSaveAction());
    }

    @FXML
    protected void onStartSearchClick() {

        String keywords = keywordsField.getText();
        var selectedSite = siteCheckComboBox.getCheckModel().getCheckedItems().stream().toList();

        String refreshFrequency = refreshFrequencyField.getText();

        for (String site : selectedSite) {
            if ("Les Bons Plans".equals(site)) {
                // Thread.ofVirtual().start(() -> fillListings(keywords, SiteEnum.LES_BONS_PLANS));
                fillListings(keywords, SiteEnum.LES_BONS_PLANS);
            }
            if ("Le Bon Coin".equals(site)) {
                //Thread.ofVirtual().start(() -> fillListings(keywords, SiteEnum.LE_BON_COIN));
                fillListings(keywords, SiteEnum.LE_BON_COIN);
            }
        }

    }

    private final Dispatcher dispatcher = new Dispatcher();

    private void fillListings(String keywords, SiteEnum siteEnum) {

        Thread.ofVirtual().start(() -> {
            dispatcher.dispatch( siteEnum, keywords, observabled );
            Thread.currentThread().interrupt();
        });

    }

    // Action event handler for menuItem saved state
    private void handleSaveAction() {
        String selectedItem = listingsListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            System.out.println("Save action triggered for item: " + selectedItem);
        } else {
            System.out.println("No item selected");
        }
    }


}