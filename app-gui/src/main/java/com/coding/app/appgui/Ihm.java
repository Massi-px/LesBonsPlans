package com.coding.app.appgui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ihm extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestion des Recherches");

        // US1: Saisir des mots-clés
        Label keywordsLabel = new Label("Mots-clés :");
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
            String keywords = keywordsField.getText();
            StringBuilder selectedSites = new StringBuilder();
            if (site1.isSelected()) selectedSites.append("Site 1 ");
            if (site2.isSelected()) selectedSites.append("Site 2 ");
            if (site3.isSelected()) selectedSites.append("Site 3 ");
            String refreshRate = refreshField.getText();

            // Afficher les informations de configuration
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Configuration sauvegardée");
            alert.setHeaderText("Détails de la configuration :");
            alert.setContentText("Mots-clés : " + keywords + "\n"
                    + "Sites sélectionnés : " + selectedSites + "\n"
                    + "Fréquence : " + refreshRate + " minutes");
            alert.showAndWait();
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
        grid.add(saveButton, 1, 3);

        // Créer la scène
        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
