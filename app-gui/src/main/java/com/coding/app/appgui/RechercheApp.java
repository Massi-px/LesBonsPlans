package com.coding.app.appgui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class RechercheApp extends Application {

    // Stockage des configurations et des résultats pour simplification
    private static ArrayList<String> motsCles = new ArrayList<>();
    private static ArrayList<String> sitesChoisis = new ArrayList<>();
    private static int frequenceRafraichissement = 0;

    // Simuler les résultats récupérés (titre, lien, image, date, site)
    private static ArrayList<String[]> annonces = new ArrayList<>();

    public static void main(String[] args) {
        // Ajouter des annonces simulées
        annonces.add(new String[]{"Titre 1", "https://site1.com", "Image1.png", "2024-11-27", "Site 1"});
        annonces.add(new String[]{"Titre 2", "https://site2.com", "Image2.png", "2024-11-27", "Site 2"});
        annonces.add(new String[]{"Titre 3", "https://site3.com", "Image3.png", "2024-11-27", "Site 3"});

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestion des Recherches");

        // US1: Saisir des mots-clés
        Label     keywordsLabel = new Label("Mots-clés :");
        TextField keywordsField = new TextField();
        keywordsField.setPromptText("Entrez vos mots-clés ici...");

        // US2: Choisir les sites à analyser
        Label sitesLabel = new Label("Sélectionnez les sites :");
        CheckBox site1 = new CheckBox("Site 1");
        CheckBox site2 = new CheckBox("Site 2");
        CheckBox site3 = new CheckBox("Site 3");
        VBox sitesBox = new VBox(5, site1, site2, site3);

        // US3: Définir une fréquence de rafraîchissement
        Label refreshLabel = new Label("Fréquence de rafraîchissement (en minutes) :");
        TextField refreshField = new TextField();
        refreshField.setPromptText("Entrez une fréquence en minutes...");

        // Bouton pour sauvegarder
        Button saveButton = new Button("Sauvegarder la configuration");
        saveButton.setOnAction(e -> {
            motsCles.clear();
            motsCles.add(keywordsField.getText());

            sitesChoisis.clear();
            if (site1.isSelected()) sitesChoisis.add("Site 1");
            if (site2.isSelected()) sitesChoisis.add("Site 2");
            if (site3.isSelected()) sitesChoisis.add("Site 3");

            try {
                frequenceRafraichissement = Integer.parseInt(refreshField.getText());
            } catch (NumberFormatException ex) {
                frequenceRafraichissement = 0; // Valeur par défaut
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Configuration sauvegardée");
            alert.setHeaderText("Configuration de recherche enregistrée !");
            alert.setContentText("Mots-clés : " + String.join(", ", motsCles) + "\n"
                    + "Sites choisis : " + String.join(", ", sitesChoisis) + "\n"
                    + "Fréquence : " + frequenceRafraichissement + " minutes");
            alert.showAndWait();
        });

        // Bouton pour passer à l'interface des résultats
        Button showResultsButton = new Button("Afficher les résultats");
        showResultsButton.setOnAction(e -> {
            SwingUtilities.invokeLater( this::showResultsInterface );
        });

        // Mise en page
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(keywordsLabel, 0, 0);
        grid.add(keywordsField, 1, 0);
        grid.add(sitesLabel, 0, 1);
        grid.add(sitesBox, 1, 1);
        grid.add(refreshLabel, 0, 2);
        grid.add(refreshField, 1, 2);
        grid.add(saveButton, 0, 3);
        grid.add(showResultsButton, 1, 3);

        // Créer la scène
        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Interface Swing pour afficher les résultats
    private void showResultsInterface() {
        JFrame frame = new JFrame("Résultats des recherches");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);

        // Table pour afficher les annonces
        String[] columns = {"Titre", "Lien", "Image", "Date", "Site"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);

        // Ajouter les annonces dans la table
        for (String[] annonce : annonces) {
            tableModel.addRow(annonce);
        }

        // Bouton pour supprimer les annonces
        JButton deleteButton = new JButton("Supprimer les annonces sélectionnées");
        deleteButton.addActionListener(e -> {
            int[] selectedRows = table.getSelectedRows();
            for (int i = selectedRows.length - 1; i >= 0; i--) {
                tableModel.removeRow(selectedRows[i]);
                annonces.remove(selectedRows[i]);
            }
        });

        // Mise en page
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(deleteButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}

