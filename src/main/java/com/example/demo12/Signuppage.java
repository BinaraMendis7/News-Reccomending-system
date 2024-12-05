package com.example.demo12;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private RadioButton sports;
    @FXML
    private RadioButton health;
    @FXML
    private RadioButton business;


    public void onrest(MouseEvent mouseEvent) {
        name.clear();
        user.clear();
        pass.clear();
    }


    public void onentering(MouseEvent mouseEvent) {
        String Name = name.getText();
        String User_name = user.getText();
        String Password = pass.getText();
        List<String> preferenceList = new ArrayList<>();
        if (sports.isSelected()) {
            preferenceList.add(sports.getText());
        }
        if (health.isSelected()) {
            preferenceList.add(health.getText());
        }
        if (business.isSelected()) {
            preferenceList.add(business.getText());
        }
        if (User_name.equals("Admin1")){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid input");
            alert.setContentText("You Cant use Admin1 as your username");
            Optional<ButtonType> result=alert.showAndWait();

            if (result.get()==ButtonType.OK){
                user.clear();

            }
        }
        else{
            User user1 = new User(User_name, Name, Password, preferenceList);
            user1.setName(Name);
            user1.setUsername(User_name);
            user1.setPassword(Password);
            user1.setPreferredCategories(preferenceList);
            user1.siginUp();
            closeCurrentStage(enter);
        }

    }

    public void onbacking(MouseEvent mouseEvent) throws IOException {
        loadSigninPage();
        closeCurrentStage(enter);
    }
}