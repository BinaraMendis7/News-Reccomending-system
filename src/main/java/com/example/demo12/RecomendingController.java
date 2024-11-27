package com.example.demo12;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class RecomendingController {
    @FXML
    private AnchorPane anchorpane;
    CRUD crud=new CRUD();
    Recommend recommend=new Recommend();

    Article article;
    User user;
    private List<Article> articles;
    public void setUser(User user) {
        this.user = user;

    }
    @FXML
    public void initialize() {
        articles = recommend.recommendedArticles;
    }

    public void onliking(MouseEvent mouseEvent) {
        if (user != null && article != null) {
            int ID = article.getArticle_ID();
            String type = crud.searchArticle(ID); // Get the article type
            crud.InsertLike(user.getUsername(), ID, type); // Insert the like into the database
            Label likeLabel = new Label("You liked this article!");
            anchorpane.getChildren().add(likeLabel);
        }
    }

    // Add a member variable to track the current article index
    private int currentArticleIndex = 0;

    public void nexting(MouseEvent mouseEvent) {
        // Clear the anchorpane only once before adding the articles
        anchorpane.getChildren().clear();

        // Check if there are articles to display
        if (articles.isEmpty()) {
            Label finishedLabel = new Label("No more articles to display.");
            anchorpane.getChildren().add(finishedLabel);
            return;
        }

        // Display the next article in the list based on the current index
        if (currentArticleIndex < articles.size()) {
            Article RecomendedArticle = articles.get(currentArticleIndex);
            currentArticleIndex++; // Move to the next article

            // Check if the article has content
            if (RecomendedArticle.getContent() != null && !RecomendedArticle.getContent().isEmpty()) {
                System.out.println(RecomendedArticle.getTitle());
                TextArea articleTextArea = new TextArea();
                articleTextArea.setText("Title: " + RecomendedArticle.getTitle() + "\n\n" + "Content:\n" + RecomendedArticle.getContent());
                articleTextArea.setWrapText(true);
                articleTextArea.setEditable(false);

                articleTextArea.setPrefWidth(600);
                articleTextArea.setPrefHeight(600);

                article = RecomendedArticle;
                anchorpane.getChildren().add(articleTextArea);
            } else {
                Label noContentLabel = new Label("This article has no content available.");
                anchorpane.getChildren().add(noContentLabel);
            }
        } else {
            // If no more articles, show the finished label
            Label finishedLabel = new Label("No more articles to display.");
            anchorpane.getChildren().add(finishedLabel);
        }
    }



}
