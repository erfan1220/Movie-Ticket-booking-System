package org.example.movieticketbookingsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserController extends RegisterController implements Initializable {
    @FXML
    private TableColumn<?, ?> Address_table;

    @FXML
    private ImageView Cinema_information_imageview;

    @FXML
    private TableView<CinemaData> Cinema_information_table;

    @FXML
    private TableColumn<?, ?> Cinema_table;

    @FXML
    private TableColumn<?, ?> Director_table;

    @FXML
    private TableColumn<?, ?> Duration_table;

    @FXML
    private AnchorPane Form;

    @FXML
    private TableColumn<?, ?> Genre_table;

    @FXML
    private ImageView Movie_information_imageview;

    @FXML
    private TableView<MovieData> Movie_information_table;

    @FXML
    private TableColumn<?, ?> Name_table;

    @FXML
    private TableColumn<?, ?> Rate_table;

    @FXML
    private TableColumn<?, ?> Time_table;

    @FXML
    private TableColumn<?, ?> Title_table;

    @FXML
    private Label label;

    @FXML
    private Label cinema_name_lable;
    private ResultSet resultSet;
    private Image image_Cinema;
    private Image image_Movie;
    public void Profile(ActionEvent event) throws IOException {
        Switch s = new Switch();
        s.switchto(event, "Profile.fxml");
    }
    public void display(){
        label.setText(Static.username);
    }

    public ObservableList<CinemaData> Cinema() throws SQLException {
        ObservableList<CinemaData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM addcinema";
        connect = Database.CODB();

        try{
            prepare = connect.prepareStatement(sql);
            resultSet = prepare.executeQuery();
            CinemaData CD;
            while (resultSet.next()){
                CD = new CinemaData();
                CD.setName(resultSet.getString("CinemaName"));
                CD.setAddress(resultSet.getString("CinemaAddress"));
                CD.setRate(resultSet.getString("CinemaRate"));
                CD.setImage(resultSet.getString("CinemaImage"));

                listData.add(CD);
            }

        }catch (Exception e){e.printStackTrace();}
        return listData;
    }
    private ObservableList<CinemaData> listCiname;
    public void showCinema() throws SQLException {
        listCiname = Cinema();

        Name_table.setCellValueFactory(new PropertyValueFactory<>("name"));
        Address_table.setCellValueFactory(new PropertyValueFactory<>("address"));
        Rate_table.setCellValueFactory(new PropertyValueFactory<>("rate"));

        Cinema_information_table.setItems(listCiname);
    }
    public void go(){
        cinema_name_lable.setText(Static.cinema);
        try {
            showMovie();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void select(){
        CinemaData CD = Cinema_information_table.getSelectionModel().getSelectedItem();
        int num = Cinema_information_table.getSelectionModel().getSelectedIndex();
        if((num - 1) < -1){
            return;
        }
        String uri = "file:"+CD.getImage();
        image_Cinema = new Image(uri,178,175,false,true);
        Cinema_information_imageview.setImage(image_Cinema);
        Static.cinema = CD.getName();
    }

    public ObservableList<MovieData> Movie() throws SQLException {
        ObservableList<MovieData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM movieadd WHERE Cinema LIKE '%"+cinema_name_lable.getText()+"%'";
        connect = Database.CODB();

        try {
            prepare = connect.prepareStatement(sql);
            resultSet = prepare.executeQuery();
            MovieData MD;
            while (resultSet.next()) {
                MD = new MovieData();

                MD.setTitle(resultSet.getString("Title"));
                MD.setDirector(resultSet.getString("Director"));
                MD.setGenre(resultSet.getString("Genre"));
                MD.setDuration(resultSet.getString("Duration"));
                MD.setCinema(resultSet.getString("Cinema"));
                MD.setTime(resultSet.getString("Time"));
                MD.setImage(resultSet.getString("Image"));

                listData.add(MD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<MovieData> listMovie;

    public void showMovie() throws SQLException {
        listMovie = Movie();

        Title_table.setCellValueFactory(new PropertyValueFactory<>("title"));
        Director_table.setCellValueFactory(new PropertyValueFactory<>("director"));
        Genre_table.setCellValueFactory(new PropertyValueFactory<>("genre"));
        Duration_table.setCellValueFactory(new PropertyValueFactory<>("duration"));
        Cinema_table.setCellValueFactory(new PropertyValueFactory<>("cinema"));
        Time_table.setCellValueFactory(new PropertyValueFactory<>("time"));

        Movie_information_table.setItems(listMovie);
    }
    public void select_movie(){
        MovieData MD = Movie_information_table.getSelectionModel().getSelectedItem();
        int num = Movie_information_table.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        String uri = "file:" + MD.getImage();
        image_Movie = new Image(uri, 178, 175, false, true);
        Movie_information_imageview.setImage(image_Movie);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        display();
        try {
            showCinema();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            showMovie();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
