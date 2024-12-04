package com.example.demo12;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ViewHistory implements Initializable {

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
    public void setUser(User user) {
        this.user = user;
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        type.setCellValueFactory(new PropertyValueFactory<>("category"));

        Platform.runLater(() -> {
            if (user != null) {
                user.viewHistory(user);
                articles = user.getUserHistory();
                table.setItems(articles);
            } else {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("User Not found Error");
                alert.setContentText("Please Login Again");
            }
        });
    }


    public void onbacking(MouseEvent mouseEvent) throws IOException {
        loadRecomending();
        mainController main=new mainController();
        main.closeCurrentStage(back);

    }

    public void loadRecomending() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Recomending.fxml"));
        Parent root = loader.load();

        RecomendingController recomendingConroller = loader.getController();
        recomendingConroller.setUser(user);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

