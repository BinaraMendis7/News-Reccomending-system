package com.example.demo12;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

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
    @FXML
    private RadioButton sports;
    @FXML
    private RadioButton health;
    @FXML
    private RadioButton business;
    @FXML
    private ToggleGroup myToggleGroup;


    public void onrest(MouseEvent mouseEvent) {
        name.clear();
        user.clear();
        pass.clear();
    }


    public void onentering(MouseEvent mouseEvent) {
        String Name = name.getText();
        String User_name = user.getText();
        String Password = pass.getText();

        // Create a list for preferences
        List<String> preferenceList = new ArrayList<>();

        // Check if each radio button is selected before adding its text
        if (sports.isSelected()) {
            preferenceList.add(sports.getText());
        }
        if (health.isSelected()) {
            preferenceList.add(health.getText());
        }
        if (business.isSelected()) {
            preferenceList.add(business.getText());
        }

        // Create a new User object with the gathered preferences
        User user1 = new User(User_name, Name, Password, preferenceList);
        user1.setName(Name);
        user1.setUsername(User_name);
        user1.setPassword(Password);
        user1.setPreferredCategories(preferenceList);

        // Use CRUD to insert the user into the database
        CRUD update = new CRUD();
        String query = "INSERT INTO USER (name, username, password, preference) VALUES (?, ?, ?, ?)";


        update.insert(query, user1.getName(), user1.getUsername(), user1.getPassword(), preferenceList);

        closeCurrentStage(enter);
    }

}