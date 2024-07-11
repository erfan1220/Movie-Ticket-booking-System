package org.example.movieticketbookingsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SeatController extends SeatController2 implements Initializable {
    @FXML
    private Button Eight;

    @FXML
    private Button Eighteen;

    @FXML
    private Button Eleven;

    @FXML
    private Button F_eight;

    @FXML
    private Button F_five;

    @FXML
    private Button F_four;

    @FXML
    private Button F_nine;

    @FXML
    private Button F_one;

    @FXML
    private Button F_seven;

    @FXML
    private Button F_six;

    @FXML
    private Button F_three;

    @FXML
    private Button F_two;

    @FXML
    private Button Fifteen;

    @FXML
    private Button Fifty;

    @FXML
    private Button Fifty_four;

    @FXML
    private Button Fifty_one;

    @FXML
    private Button Fifty_three;

    @FXML
    private Button Fifty_two;

    @FXML
    private Button Five;

    @FXML
    private Button Forty;

    @FXML
    private Button Four;

    @FXML
    private Button Fourteen;

    @FXML
    private Button Nine;

    @FXML
    private Button Nineteen;

    @FXML
    private Button One;

    @FXML
    private Button Seven;

    @FXML
    private Button Seventeen;

    @FXML
    private Button Six;

    @FXML
    private Button Sixteen;

    @FXML
    private Button T_eight;

    @FXML
    private Button T_five;

    @FXML
    private Button T_four;

    @FXML
    private Button T_nine;

    @FXML
    private Button T_one;

    @FXML
    private Button T_s;

    @FXML
    private Button T_six;

    @FXML
    private Button T_three;

    @FXML
    private Button T_two;

    @FXML
    private Button Ten;

    @FXML
    private Button Th_eight;

    @FXML
    private Button Th_five;

    @FXML
    private Button Th_four;

    @FXML
    private Button Th_nine;

    @FXML
    private Button Th_one;

    @FXML
    private Button Th_seven;

    @FXML
    private Button Th_six;

    @FXML
    private Button Th_three;

    @FXML
    private Button Th_two;

    @FXML
    private Button Thirteen;

    @FXML
    private Button Thirty;

    @FXML
    private Button Three;

    @FXML
    private Button Twelve;

    @FXML
    private Button Twenty;

    @FXML
    private Button Two;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet resultSet;
    public void sh() throws SQLException {
        Button[] a = {One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Eleven, Twelve, Thirteen,
                Fourteen, Fifteen, Sixteen, Seventeen, Eighteen, Nineteen, Twenty, T_one, T_two, T_three, T_four,
                T_five, T_six, T_s, T_eight, T_nine, Thirty, Th_one, Th_two, Th_three, Th_four, Th_five,
                Th_six, Th_seven, Th_eight, Th_eight, Th_nine, Forty, F_one, F_two, F_three, F_four,
                F_five, F_six, F_seven, F_eight, F_nine, Fifty, Fifty_one, Fifty_two, Fifty_three, Fifty_four};
        for(int i=1; i<56; i++){
            String sql = "SELECT Seat FROM seat WHERE Cinema = '" + Static.cinema + "' AND Time = '"
                    + Static.movietime + "' AND MovieTitle = '" + Static.moviename + "' AND " +
                    "Seat = '" + i + "'";
            connect = Database.CODB();
            try{
                prepare = connect.prepareStatement(sql);
                resultSet = prepare.executeQuery();
                if (resultSet.next()) {
                    a[i - 1].setStyle("-fx-background-color :red;");
                } else {
                    a[i - 1].setStyle("-fx-background-color :green;");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            sh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}