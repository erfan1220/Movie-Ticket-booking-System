module org.example.movieticketbookingsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.movieticketbookingsystem to javafx.fxml;
    exports org.example.movieticketbookingsystem;
}