package com.example.demo12;

import javafx.application.Platform;
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

        new Thread(() -> {
            try {
                Article article = new Article();
                article.insertArticle();
                article.insertSports();
                article.insertBusiness();
                article.insertHealth();


                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("All articles added successfully!");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        manageNews.setText("Done");
                    }
                });
            } catch (Exception e) {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("An error occurred while adding articles.");
                    alert.showAndWait();
                    manageNews.setText("Failed");
                });
            }
        }).start();
    }

    public void ondeleting(MouseEvent mouseEvent) {
        Admin admin=new Admin();
        admin.deleteArticle();
    }
}

