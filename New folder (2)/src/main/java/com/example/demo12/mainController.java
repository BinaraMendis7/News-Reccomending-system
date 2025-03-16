package com.example.demo12;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class mainController {
    void closeCurrentStage(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close(); // close the current stage
    }

    private void openNewStage(String fxmlFile, String title, int width, int height) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo12/" + fxmlFile));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();
    }

    public void loadSignuppage() throws IOException {
        openNewStage("signuppage.fxml", "Signup Page", 500, 500); // fixed typo in FXML filename
    }
    public void loadHome() throws IOException {
        openNewStage("HOME.fxml","News Page",700,700);
    }
    public void loadAdmin() throws IOException {
        openNewStage("admin.fxml","Admin News",500,500);
    }
    public void loadSigninPage() throws IOException {
        openNewStage("hello-view.fxml","SignInpage",600,400);
    }
}
