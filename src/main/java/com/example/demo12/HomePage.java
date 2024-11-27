package com.example.demo12;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.Random;

public class HomePage{

    @FXML
    private Button previous;
    @FXML
    private Button next;
    @FXML
    private AnchorPane newsbody;
    @FXML
    private Label title;
    @FXML
    private Label Welcome;

    CRUD crud = new CRUD();
    Article article;
    User user;
    private ObservableList<Article> articles;
    private ObservableList<Article> remainingArticles;
    private Random random = new Random();

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    public void initialize() {
        articles = crud.getArticlesFromDatabase();
        remainingArticles = FXCollections.observableArrayList(articles);
    }

    @FXML
    public void nexting(MouseEvent mouseEvent) {
        if (remainingArticles.isEmpty()) {
            Label finishedLabel = new Label("No more articles to display.");
            newsbody.getChildren().clear();
            newsbody.getChildren().add(finishedLabel);
            return;
        }
        int randomIndex = random.nextInt(remainingArticles.size());
        Article randomArticle = remainingArticles.remove(randomIndex);
        newsbody.getChildren().clear();

        if (randomArticle.getContent() != null && !randomArticle.getContent().isEmpty()) {
            TextArea articleTextArea = new TextArea();
            articleTextArea.setText("Title: " + randomArticle.getTitle() + "\n\n" + "Content:\n" + randomArticle.getContent());
            articleTextArea.setWrapText(true);
            articleTextArea.setEditable(false);

            articleTextArea.setPrefWidth(600);
            articleTextArea.setPrefHeight(600);

            article = randomArticle;
            newsbody.getChildren().add(articleTextArea);
        } else {
            Label noContentLabel = new Label("This article has no content available.");
            newsbody.getChildren().add(noContentLabel);
        }

    }

    @FXML
    public void onliking(MouseEvent mouseEvent) {
        // Check if a user and an article are set before liking
        if (user != null && article != null) {
            int ID = article.getArticle_ID();
            String type = crud.searchArticle(ID); // Get the article type
            crud.InsertLike(user.getUsername(), ID, type); // Insert the like into the database
            Label likeLabel = new Label("You liked this article!");
            newsbody.getChildren().add(likeLabel);
        }

    }


    public void letsGo(MouseEvent mouseEvent) {
    }
}
