package org.example.movieticketbookingsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {
    @FXML
    private TableColumn<?, ?> table_Cinema;

    @FXML
    private TableColumn<?, ?> table_Time;

    @FXML
    private TableColumn<?, ?> table_Title;
    @FXML
    private TableColumn<?, ?> table_Seat;

    @FXML
    private TableView<HistoryData> table_history;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet resultSet;
    private Alert alert;
    public void cancel(){
        String delet = "DELETE FROM history WHERE Cinema = '"+ Static.cinema+"' AND Time ='"+
                Static.movietime+"' AND MovieTitle ='"+Static.moviename+"' AND user ='"+
                Static.username+"'";
        try{
            prepare = connect.prepareStatement(delet);
            prepare.executeUpdate();

            delete();

            show();
        }catch (Exception e){e.printStackTrace();}
    }
    public void delete() throws SQLException {
        String sql = "DELETE FROM seat WHERE MovieTitle ='"+Static.moviename+"' AND Cinema ='" +
                Static.cinema+"' AND Time ='"+Static.movietime+"' AND seat ='"+Static.seat+"'";
        connect = Database.CODB();
        try{
            prepare = connect.prepareStatement(sql);
            prepare.executeUpdate();
            
            alert = new Alert();
            alert.information("Successfully deleted");
        }catch (Exception e){e.printStackTrace();}
    }
    public void select(){
        HistoryData HD = table_history.getSelectionModel().getSelectedItem();
        int num = table_history.getSelectionModel().getSelectedIndex();
        if((num - 1) < -1){
            return;
        }
        Static.cinema = HD.getCinema();
        Static.moviename = HD.getTitle();
        Static.movietime = HD.getTime();
        Static.seat = HD.getSeat();
    }
    public ObservableList<HistoryData> history() throws SQLException {
        ObservableList<HistoryData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM history WHERE user ='"+Static.username+"'";
        connect = Database.CODB();

        try{
            prepare = connect.prepareStatement(sql);
            resultSet = prepare.executeQuery();
            HistoryData HD;
            while (resultSet.next()){
                HD = new HistoryData();
                HD.setTitle(resultSet.getString("MovieTitle"));
                HD.setTime(resultSet.getString("Time"));
                HD.setCinema(resultSet.getString("Cinema"));
                HD.setUser(resultSet.getString("user"));
                HD.setSeat(resultSet.getString("Seat"));

                listData.add(HD);
            }

        }catch (Exception e){e.printStackTrace();}
        return listData;
    }
    private ObservableList<HistoryData> list;
    public void show() throws SQLException {
        list = history();

        table_Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        table_Cinema.setCellValueFactory(new PropertyValueFactory<>("Cinema"));
        table_Time.setCellValueFactory(new PropertyValueFactory<>("Time"));
        table_Seat.setCellValueFactory(new PropertyValueFactory<>("seat"));

        table_history.setItems(list);
    }
    public void back(ActionEvent event) throws IOException {
        ProfileController pc = new ProfileController();
        pc.back(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
