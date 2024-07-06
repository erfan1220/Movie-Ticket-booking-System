package org.example.movieticketbookingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    private Alert alert;
    Connection connect;
    PreparedStatement prepare;

    @FXML
    private TextField PersonalEmail;

    @FXML
    private TextField PersonalPassword;

    @FXML
    private TextField PersonalPhone;

    @FXML
    private TextField PersonalUsername;

    public void back(ActionEvent event) throws IOException {
        Switch s = new Switch();
        s.switchto(event,"User.fxml");
    }

    public void Update(ActionEvent event) throws IOException {
        if(PersonalEmail.getText().isEmpty() || PersonalPassword.getText().isEmpty()
        || PersonalPhone.getText().isEmpty() || PersonalUsername.getText().isEmpty()){
            alert = new Alert();
            alert.Error("Please fill out all field");
        }else{
            try {
                connect = Database.CODB();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try{
                String updatep = "UPDATE information SET password = '" + PersonalPassword.getText()+
                        "', phone ='"+PersonalPhone.getText()+"' , email = '" +PersonalEmail.getText()+
                        "' WHERE username = '" +PersonalUsername.getText()+"'";
                prepare = connect.prepareStatement(updatep);
                prepare.executeUpdate();
                alert = new Alert();
                alert.information("Successfully updated.");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        back(event);
    }
    public void display(){
        PersonalUsername.setText(Static.username);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        display();
    }
}
