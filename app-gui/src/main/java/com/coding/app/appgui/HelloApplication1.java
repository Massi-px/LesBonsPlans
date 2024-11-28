package com.coding.app.appgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication1 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new MainView( 20 ), 1200, 700);
        stage.centerOnScreen(); // Centre the window on the screen
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}