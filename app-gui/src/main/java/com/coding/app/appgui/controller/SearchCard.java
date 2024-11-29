package com.coding.app.appgui.controller;


import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.awt.*;

public class SearchCard extends CardLayout {


    private Label titre = new Label();
    private Label lien = new Label();
    private Label site = new Label();

    public SearchCard(String titre, String lien, String site) {
        this.titre.setText(titre);
        this.lien.setText(lien);
        this.site.setText(site);
    }

}
