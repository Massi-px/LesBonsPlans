package com.coding.app.appgui;

import com.coding.app.dispacher.Dispatcher;
import com.coding.app.utils.SiteEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
    }

    @FXML
    protected void onStartSearchClick() {

        String keywords = keywordsField.getText();
        var selectedSite = siteCheckComboBox.getCheckModel().getCheckedItems().stream().toList();

        String refreshFrequency = refreshFrequencyField.getText();

        for (String site : selectedSite) {
            if ("Les Bons Plans".equals(site)) {
                Thread.ofVirtual().start(() -> fillListings(keywords, SiteEnum.LES_BONS_PLANS));
//                Thread thread = new Thread(() -> fillListings(keywords, SiteEnum.LES_BONS_PLANS));
//                fillListings(keywords, SiteEnum.LES_BONS_PLANS);
            }
            if ("Le Bon Coin".equals(site)) {
                Thread.ofVirtual().start(() -> fillListings(keywords, SiteEnum.LE_BON_COIN));
                //fillListings(keywords, SiteEnum.LE_BON_COIN);
            }
        }

    }

    private final Dispatcher dispatcher = new Dispatcher();

    private void fillListings(String keywords, SiteEnum siteEnum) {
        dispatcher.dispatch( siteEnum, keywords, observabled );
    }

}