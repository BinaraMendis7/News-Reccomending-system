package com.example.demo12;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;

public class SigninPage {
    @FXML
    private Button signin;
    @FXML
    private Button signup;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public void onsignin(MouseEvent mouseEvent) throws IOException {
        String User_name=username.getText();
        String Password=password.getText();
        loadNewsArticle();
        closeCurrentStage(signin);

    }

    public void onsigningup(MouseEvent mouseEvent) throws IOException {
        loadSignuppage();
        closeCurrentStage(signup);
    }

    void closeCurrentStage(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close(); // close the current stage
    }

    private void openNewStage(String fxmlFile, String title, int width, int height) throws IOException { // method to open new stages
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();
    }
    public void loadSignuppage() throws IOException {
        openNewStage("signuppage.fxml", "Signup Page", 500, 500); // fixed typo in FXML filename
    }
    public void loadNewsArticle() throws IOException {
        openNewStage("NewsArticle.fxml","News Article Page",500,500);
    }
}