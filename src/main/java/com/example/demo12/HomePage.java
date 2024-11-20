package com.example.demo12;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class HomePage extends mainController{
    @FXML
    private Button sport;
    @FXML
    private Button international;

    public void onclickingsports(MouseEvent mouseEvent) throws IOException {
        closeCurrentStage(sport);
        loadSport();

    }

    public void onclickinginternationalnews(MouseEvent mouseEvent) {
        closeCurrentStage(international);

    }

    public void onclickingbuissniess(MouseEvent mouseEvent) {
    }
}
