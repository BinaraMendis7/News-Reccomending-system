package com.example.demo12;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Signuppage extends SigninPage  {
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


    public void onrest(MouseEvent mouseEvent) {
        name.clear();
        user.clear();
        pass.clear();
    }


    public void onentering(MouseEvent mouseEvent) {
        String Name = name.getText();
        String User_name = user.getText();
        String Password = pass.getText();
        User user1=new User(User_name,Name,Password);
        user1.setName(Name);
        user1.setUsername(User_name);
        user1.setPassword(Password);

        CRUD update=new CRUD();

        String query = "INSERT INTO USER (name, username, password) VALUES (?, ?, ?)";
        update.insert(query, user1.getName(), user1.getUsername(), user1.getPassword());


        closeCurrentStage(enter);
    }
}