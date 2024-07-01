package org.example.movieticketbookingsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Movie Ticket Booking System");
        //Image image = new Image("file:src/images.jpg");
        //stage.getIcons().add(image);
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();
    }
}