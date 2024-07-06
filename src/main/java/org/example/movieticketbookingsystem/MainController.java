package org.example.movieticketbookingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainController {
    PreparedStatement prepare;
    Connection connect;
    ResultSet resultSet;
    private Alert alert;
    @FXML
    private PasswordField loginPass;

    @FXML
    private TextField loginusername;

    public void Createaccount(ActionEvent event) throws IOException {
        Switch s = new Switch();
        s.switchto(event, "CreateAccount.fxml");
    }

    public void login(ActionEvent event) throws IOException {

        Static.username = loginusername.getText();
        if (loginPass.getText().isEmpty() || loginusername.getText().isEmpty()) {
            alert = new Alert();
            alert.Error("Invalid information.");
        } else {
            String logdeta = "SELECT username, password FROM information WHERE username = ? and password = ? " +
                    "and role = 'user'";
            String logindeta = "SELECT username, password FROM information WHERE username = ? and password = ? " +
                    "and role = 'admin'";
            try {
                connect = Database.CODB();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                prepare = connect.prepareStatement(logdeta);
                prepare.setString(1, loginusername.getText());
                prepare.setString(2, loginPass.getText());
                resultSet = prepare.executeQuery();
                if (resultSet.next()) {
                    Switch s1 = new Switch();
                    s1.switchto(event, "User.fxml");
                } else {
                    prepare = connect.prepareStatement(logindeta);
                    prepare.setString(1, loginusername.getText());
                    prepare.setString(2, loginPass.getText());
                    resultSet = prepare.executeQuery();
                    if (resultSet.next()) {
                        Switch s1 = new Switch();
                        s1.switchto(event, "Admin.fxml");
                    } else {
                        alert = new Alert();
                        alert.Error("Invalid information.");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
