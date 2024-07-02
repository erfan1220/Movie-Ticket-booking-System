package org.example.movieticketbookingsystem;

import javafx.event.ActionEvent;

import java.io.IOException;

public class RegisterController {
    public void login(ActionEvent event) throws IOException {
        Switch s = new Switch();
        s.switchto(event , "hello-view.fxml");
    }

}
