package com.example.demo12;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.collections.ObservableList;

import java.util.Random;

public class HomePage extends mainController {
    @FXML
    private Button previous;
    @FXML
    private Button next;
    @FXML
    private AnchorPane newsbody;
    @FXML
    private Label title;
    CRUD crud=new CRUD();

    // Method to handle the "Next" button click
    @FXML
    public void nexting(MouseEvent mouseEvent) {
        ObservableList<Article> articles = crud.getArticlesFromDatabase();

        if (!articles.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(articles.size());
            Article randomArticle = articles.get(randomIndex);
            newsbody.getChildren().clear();

            Label titleLabel = new Label(randomArticle.getTitle());
            titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

            title.setText(randomArticle.getTitle());
            AnchorPane.setTopAnchor(titleLabel, 10.0);
            AnchorPane.setLeftAnchor(titleLabel, 10.0);
            newsbody.getChildren().addAll(titleLabel);


        } else {
            newsbody.getChildren().clear();
            Label noArticlesLabel = new Label("No articles available.");
            AnchorPane.setTopAnchor(noArticlesLabel, 10.0);
            AnchorPane.setLeftAnchor(noArticlesLabel, 10.0);
            newsbody.getChildren().add(noArticlesLabel);
        }
    }
    public void onliking(MouseEvent mouseEvent) {
    }
}
