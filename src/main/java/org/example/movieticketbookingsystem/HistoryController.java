package org.example.movieticketbookingsystem;

import javafx.event.ActionEvent;

import java.io.IOException;

public class HistoryController {
    public void cancel(){

    }
    public void back(ActionEvent event) throws IOException {
        ProfileController pc = new ProfileController();
        pc.back(event);
    }
}
