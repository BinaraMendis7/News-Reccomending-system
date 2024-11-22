package com.example.demo12;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.collections.ObservableList;

import java.util.Random;


public class HomePage {
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
    User user; // User object to track the logged-in user

    public void setUser(User user) {
        this.user = user; // Set the user object
    }

    @FXML
    public void nexting(MouseEvent mouseEvent) {
        ObservableList<Article> articles = crud.getArticlesFromDatabase();

        Random random = new Random();
        int randomIndex = random.nextInt(articles.size());
        Article randomArticle = articles.get(randomIndex);

        if (randomArticle.getContent() != null && !randomArticle.getContent().isEmpty()) {
            TextArea articleTextArea = new TextArea();
            articleTextArea.setText("Title: " + randomArticle.getTitle() + "\n\n" + "Content:\n" + randomArticle.getContent());
            articleTextArea.setWrapText(true);
            articleTextArea.setEditable(false);

            articleTextArea.setPrefWidth(600);
            articleTextArea.setPrefHeight(600);
            article = new Article(randomArticle.getArticle_ID());
            article.setArticle_ID(randomArticle.getArticle_ID());
            newsbody.getChildren().add(articleTextArea);
        } else {
            Label noContentLabel = new Label("This article has no content available.");
            newsbody.getChildren().add(noContentLabel);
        }
    }

    public void onliking(MouseEvent mouseEvent) {
        if (user != null && article != null) {
            crud.InsertLike(user.getUsername(), article.getArticle_ID());
        } else {
            System.out.println("User or article is null!");
        }
    }
}

