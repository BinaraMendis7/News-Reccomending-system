package com.example.demo12;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.scene.input.MouseEvent;

public class ViewHistory extends SigninPage implements Initializable {

    @FXML
    private TableView<Article> table;

    @FXML
    private TableColumn<Article, Integer> title;

    @FXML
    private TableColumn<Article, String> type;
    @FXML
    private Button back;
    ObservableList<Article> articles= FXCollections.observableArrayList();
    CRUD crud=new CRUD();
    User user;
    public void setUser(User user) throws IOException {
        this.user = user;
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up the table columns
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        type.setCellValueFactory(new PropertyValueFactory<>("category"));

        // Run the data loading part on the JavaFX Application Thread
        Platform.runLater(() -> {
            if (user != null) {
                articles = crud.readUserHistory(user);  // Fetch user history
                table.setItems(articles);  // Set the articles in the TableView
            } else {
                System.out.println("User is null. Cannot load user history.");
            }
        });
    }


    public void onbacking(MouseEvent mouseEvent) throws IOException {
        loadRecomending();
        mainController main=new mainController();
        main.closeCurrentStage(back);

    }
}

