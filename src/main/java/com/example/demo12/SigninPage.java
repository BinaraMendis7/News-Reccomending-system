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
import java.util.HashMap;
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
    @FXML
    private Button admin;

    User user;

    /*static HashMap<Integer,String> sports=new HashMap<>();
    static HashMap<Integer,String> health=new HashMap<>();



    static HashMap<Integer,String> bussiness=new HashMap<>();

    public static HashMap<Integer, String> getSports() {
        return sports;
    }

    public static HashMap<Integer, String> getHealth() {
        return health;
    }

    public static HashMap<Integer, String> getBussiness() {
        return bussiness;
    }*/

    public void onsignin(MouseEvent mouseEvent) throws IOException {
        String User_name=username.getText();
        String Password=password.getText();
        user=new User(User_name);
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



    public void loadHome() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HOME.fxml"));
        Parent root = loader.load();

        HomePage homePageController = loader.getController();
        homePageController.setUser(user);
        /*homePageController.setSports(SigninPage.getSports());
        homePageController.setBussiness(SigninPage.getBussiness());
        homePageController.setHealth(SigninPage.getHealth());*/

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void onClicking(MouseEvent mouseEvent) throws IOException {
        loadAdmin();
        closeCurrentStage(admin);

    }
}