package org.example.movieticketbookingsystem;

public class Alert {
    private javafx.scene.control.Alert alert;
    public void Error(String mg){
        alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);

        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mg);
        alert.showAndWait();
    }
    public void  information(String mg){
        alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Information Massage");
        alert.setHeaderText(null);
        alert.setContentText(mg);
        alert.showAndWait();
    }
}
