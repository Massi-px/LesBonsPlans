package com.coding.app.appgui;

import com.coding.app.data.dao.AnnonceDao;
import com.coding.app.data.model.Annonce;
import com.coding.app.dispacher.Dispacher;
import com.coding.app.utils.SiteEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

    @FXML
    public void initialize() {
        siteCheckComboBox.getItems().addAll("LesBonsPlans", "LeBonCoin");
    }

    @FXML
    protected void onStartSearchClick() {
        String keywords = keywordsField.getText();
        String selectedSite = siteCheckComboBox.getCheckModel().getCheckedItems().get(0);

        if ("LesBonsPlans".equals(selectedSite)) {
            fetchLesBonsPlansListings(keywords);
        }
        if ("LeBonCoin".equals(selectedSite)) {
            fetchLeBonCoinListings(keywords);
        }
    }
    private final AnnonceDao annonceDao = new AnnonceDao();

    @FXML
    private void onSaveSelectedClick() {
        List<Annonce> selectedAnnonces = listingsListView.getSelectionModel().getSelectedItems();
        for (Annonce annonce : selectedAnnonces) {
            annonceDao.saveAnnonce(annonce);
        }
    }

    private void displayListings(List<Annonce> annonces) {
        ObservableList<Annonce> listings = FXCollections.observableArrayList(annonces);
        listingsListView.setItems(listings);
    }

    private final Dispacher dispacher = new Dispacher();

    private void fetchLesBonsPlansListings(String keywords) {
        var siteWrapper = dispacher.dispach( SiteEnum.LES_BONS_PLANS );
        siteWrapper.setParams( keywords );
        var liste = siteWrapper.search();

        displayListings(liste );

    }
    private void fetchLeBonCoinListings(String keywords) {
        var siteWrapper = dispacher.dispach( SiteEnum.LE_BON_COIN );
        siteWrapper.setParams( keywords );
        var liste = siteWrapper.search();

        displayListings(liste );

    }
}