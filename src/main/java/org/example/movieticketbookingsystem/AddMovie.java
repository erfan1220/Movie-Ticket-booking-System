package org.example.movieticketbookingsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

public class AddMovie implements Initializable {


    @FXML
    private TextField Cinema;

    @FXML
    private TextField Director;

    @FXML
    private TextField Duration;

    @FXML
    private TextField Genre;

    @FXML
    private TextField Time;

    @FXML
    private TextField Title;

    @FXML
    private ImageView imageview_movie;

    @FXML
    private TableColumn<MovieData, String> tableview_Cinema;

    @FXML
    private TableColumn<MovieData, String> tableview_Director;

    @FXML
    private TableColumn<MovieData, String> tableview_Duration;

    @FXML
    private TableColumn<MovieData, String> tableview_Genre;

    @FXML
    private TableColumn<MovieData, String> tableview_Time;

    @FXML
    private TableColumn<MovieData, String> tableview_Title;

    @FXML
    private TableView<MovieData> tableview_add_movie;

    @FXML
    private AnchorPane AddMovie_Form;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet resultSet;
    private Image image;
    private Alert alert;

    public void back(ActionEvent event) throws IOException {
        Switch s = new Switch();
        s.switchto(event, "Admin.fxml");
    }

    public void clear() {
        Title.setText("");
        Duration.setText("");
        Director.setText("");
        Time.setText("");
        Cinema.setText("");
        Genre.setText("");
        imageview_movie.setImage(null);
    }

    public ObservableList<MovieData> AddMovie() throws SQLException {
        ObservableList<MovieData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM movieadd";
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

    private ObservableList<MovieData> listAddMovie;

    public void showAddMovie() throws SQLException {
        listAddMovie = AddMovie();

        tableview_Title.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableview_Director.setCellValueFactory(new PropertyValueFactory<>("director"));
        tableview_Genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        tableview_Duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        tableview_Cinema.setCellValueFactory(new PropertyValueFactory<>("cinema"));
        tableview_Time.setCellValueFactory(new PropertyValueFactory<>("time"));

        tableview_add_movie.setItems(listAddMovie);
    }

    public void insert() throws SQLException {
        if (Title.getText().isEmpty() || Duration.getText().isEmpty() ||
                Director.getText().isEmpty() || Title.getText().isEmpty() ||
                Genre.getText().isEmpty() || Cinema.getText().isEmpty()
                || imageview_movie.getImage() == null) {
            alert = new Alert();
            alert.Error("Please fill out all field");
        } else {
            connect = Database.CODB();
            try {
                String insertdeta = "INSERT INTO movieadd" +
                        " (Title, Director, Genre, Duration, Cinema, Time, Image)" + "VALUES(?,?,?,?,?,?,?)";
                prepare = connect.prepareStatement(insertdeta);
                prepare.setString(1, Title.getText());
                prepare.setString(2, Director.getText());
                prepare.setString(3, Genre.getText());
                prepare.setString(4, Duration.getText());
                prepare.setString(5, Cinema.getText());
                prepare.setString(6, Time.getText());
                String uri = Static.path_Movie;
                uri = uri.replace("\\", "\\\\");
                prepare.setString(7, uri);

                prepare.executeUpdate();

                alert = new Alert();
                alert.information("Successfully Added.");

                showAddMovie();
                clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete() {
        String delet = "DELETE FROM movieadd WHERE Title = '" + Static.moviename + "' AND Time = '" +
                Static.movietime + "'";
        try {
            prepare = connect.prepareStatement(delet);
            prepare.executeUpdate();
            alert = new Alert();
            alert.information("Successfully deleted");

            showAddMovie();
            clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void update() throws SQLException {
        if(Title.getText().isEmpty()|| Duration.getText().isEmpty()||
                Director.getText().isEmpty()|| imageview_movie.getImage()==null ||
        Time.getText().isEmpty() || Genre.getText().isEmpty()||
        Cinema.getText().isEmpty()){
            alert = new Alert();
            alert.Error("Please fill out all field");
        }else {
            String url = Static.path_Movie;
            url = url.replace("\\", "\\\\");
            String Update = "UPDATE movieadd SET Title = '"+Title.getText()+"',  Director = '"+
                    Director.getText()+"', Genre = '"+Genre.getText()+"', Duration = '"+
                    Duration.getText()+"', Cinema = '"+Cinema.getText()+"', Time ='" +
                    Time.getText()+"', Image = '"+url+"' WHERE Title = '"+Static.moviename+"' AND " +
                    "Time = '"+Static.movietime+"' AND Cinema = '"+Static.cinema+"'";


            connect = Database.CODB();
            try{

                prepare = connect.prepareStatement(Update);
                prepare.executeUpdate();

                alert = new Alert();
                alert.information("Successfully updated.");

                showAddMovie();
                clear();
            }catch (Exception e){e.printStackTrace();}
        }
    }

    public void import_image() {
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*jpg", "*png"));
        Stage stage = (Stage) AddMovie_Form.getScene().getWindow();
        File file = open.showOpenDialog(stage);
        if (file != null) {
            image = new Image(file.toURI().toString(), 178, 175, false, true);
            imageview_movie.setImage(image);
            Static.path_Movie = file.getAbsolutePath();
        }
    }

    public void select() {
        MovieData MD = tableview_add_movie.getSelectionModel().getSelectedItem();
        int num = tableview_add_movie.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        Title.setText(MD.getTitle());
        Duration.setText(MD.getDuration());
        Director.setText(MD.getDirector());
        Genre.setText(MD.getGenre());
        Cinema.setText(MD.getCinema());
        Time.setText(MD.getTime());
        String uri = "file:" + MD.getImage();
        image = new Image(uri, 178, 175, false, true);
        imageview_movie.setImage(image);
        Static.moviename = MD.getTitle();
        Static.movietime = MD.getTime();
        //System.out.println(Static.id);
        Static.path_Movie = MD.getImage();
        Static.cinema = MD.getCinema();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showAddMovie();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
