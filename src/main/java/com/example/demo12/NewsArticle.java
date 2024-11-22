package com.example.demo12;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class NewsArticle implements Initializable {


    @FXML
    private TableColumn<Article, String> articleName;

    @FXML
    public TableView<Article> table;

    @FXML
    private TableColumn<Article, String> title;
    CRUD crud=new CRUD();
    ObservableList<Article> tableui=crud.getArticlesFromDatabase();




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        articleName.setCellValueFactory(new PropertyValueFactory<Article,String>("title"));
        title.setCellValueFactory(new PropertyValueFactory<Article,String>("content"));

        table.setItems(tableui);

    }


}

