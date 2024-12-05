package com.example.demo12;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Platform;
import javafx.stage.Stage;

public class RecomendingController{
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
    @FXML
    private Button next;
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
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
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
            user.addLikedArticle(article);
            user.like(article);
            Label likeLabel = new Label("You liked this article!");
            anchorpane.getChildren().add(likeLabel);
        }
    }
    private void displayArticle(Article RandomArticle) {
        anchorpane.getChildren().clear();
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
        recomending.setText("Recommend");
        next.setVisible(true);
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
        alert.setContentText("Do you want to log out from your account");
        Optional<ButtonType> result=alert.showAndWait();
        if (result.get()==ButtonType.OK){
            mainController mainController=new mainController();
            mainController.loadSigninPage();
            mainController.closeCurrentStage(logout);
        }

    }


    public void Recommending(MouseEvent mouseEvent) {
        anchorpane.getChildren().clear();
        recomending.setText("Next");
        next.setVisible(false);

        executor.submit(() -> {
            recommend.Recommend(user);
            articles = recommend.recommendedArticles;
            Platform.runLater(this::displayNextRecommendedArticle);
        });
    }

    private void displayNextRecommendedArticle() {
        if (articles.isEmpty()) {
            Label finishedLabel = new Label("No more articles to display.");
            anchorpane.getChildren().add(finishedLabel);
            return;
        }

        if (currentArticleIndex < articles.size()) {
            Article recommendedArticle = articles.get(currentArticleIndex);
            currentArticleIndex++;
            displayArticle(recommendedArticle);
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