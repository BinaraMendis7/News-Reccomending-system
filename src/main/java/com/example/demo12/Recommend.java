package com.example.demo12;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Recommend {
    CRUD crud=new CRUD();
    public void Recommend(){
        ObservableList<Article> articles= crud.getArticlesFromDatabase();
        crud.readUser();
        crud.readUserHistory();
        for (String username: crud.userDetails.keySet()){
            User user=new User(username,crud.userDetails.get(username),)

        }


    }
}
