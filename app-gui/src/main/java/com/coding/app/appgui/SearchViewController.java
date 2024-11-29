package com.coding.app.appgui;

import com.coding.app.data.dao.AnnonceDao;
import com.coding.app.data.model.Annonce;
import com.coding.app.dispacher.Dispatcher;
import com.coding.app.utils.SiteEnum;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import org.controlsfx.control.CheckComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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

    private final Dispatcher dispatcher = new Dispatcher();

    ObservableList<Annonce> observabled = FXCollections.observableArrayList();

    private long refresh;

    @FXML
    public void initialize() {

        refresh = 1L;

        siteCheckComboBox.getItems().addAll("Les Bons Plans", "Le Bon Coin");
        listingsListView.setItems( observabled );

        // Create context menu
        ContextMenu contextMenu = new ContextMenu();
        MenuItem    menuItem   = new MenuItem("save");
        contextMenu.getItems().addAll(menuItem);

        // Set context menu to the ListView
        listingsListView.setContextMenu(contextMenu);
        // Set action event handler for menuItem
        menuItem.setOnAction(event -> handleSaveAction());
    }

    @FXML
    protected void onStartSearchClick() {
        String keywords = keywordsField.getText();
        var selectedSite = siteCheckComboBox.getCheckModel().getCheckedItems().stream().toList();

        String refreshFrequency = refreshFrequencyField.getText();

        if (refreshFrequency!= null &&!refreshFrequency.isEmpty()) {
            refresh = Long.parseLong(refreshFrequency) * 1000;
        }

        for (String site : selectedSite) {
            if ("Les Bons Plans".equals(site)) {
                fillListings(keywords, SiteEnum.LES_BONS_PLANS);
            }
            if ("Le Bon Coin".equals(site)) {
                fillListings(keywords, SiteEnum.LE_BON_COIN);
            }
        }
    }
    private final AnnonceDao annonceDao = new AnnonceDao();

    @FXML
    private void onSaveSelectedClick() {
        List<Annonce> selectedAnnonces = listingsListView.getSelectionModel().getSelectedItems();
        for (Annonce annonce : selectedAnnonces) {
            annonceDao.saveAnnonce( annonce );
        }
    }

    private void fillListings(String keywords, SiteEnum siteEnum) {

        long current = System.currentTimeMillis();
        long end = current + refresh * 1000;
//        while (System.currentTimeMillis() < end) {
//            Thread.sleep(1000);
//        }

        Platform.runLater(new Runnable() {public void run() {
            dispatcher.dispatch( siteEnum, keywords, observabled );
        }});

    }

    // Action event handler for menuItem saved state
    private void handleSaveAction() {
        onSaveSelectedClick();
    }


}