package com.coding.app.appgui;

import com.coding.app.data.dao.AnnonceDAO;
import com.coding.app.data.dao.RechercheDAO;
import com.coding.app.data.model.Annonce;
import com.coding.app.data.model.Recherche;
import com.coding.app.dispacher.Dispatcher;
import com.coding.app.utils.ApiUtils;
import com.coding.app.utils.SiteEnum;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import org.controlsfx.control.CheckComboBox;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SearchViewController {
    @FXML
    public TextField searchListingsField;
    @FXML
    public ComboBox<String> siteHistoryCheck;
    @FXML
    private TextField keywordsField;
    @FXML
    private CheckComboBox<String> siteComboBox;
    @FXML
    private TextField refreshFrequencyField;
    @FXML
    private ListView<Object> listingsListView;
    @FXML
    private Button startSearchButton;
    @FXML
    private Button stopSearchButton;
    @FXML
    private Region spacer;
    @FXML
    private Region spacer2;

    private final Dispatcher dispatcher = new Dispatcher();

    ObservableList<Object> observabled = FXCollections.observableArrayList();
    private final AnnonceDAO annonceDao = new AnnonceDAO();
    private final RechercheDAO rechercheDao = new RechercheDAO();
    ScheduledExecutorService scheduler;


    @FXML
    public void initialize() {
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        siteComboBox.getItems().addAll(
                SiteEnum.LES_BONS_PLANS.getName(),
                SiteEnum.LE_BON_COIN.getName()
        );
        listingsListView.setItems( observabled );
        keywordsField.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        siteComboBox.getCheckModel().getCheckedItems().addListener((ListChangeListener<String>) change -> validateFields());
        refreshFrequencyField.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        onSearchHistory();
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
        var selectedSite = siteComboBox.getCheckModel().getCheckedItems().stream().toList();

        for (String site : selectedSite) {
            if (SiteEnum.LES_BONS_PLANS.getName().equals(site)) {
                scheduler.scheduleAtFixedRate(() -> Platform.runLater(
                        () -> fillListings(keywords, SiteEnum.LES_BONS_PLANS)
                        ), 0, getRefreshFrequency(), TimeUnit.SECONDS);
            }
            if (SiteEnum.LE_BON_COIN.getName().equals(site)) {
                scheduler.scheduleAtFixedRate(() -> Platform.runLater(
                        () -> fillListings(keywords, SiteEnum.LE_BON_COIN)
                ), 0, getRefreshFrequency(), TimeUnit.SECONDS);
            }
        }
    }

    @FXML
    protected void onClearSearchClick() {
        observabled.clear();
        refreshFrequencyField.setText("1");
        siteComboBox.getCheckModel().clearChecks();

    }

    @FXML
    private void onSaveSelectedClick() {
        ObservableList<Object> selectedAnnonces = listingsListView.getSelectionModel().getSelectedItems();
        for (Object annonce : selectedAnnonces) {
            if (annonce instanceof Annonce a) {
                annonceDao.save(a);
            }
        }
    }

    @FXML
    private void onLoadSearch() {
        String selectedSearch = siteHistoryCheck.getValue();
        if (selectedSearch != null && !selectedSearch.isEmpty()) {
            String[] searchParts = selectedSearch.split(";");
            String keywords = searchParts[0].split("Keywords: ")[1];
            String sites = searchParts[1].split(" Sites: ")[1];
            int frequency = Integer.parseInt(searchParts[2].split(" Frequency: ")[1]);
            keywordsField.setText(keywords);
            refreshFrequencyField.setText(String.valueOf(frequency));
            siteComboBox.getCheckModel().clearChecks();
            String[] siteArray = sites.split(", ");
            for (String site : siteArray) {
                siteComboBox.getCheckModel().check(site);
            }
        }
    }

    @FXML
    private void onSaveSearchClick() {
        Recherche recherche = new Recherche();
        if(!keywordsField.getText().isEmpty() && !siteComboBox.getCheckModel().getCheckedItems().isEmpty() && !refreshFrequencyField.getText().isEmpty()) {
            recherche.setKeywords(keywordsField.getText());
            recherche.setSites(String.join(", ", siteComboBox.getCheckModel().getCheckedItems()));
            recherche.setFrequency(Integer.parseInt(refreshFrequencyField.getText()));
            rechercheDao.addRecherche(recherche);
            if (!siteHistoryCheck.getItems().contains("Keywords: " + recherche.getKeywords() + "; Sites: " + recherche.getSites() + "; Frequency: " + recherche.getFrequency())) {
                siteHistoryCheck.getItems().add("Keywords: " + recherche.getKeywords() + "; Sites: " + recherche.getSites() + "; Frequency: " + recherche.getFrequency());
            }
        }
    }

    private void onSearchHistory() {
        List<Recherche> recherches = rechercheDao.getAllRecherches();
        for (Recherche recherche : recherches) {
            siteHistoryCheck.getItems().add("Keywords: " + recherche.getKeywords() + "; Sites: " + recherche.getSites() + "; Frequency: " + recherche.getFrequency());
        }
    }

    private void validateFields() {
        boolean isKeywordsFieldEmpty = keywordsField.getText().trim().isEmpty();
        boolean isSiteCheckComboBoxEmpty = siteComboBox.getCheckModel().getCheckedItems().isEmpty();
        boolean isRefreshFrequencyFieldEmpty = refreshFrequencyField.getText().trim().isEmpty();
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


    public void onKeyPressedfilter(KeyEvent keyEvent) {
        String key = searchListingsField.getCharacters().toString();
        ObservableList<Object> filteredList = FXCollections.observableArrayList();
        for (Object annonce : observabled) {
            if (annonce instanceof Annonce a) {
                if (a.getTitle().toLowerCase( Locale.ROOT ).contains(key.toLowerCase( Locale.ROOT ))) {
                    filteredList.add(a);
                }
            } else {
                String a = (String) annonce;
                if (a.contains(key)) {
                    filteredList.add(annonce);
                }
            }
        }
        listingsListView.setItems(filteredList);
        if (key.isEmpty()) {
            listingsListView.setItems(observabled);
        }
    }
}