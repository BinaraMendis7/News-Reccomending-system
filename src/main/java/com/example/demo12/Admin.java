package com.example.demo12;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Optional;

public class Admin extends mainController{
    @FXML
    private Button back;

    public void OnManaging(MouseEvent mouseEvent) throws IOException {



    }

    public void Onbacking(MouseEvent mouseEvent) throws IOException {
        loadSigninPage();
        closeCurrentStage(back);
    }
}
