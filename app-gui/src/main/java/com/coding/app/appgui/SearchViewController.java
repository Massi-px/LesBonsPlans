package com.coding.app.appgui;

import com.coding.app.data.dao.AnnonceDao;
import com.coding.app.data.model.Annonce;
import com.coding.app.dispacher.Dispatcher;
import com.coding.app.utils.SiteEnum;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import org.controlsfx.control.CheckComboBox;

import java.util.List;

public class SearchViewController {
    @FXML
    private TextField keywordsField;
    @FXML
    private CheckComboBox<String> siteCheckComboBox;
    @FXML
    private TextField refreshFrequencyField;
    @FXML
    private ListView<Annonce> listingsListView;
    @FXML
    private Button startSearchButton;
    @FXML
    private Button stopSearchButton;
    @FXML
    private Region spacer;

    private final Dispatcher dispatcher = new Dispatcher();

    ObservableList<Annonce> observabled = FXCollections.observableArrayList();
    private final AnnonceDao annonceDao = new AnnonceDao();


    @FXML
    public void initialize() {
        HBox.setHgrow(spacer, Priority.ALWAYS);
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
    private void increaseRefreshFrequency() {
        int currentValue = getRefreshFrequency();
        refreshFrequencyField.setText(String.valueOf(currentValue + 1));
    }

    @FXML
    private void decreaseRefreshFrequency() {
        int currentValue = getRefreshFrequency();
        if (currentValue > 0) {
            refreshFrequencyField.setText(String.valueOf(currentValue - 1));
        }
    }

    @FXML
    protected void onStopSearchClick() {
        startSearchButton.setDisable(false);
        stopSearchButton.setDisable(true);
        // Add logic to stop the search if needed
    }

    @FXML
    protected void onStartSearchClick() {
        String keywords = keywordsField.getText();
        var selectedSite = siteCheckComboBox.getCheckModel().getCheckedItems().stream().toList();

        String refreshFrequency = refreshFrequencyField.getText();

        for (String site : selectedSite) {
            if ("Les Bons Plans".equals(site)) {
                fillListings(keywords, SiteEnum.LES_BONS_PLANS);
            }
            if ("Le Bon Coin".equals(site)) {
                fillListings(keywords, SiteEnum.LE_BON_COIN);
            }
        }
    }

    @FXML
    protected void onClearSearchClick() {
        observabled.clear();
    }

    @FXML
    private void onSaveSelectedClick() {
        List<Annonce> selectedAnnonces = listingsListView.getSelectionModel().getSelectedItems();
        for (Annonce annonce : selectedAnnonces) {
            annonceDao.saveAnnonce( annonce );
        }
    }


    private int getRefreshFrequency() {
        try {
            return Integer.parseInt(refreshFrequencyField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void fillListings(String keywords, SiteEnum siteEnum) {

        Platform.runLater(new Runnable() {public void run() {
            dispatcher.dispatch( siteEnum, keywords, observabled );
        }});

    }

    // Action event handler for menuItem saved state
    private void handleSaveAction() {

    }


}