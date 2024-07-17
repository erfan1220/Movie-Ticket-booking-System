package org.example.movieticketbookingsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
import java.nio.channels.FileChannel;
import java.sql.*;
import java.util.ResourceBundle;

public class Admin extends RegisterController implements Initializable {
    @FXML
    private ImageView add_cinema_imageview;

    @FXML
    private Button addmovie_btn;

    @FXML
    private TableColumn<CinemaData, String> address_tableview;

    @FXML
    private TextField cinema_address;

    @FXML
    private TextField cinema_name;

    @FXML
    private TextField cinema_rate;

    @FXML
    private TableView<CinemaData> cinema_tableview;

    @FXML
    private TableColumn<CinemaData, String> cinemaname_tableview;

    @FXML
    private TableColumn<CinemaData, String> rate_tableview;

    @FXML
    private AnchorPane AddCinema_Form;


    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet resultSet;
    private Image image;
    private Alert alert;

    public ObservableList<CinemaData> AddCinema() throws SQLException {
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
    private ObservableList<CinemaData> listAddCiname;
    public void showAddCinema() throws SQLException {
        listAddCiname = AddCinema();

        cinemaname_tableview.setCellValueFactory(new PropertyValueFactory<>("name"));
        address_tableview.setCellValueFactory(new PropertyValueFactory<>("address"));
        rate_tableview.setCellValueFactory(new PropertyValueFactory<>("rate"));

        cinema_tableview.setItems(listAddCiname);
    }
    public void import_image() {
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File" , "*jpg","*png"));
        Stage stage = (Stage)AddCinema_Form.getScene().getWindow();
        File file = open.showOpenDialog(stage);
        if(file != null){
            image = new Image(file.toURI().toString(),178 , 175,false,true);
            add_cinema_imageview.setImage(image);
            Static.path = file.getAbsolutePath();
        }
    }
    public void insert() throws SQLException {
        String mysql = "INSERT INTO addcinema (CinemaName , CinemaAddress , CinemaRate, CinemaImage)" +
                "VALUES(?,?,?,?)";
        String uri = Static.path;
        uri = uri.replace("\\" , "\\\\");
        connect = Database.CODB();
        prepare = connect.prepareStatement(mysql);
        prepare.setString(1, cinema_name.getText());
        prepare.setString(2, cinema_address.getText());
        prepare.setString(3, cinema_rate.getText());
        prepare.setString(4, uri);
        prepare.executeUpdate();

        alert = new Alert();
        alert.information("Successful.");

        clear();
        showAddCinema();
    }
    public void clear(){
        cinema_name.setText("");
        cinema_address.setText("");
        cinema_rate.setText("");
        add_cinema_imageview.setImage(null);
    }
    public void select(){
        CinemaData CD = cinema_tableview.getSelectionModel().getSelectedItem();
        int num = cinema_tableview.getSelectionModel().getSelectedIndex();
        if((num - 1) < -1){
            return;
        }
        cinema_name.setText(CD.getName());
        cinema_address.setText(CD.getAddress());
        cinema_rate.setText(CD.getRate());
        String uri = "file:"+CD.getImage();
        image = new Image(uri,178,175,false,true);
        add_cinema_imageview.setImage(image);
        Static.cinema = CD.getName();
        Static.path = CD.getImage();
    }
    public void addmovie(ActionEvent event) throws IOException {
        Switch s =new Switch();
        s.switchto(event, "Admin2.fxml");
    }
    public void delete(){
        String delet = "DELETE FROM addcinema WHERE CinemaName = '"+ Static.cinema+"'";
        try{
            prepare = connect.prepareStatement(delet);
            prepare.executeUpdate();
            alert = new Alert();
            alert.information("Successfully deleted");

            showAddCinema();
            clear();
        }catch (Exception e){e.printStackTrace();}
    }
    public void update() throws SQLException {
        if(cinema_name.getText().isEmpty()|| cinema_address.getText().isEmpty()||
                cinema_rate.getText().isEmpty()|| add_cinema_imageview.getImage()==null ){
            alert = new Alert();
            alert.Error("Please fill out all field");
        }else {
            String url = Static.path;
            url = url.replace("\\", "\\\\");
            String Update = "UPDATE addcinema SET CinemaName = '"+cinema_name.getText()+"',  CinemaAddress = '"+
                    cinema_address.getText()+"', CinemaRate = '"+cinema_rate.getText()+"', CinemaImage = '"+
                    url+"' WHERE CinemaName = '"+Static.cinema+"'";


            connect = Database.CODB();
            try{

                prepare = connect.prepareStatement(Update);
                prepare.executeUpdate();

                alert = new Alert();
                alert.information("Successfully updated.");

                showAddCinema();
                clear();
            }catch (Exception e){e.printStackTrace();}
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showAddCinema();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
