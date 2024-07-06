module org.example.movieticketbookingsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;


    opens org.example.movieticketbookingsystem to javafx.fxml;
    exports org.example.movieticketbookingsystem;
}