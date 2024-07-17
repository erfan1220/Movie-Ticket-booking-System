package org.example.movieticketbookingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterController {
    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phonenumber;

    @FXML
    private TextField username;
    private Alert alert;
    Connection connect;
    PreparedStatement prepare;

    public void login(ActionEvent event) throws IOException {
        Switch s = new Switch();
        s.switchto(event , "hello-view.fxml");
    }

    public void createbtn(ActionEvent event) throws SQLException, IOException {
        if(email.getText().isEmpty() || password.getText().isEmpty() ||
        phonenumber.getText().isEmpty() || username.getText().isEmpty()){
            alert = new Alert();
            alert.Error("Please fill out all items");
        }else{
            String create = "INSERT INTO information (username,password,phone,email)" +
                    "VALUES(?,?,?,?)";
            connect = Database.CODB();
            Static.usernameC = username.getText();
            Email il = new Email();
            Static s = new Static();
            il.email("sltanyh468@gmail.com","mepm qybe pevi rxgd",
                    email.getText(),s.subject,s.text);
            prepare = connect.prepareStatement(create);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());
            prepare.setString(3, phonenumber.getText());
            prepare.setString(4, email.getText());
            prepare.executeUpdate();

            alert = new Alert();
            alert.information("Successfully registered Account.");

            username.setText("");
            password.setText("");
            phonenumber.setText("");
            email.setText("");
            login(event);
        }
    }
}
