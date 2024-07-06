package org.example.movieticketbookingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserController extends RegisterController implements Initializable {
    @FXML
    private Label label;
    public void Profile(ActionEvent event) throws IOException {

        Switch s = new Switch();
        s.switchto(event, "Profile.fxml");
    }
    public void display(){
        label.setText(Static.username);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        display();
    }
}
