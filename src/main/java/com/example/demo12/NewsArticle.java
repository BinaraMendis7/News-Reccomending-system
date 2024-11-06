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
    private TableView<Table> table;
    @FXML
    private TableColumn<Table, Integer> articleID;

    @FXML
    private TableColumn<Table, String> articleName;

    @FXML
    private TableColumn<Table, String> type;

    ObservableList<Table> tableui= FXCollections.observableArrayList(
            new Table(001,"Sports","Rising Star: Kavindu’s Journey to the National Cricket Team"),
            new Table(002,"Entertainment","The Rise of Amara Jayasuriya: Sri Lanka’s Newest Music Sensation")

    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        articleID.setCellValueFactory(new PropertyValueFactory<Table,Integer>("Article_ID"));
        type.setCellValueFactory(new PropertyValueFactory<Table,String>("Type"));
        articleName.setCellValueFactory(new PropertyValueFactory<Table,String>("Article_Name"));

        table.setItems(tableui);
    }
}
