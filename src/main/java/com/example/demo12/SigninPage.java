package com.example.demo12;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Optional;

public class SigninPage extends mainController {
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
        CRUD read=new CRUD();

        if (read.read(User_name,Password)==true){
            loadHome();
            closeCurrentStage(signin);


        }else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");


            Optional<ButtonType> result=alert.showAndWait();

            if (result.get()==ButtonType.OK){
                reset();

            }
        }


    }
    public void reset(){
        username.clear();
        password.clear();
    }

    public void onsigningup(MouseEvent mouseEvent) throws IOException {
        loadSignuppage();
        closeCurrentStage(signup);
    }


}