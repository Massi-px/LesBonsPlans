package com.coding.app.appgui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SearchCardController {


    @FXML public Label titre;
    @FXML public Label lien;
    @FXML public Label site;

    public SearchCardController(String titre, String lien, String site) {
        this.titre.setText(titre);
        this.lien.setText(lien);
        this.site.setText(site);
    }
}
