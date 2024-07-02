package org.example.movieticketbookingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
    }
    public void Createaccount(ActionEvent event) throws IOException {
        Switch s = new Switch();
        s.switchto(event , "CreateAccount.fxml");
    }
}