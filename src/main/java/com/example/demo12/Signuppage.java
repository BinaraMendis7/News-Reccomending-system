package com.example.demo12;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Signuppage  {
    @FXML
    private TextField name;
    @FXML
    private TextField user;
    @FXML
    private PasswordField pass;
    @FXML
    private Button enter;
    @FXML
    private Button rest;
    @FXML
    private Label errormessage;
    HelloController c1 = new HelloController();
    database database=new database();

    public void onrest(MouseEvent mouseEvent) {
        name.clear();
        user.clear();
        pass.clear();
    }


    public void onentering(MouseEvent mouseEvent) {
        String Name = name.getText();
        String User_name = user.getText();
        String Password = pass.getText();


        String query = "INSERT INTO USER (name, username, password) VALUES (?, ?, ?)";
        database.getConnection(query, Name, User_name, Password);


        c1.closeCurrentStage(enter);
    }
}
