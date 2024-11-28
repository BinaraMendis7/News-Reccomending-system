package com.example.demo12;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javafx.application.Platform;
import javafx.stage.Stage;

public class RecomendingController extends SigninPage{
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Button sports;
    @FXML
    private Button viewHistory;
    @FXML
    private Button recomending;
    @FXML
    private Button logout;
    CRUD crud=new CRUD();
    Recommend recommend=new Recommend();

    Article article;
    User user;
    private List<Article> articles;
    ObservableList<Article> categorizeBArticle;
    ObservableList<Article> categorizeSpotArticleContent;
    ObservableList<Article> categorizeHealthArticle;
    private String currentCategory = "";
    private int currentIndex = 0;
    private int currentArticleIndex = 0;
    public void setUser(User user) {
        this.user = user;

    }
    public void initialize() {
        Platform.runLater(() -> {
            articles = recommend.recommendedArticles;
            categorizeBArticle = crud.readBiz();
            categorizeSpotArticleContent= crud.readSport();
            categorizeHealthArticle= crud.readHealth();

        });
    }


    public void onliking(MouseEvent mouseEvent) {
        if (user != null && article != null) {
            int ID = article.getArticle_ID();
            String title=article.getTitle();
            String type = crud.searchArticle(ID); // Get the article type
            crud.InsertLike(user.getUsername(), ID, type,title); // Insert the like into the database
            Label likeLabel = new Label("You liked this article!");
            anchorpane.getChildren().add(likeLabel);
        }
    }
    private void displayArticle(Article RandomArticle) {
        anchorpane.getChildren().clear();
        recomending.setText("Recomend");
        if (RandomArticle != null && RandomArticle.getContent() != null && !RandomArticle.getContent().isEmpty()) {
            TextArea articleTextArea = new TextArea("Title: " + RandomArticle.getTitle() + "\n\n" + "Content:\n" + RandomArticle.getContent());
            articleTextArea.setWrapText(true);
            articleTextArea.setEditable(false);
            articleTextArea.setPrefWidth(665);
            articleTextArea.setPrefHeight(433);
            anchorpane.getChildren().add(articleTextArea);
            article=RandomArticle;

        } else {
            Label noContentLabel = new Label("This article has no content available.");
            anchorpane.getChildren().add(noContentLabel);
        }
    }
    private void loadNextArticle(ObservableList<Article> articleList) {
        if (currentIndex < articleList.size()) {
            displayArticle(articleList.get(currentIndex));
            currentIndex++;
        } else {
            anchorpane.getChildren().clear();
            Label finishedLabel = new Label("No more articles to display.");
            anchorpane.getChildren().add(finishedLabel);
        }
    }
    public void sport(MouseEvent mouseEvent) {
        currentCategory = "sport";
        currentIndex = 0;
        loadNextArticle(categorizeSpotArticleContent);
    }

    public void health(MouseEvent mouseEvent) {
        currentCategory = "health";
        currentIndex = 0;
        loadNextArticle(categorizeHealthArticle);
    }

    public void bussiness(MouseEvent mouseEvent) {
        currentCategory = "business";
        currentIndex = 0;
        loadNextArticle(categorizeBArticle);
    }




    public void onloggingout(MouseEvent mouseEvent) throws IOException {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Login out");
        Optional<ButtonType> result=alert.showAndWait();
        if (result.get()==ButtonType.OK){
            mainController mainController=new mainController();
            mainController.loadSigninPage();
            mainController.closeCurrentStage(logout);
        }

    }


    public void Recommending(MouseEvent mouseEvent) {
        // Clear the anchorpane only once before adding the articles
        anchorpane.getChildren().clear();
        recomending.setText("Next");

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

                articleTextArea.setPrefWidth(665);
                articleTextArea.setPrefHeight(433);

                article = RecomendedArticle;
                anchorpane.getChildren().add(articleTextArea);
            } else {
                Label noContentLabel = new Label("This article has no content available.");
                anchorpane.getChildren().add(noContentLabel);
            }
        } else {
            Label finishedLabel = new Label("No more articles to display.");
            anchorpane.getChildren().add(finishedLabel);
        }
    }
    public void skip(MouseEvent mouseEvent) {
        switch (currentCategory) {
            case "sport":
                loadNextArticle(categorizeSpotArticleContent);
                break;
            case "health":
                loadNextArticle(categorizeHealthArticle);
                break;
            case "business":
                loadNextArticle(categorizeBArticle);
                break;
            default:
                Label selectCategoryLabel = new Label("Please select a category first.");
                anchorpane.getChildren().clear();
                anchorpane.getChildren().add(selectCategoryLabel);
                break;
        }
    }

    public void viewHistory(MouseEvent mouseEvent) throws IOException {
        mainController mainController=new mainController();
        loadviewHistory();
        mainController.closeCurrentStage(viewHistory);
    }
    public void loadviewHistory() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewHistory.fxml"));
        Parent root = loader.load();

        ViewHistory Controller = loader.getController();
        Controller.setUser(user);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}