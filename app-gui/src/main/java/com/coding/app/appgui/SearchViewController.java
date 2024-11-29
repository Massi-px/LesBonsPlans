package com.coding.app.appgui;

import com.coding.app.data.dao.AnnonceDao;
import com.coding.app.data.dao.RechercheDao;
import com.coding.app.data.model.Annonce;
import com.coding.app.data.model.Recherche;
import com.coding.app.dispacher.Dispatcher;
import com.coding.app.utils.SiteEnum;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import org.controlsfx.control.CheckComboBox;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SearchViewController {
    @FXML
    public CheckComboBox siteHistoryCheck;
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
    private final RechercheDao rechercheDao = new RechercheDao();
    ScheduledExecutorService scheduler;


    @FXML
    public void initialize() {
        HBox.setHgrow(spacer, Priority.ALWAYS);
        siteCheckComboBox.getItems().addAll("Les Bons Plans", "Le Bon Coin");
        listingsListView.setItems( observabled );
        keywordsField.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        siteCheckComboBox.getCheckModel().getCheckedItems().addListener((ListChangeListener<String>) change -> validateFields());
        refreshFrequencyField.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        // Initial validation
        validateFields();
        ContextMenu contextMenu = new ContextMenu();
        MenuItem    menuItem   = new MenuItem("save");
        contextMenu.getItems().addAll(menuItem);
        // Set context menu to the ListView
        listingsListView.setContextMenu(contextMenu);
        // Set action event handler for menuItem
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
        scheduler.shutdownNow();

    }

    @FXML
    protected void onStartSearchClick() {
        scheduler = Executors.newScheduledThreadPool(1);
        startSearchButton.setDisable(true);
        stopSearchButton.setDisable(false);
        String keywords = keywordsField.getText();
        var selectedSite = siteCheckComboBox.getCheckModel().getCheckedItems().stream().toList();

        for (String site : selectedSite) {
            if ("Les Bons Plans".equals(site)) {
                scheduler.scheduleAtFixedRate(() -> Platform.runLater(
                        () -> fillListings(keywords, SiteEnum.LES_BONS_PLANS)
                        ), 0, getRefreshFrequency(), TimeUnit.SECONDS);
            }
            if ("Le Bon Coin".equals(site)) {
                scheduler.scheduleAtFixedRate(() -> Platform.runLater(
                        () -> fillListings(keywords, SiteEnum.LE_BON_COIN)
                ), 0, getRefreshFrequency(), TimeUnit.SECONDS);
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

    @FXML
    private void onSaveSearchClick () {
        Recherche recherche = new Recherche();
        recherche.setKeywords(keywordsField.getText());
        recherche.setSites(siteCheckComboBox.getCheckModel().getCheckedItems().stream().toString());
        recherche.setFrequency(Integer.parseInt(refreshFrequencyField.getText()));
        rechercheDao.addRecherche(recherche);
    }

    @FXML
    private void onSearchHistoryClick() {
        List<Recherche> recherches = rechercheDao.getAllRecherches();
        for (Recherche recherche : recherches) {
            System.out.println(recherche);
        }
    }

    private void validateFields() {
        boolean isKeywordsFieldEmpty = keywordsField.getText().trim().isEmpty();
        boolean isSiteCheckComboBoxEmpty = siteCheckComboBox.getCheckModel().getCheckedItems().isEmpty();
        boolean isRefreshFrequencyFieldEmpty = refreshFrequencyField.getText().trim().isEmpty();

        // Disable the start button if any required field is empty
        startSearchButton.setDisable(isKeywordsFieldEmpty || isSiteCheckComboBoxEmpty || isRefreshFrequencyFieldEmpty);
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

    private void handleSaveAction() {
        onSaveSelectedClick();
    }


}