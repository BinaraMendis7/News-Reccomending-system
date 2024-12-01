package com.example.demo12;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Optional;

public class AdminController extends mainController{
    @FXML
    private Button back;
    @FXML
    private Button manageNews;

    public void Onbacking(MouseEvent mouseEvent) throws IOException {
        loadSigninPage();
        closeCurrentStage(back);
    }

    public void OnManaging(MouseEvent mouseEvent) throws IOException {
        manageNews.setText("PROCESS ON GOING");
        Article article=new Article();
        article.insertArticle();
        article.insertSports();
        article.insertBusiness();
        article.insertHealth();
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");


        Optional<ButtonType> result=alert.showAndWait();

        if (result.get()==ButtonType.OK) {
            manageNews.setText("Done");

        }
    }
}

