package com.coding.app.appgui;

import com.coding.app.data.dao.AnnonceDAO;
import com.coding.app.data.model.Annonce;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.controlsfx.control.CheckComboBox;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ManageListingsViewController {

    @FXML
    private ListView<Annonce> savedListingsListView;

    @FXML
    private CheckComboBox<String> siteFilterCheckComboBox;

    private final AnnonceDAO annonceDao = new AnnonceDAO();
    private ObservableList<Annonce> annonces;

    @FXML
    public void initialize() {
        annonces = FXCollections.observableArrayList(); // Initialize the annonces list
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> Platform.runLater(this::loadAnnonces), 0, 5, TimeUnit.MINUTES);
        // Add listener to CheckComboBox
        siteFilterCheckComboBox.getCheckModel().getCheckedItems().addListener((ListChangeListener<String>) change -> onFilterChange());
    }

    private void loadAnnonces() {
        List<Annonce> annonceList = annonceDao.getAll();
        annonces = FXCollections.observableArrayList(annonceList);
        savedListingsListView.setItems(annonces);
        setupSiteFilter(); // Call setupSiteFilter after loading annonces
    }

    private void setupSiteFilter() {
        List<String> sites = annonces.stream()
                .map(Annonce::getSite)
                .distinct()
                .collect(Collectors.toList());
        siteFilterCheckComboBox.getItems().setAll(sites);
    }

    @FXML
    private void onRefreshClick() {
        loadAnnonces();
    }

    @FXML
    private void onFilterChange() {
        List<String> selectedSites = siteFilterCheckComboBox.getCheckModel().getCheckedItems();
        if (selectedSites.isEmpty()) {
            savedListingsListView.setItems(annonces);
        } else {
            List<Annonce> filteredAnnonces = annonces.stream()
                    .filter(annonce -> selectedSites.contains(annonce.getSite()))
                    .collect(Collectors.toList());
            savedListingsListView.setItems(FXCollections.observableArrayList(filteredAnnonces));
        }
    }

    @FXML
    private void onDeleteSelectedClick() {
        Annonce selectedAnnonce = savedListingsListView.getSelectionModel().getSelectedItem();
        if (selectedAnnonce != null) {
            annonceDao.deleteById(selectedAnnonce.getId());
            annonces.remove(selectedAnnonce);
            savedListingsListView.setItems(annonces);
        }
    }
}