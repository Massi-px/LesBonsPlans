package com.coding.app.appgui;

import com.coding.app.data.dao.AnnonceDao;
import com.coding.app.data.model.Annonce;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.util.List;
import java.util.stream.Collectors;

public class ManageListingsViewController {

    @FXML
    private ListView<Annonce> savedListingsListView;

    @FXML
    private ComboBox<String> siteFilterComboBox;

    private final AnnonceDao annonceDao = new AnnonceDao();
    private ObservableList<Annonce> annonces;

    @FXML
    public void initialize() {
        loadAnnonces();
        setupSiteFilter();
    }

    private void loadAnnonces() {
        List<Annonce> annonceList = annonceDao.getAllAnnonces();
        annonces = FXCollections.observableArrayList(annonceList);
        savedListingsListView.setItems(annonces);
    }

    private void setupSiteFilter() {
        List<String> sites = annonces.stream()
                .map(Annonce::getSite)
                .distinct()
                .collect(Collectors.toList());
        siteFilterComboBox.setItems(FXCollections.observableArrayList(sites));
    }

    @FXML
    private void onFilterChange() {
        String selectedSite = siteFilterComboBox.getValue();
        if (selectedSite == null || selectedSite.isEmpty()) {
            savedListingsListView.setItems(annonces);
        } else {
            List<Annonce> filteredAnnonces = annonces.stream()
                    .filter(annonce -> annonce.getSite().equals(selectedSite))
                    .collect(Collectors.toList());
            savedListingsListView.setItems(FXCollections.observableArrayList(filteredAnnonces));
        }
    }

    @FXML
    private void onDeleteSelectedClick() {
        Annonce selectedAnnonce = savedListingsListView.getSelectionModel().getSelectedItem();
        if (selectedAnnonce != null) {
            annonceDao.deleteAnnonce(selectedAnnonce.getId());
            annonces.remove(selectedAnnonce);
            savedListingsListView.setItems(annonces);
        }
    }
}