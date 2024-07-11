package org.example.movieticketbookingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatController2 extends ProfileController {
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

    private PreparedStatement prepare;
    private Connection connect;
    private ResultSet resultSet;
    private Alert alert;

    public void Button(ActionEvent event) throws SQLException {
        connect = Database.CODB();
        try {
            String sql = "SELECT Seat FROM seat WHERE Cinema = '" +
                    Static.cinema + "' AND MovieTitle = '" + Static.moviename + "' AND " +
                    "Time = '" + Static.movietime + "' AND Seat = '"+Static.seat+"'";
            prepare = connect.prepareStatement(sql);
            resultSet = prepare.executeQuery();
            if (resultSet.next()) {
                alert = new Alert();
                alert.Error("This place has already been booked.");
            } else {
                String mysql = "INSERT INTO seat (Cinema , MovieTitle, Time,Seat)" +
                        "VALUES(?,?,?,?)";
                prepare = connect.prepareStatement(mysql);
                prepare.setString(1, Static.cinema);
                prepare.setString(2, Static.moviename);
                prepare.setString(3, Static.movietime);
                prepare.setString(4, Static.seat);
                prepare.executeUpdate();

                insert();

                alert = new Alert();
                alert.information("Successful.");

                back(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void insert() throws SQLException {
        String sql = "INSERT INTO history (MovieTitle, Time,Cinema,user,Seat)" +
                "VALUES(?,?,?,?,?)";
        prepare = connect.prepareStatement(sql);
        prepare.setString(1, Static.moviename);
        prepare.setString(2, Static.movietime);
        prepare.setString(3, Static.cinema);
        prepare.setString(4, Static.username);
        prepare.setString(5,Static.seat);
        prepare.executeUpdate();
    }

    public void b1(ActionEvent event) throws SQLException {
        Static.seat = One.getText();
        Button(event);
    }

    public void b2(ActionEvent event) throws SQLException {
        Static.seat = Two.getText();
        Button(event);
    }

    public void b3(ActionEvent event) throws SQLException {
        Static.seat = Three.getText();
        Button(event);
    }

    public void b4(ActionEvent event) throws SQLException {
        Static.seat = Four.getText();
        Button(event);
    }

    public void b5(ActionEvent event) throws SQLException {
        Static.seat = Five.getText();
        Button(event);
    }

    public void b6(ActionEvent event) throws SQLException {
        Static.seat = Six.getText();
        Button(event);
    }

    public void b7(ActionEvent event) throws SQLException {
        Static.seat = Seven.getText();
        Button(event);
    }

    public void b8(ActionEvent event) throws SQLException {
        Static.seat = Eight.getText();
        Button(event);
    }

    public void b9(ActionEvent event) throws SQLException {
        Static.seat = Nine.getText();
        Button(event);
    }

    public void b10(ActionEvent event) throws SQLException {
        Static.seat = Ten.getText();
        Button(event);
    }

    public void b11(ActionEvent event) throws SQLException {
        Static.seat = Eleven.getText();
        Button(event);
    }

    public void b12(ActionEvent event) throws SQLException {
        Static.seat = Twelve.getText();
        Button(event);
    }

    public void b13(ActionEvent event) throws SQLException {
        Static.seat = Thirteen.getText();
        Button(event);
    }

    public void b14(ActionEvent event) throws SQLException {
        Static.seat = Fourteen.getText();
        Button(event);
    }

    public void b15(ActionEvent event) throws SQLException {
        Static.seat = Fifteen.getText();
        Button(event);
    }

    public void b16(ActionEvent event) throws SQLException {
        Static.seat = Sixteen.getText();
        Button(event);
    }

    public void b17(ActionEvent event) throws SQLException {
        Static.seat = Seventeen.getText();
        Button(event);
    }

    public void b18(ActionEvent event) throws SQLException {
        Static.seat = Eighteen.getText();
        Button(event);
    }

    public void b19(ActionEvent event) throws SQLException {
        Static.seat = Nineteen.getText();
        Button(event);
    }

    public void b20(ActionEvent event) throws SQLException {
        Static.seat = Twenty.getText();
        Button(event);
    }

    public void b21(ActionEvent event) throws SQLException {
        Static.seat = T_one.getText();
        Button(event);
    }

    public void b22(ActionEvent event) throws SQLException {
        Static.seat = T_two.getText();
        Button(event);
    }

    public void b23(ActionEvent event) throws SQLException {
        Static.seat = T_three.getText();
        Button(event);
    }

    public void b24(ActionEvent event) throws SQLException {
        Static.seat = T_four.getText();
        Button(event);
    }

    public void b25(ActionEvent event) throws SQLException {
        Static.seat = T_five.getText();
        Button(event);
    }

    public void b26(ActionEvent event) throws SQLException {
        Static.seat = T_six.getText();
        Button(event);
    }

    public void b27(ActionEvent event) throws SQLException {
        Static.seat = T_s.getText();
        Button(event);
    }

    public void b28(ActionEvent event) throws SQLException {
        Static.seat = T_eight.getText();
        Button(event);
    }

    public void b29(ActionEvent event) throws SQLException {
        Static.seat = T_nine.getText();
        Button(event);
    }

    public void b30(ActionEvent event) throws SQLException {
        Static.seat = Thirty.getText();
        Button(event);
    }

    public void b31(ActionEvent event) throws SQLException {
        Static.seat = Th_one.getText();
        Button(event);
    }

    public void b32(ActionEvent event) throws SQLException {
        Static.seat = Th_two.getText();
        Button(event);
    }

    public void b33(ActionEvent event) throws SQLException {
        Static.seat = Th_three.getText();
        Button(event);
    }

    public void b34(ActionEvent event) throws SQLException {
        Static.seat = Th_four.getText();
        Button(event);
    }

    public void b35(ActionEvent event) throws SQLException {
        Static.seat = Th_five.getText();
        Button(event);
    }

    public void b36(ActionEvent event) throws SQLException {
        Static.seat = Th_six.getText();
        Button(event);
    }

    public void b37(ActionEvent event) throws SQLException {
        Static.seat = Th_seven.getText();
        Button(event);
    }

    public void b38(ActionEvent event) throws SQLException {
        Static.seat = Th_eight.getText();
        Button(event);
    }

    public void b39(ActionEvent event) throws SQLException {
        Static.seat = Th_nine.getText();
        Button(event);
    }

    public void b40(ActionEvent event) throws SQLException {
        Static.seat = Forty.getText();
        Button(event);
    }

    public void b41(ActionEvent event) throws SQLException {
        Static.seat = F_one.getText();
        Button(event);
    }

    public void b42(ActionEvent event) throws SQLException {
        Static.seat = F_two.getText();
        Button(event);
    }

    public void b43(ActionEvent event) throws SQLException {
        Static.seat = F_three.getText();
        Button(event);
    }

    public void b44(ActionEvent event) throws SQLException {
        Static.seat = F_four.getText();
        Button(event);
    }

    public void b45(ActionEvent event) throws SQLException {
        Static.seat = F_five.getText();
        Button(event);
    }

    public void b46(ActionEvent event) throws SQLException {
        Static.seat = F_six.getText();
        Button(event);
    }

    public void b47(ActionEvent event) throws SQLException {
        Static.seat = F_seven.getText();
        Button(event);
    }

    public void b48(ActionEvent event) throws SQLException {
        Static.seat = F_eight.getText();
        Button(event);
    }

    public void b49(ActionEvent event) throws SQLException {
        Static.seat = F_nine.getText();
        Button(event);
    }

    public void b50(ActionEvent event) throws SQLException {
        Static.seat = Fifty.getText();
        Button(event);
    }

    public void b51(ActionEvent event) throws SQLException {
        Static.seat = Fifty_one.getText();
        Button(event);
    }

    public void b52(ActionEvent event) throws SQLException {
        Static.seat = Fifty_two.getText();
        Button(event);
    }

    public void b53(ActionEvent event) throws SQLException {
        Static.seat = Fifty_three.getText();
        Button(event);
    }

    public void b54(ActionEvent event) throws SQLException {
        Static.seat = Fifty_four.getText();
        Button(event);
    }
}