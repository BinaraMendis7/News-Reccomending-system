package com.example.demo12;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CRUD extends database {
    Scraping scraping=new Scraping();


    HashMap<Integer,String> details=new HashMap<>();
    HashMap<String,ArrayList<String>> userDetails=new HashMap<>();
    ArrayList<String> detailsUser=new ArrayList<>();
    ArrayList<ArrayList<Object>> userhistory=new ArrayList<>();



    public void insert(String query, String name, String username, String password, List<String> preferences) {
        try {

            getConnection();


            ResultSet oldUsernames = statement.executeQuery("SELECT username FROM user");

            boolean userExists = false;
            while (oldUsernames.next()) {
                if (oldUsernames.getString("username").equals(username)) {
                    System.out.println("Username already exists.");
                    userExists = true;
                    break;
                }
            }

            if (!userExists) {

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, password);
                preparedStatement.setString(4, String.valueOf(preferences));


                preparedStatement.executeUpdate();
                System.out.println("User inserted successfully.");


                preparedStatement.close();
            }

            
            oldUsernames.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public boolean read(String username, String Password){
        boolean userExists=false;
        try {
            getConnection();
            String query="SELECT * FROM user WHERE username= ? AND password= ?";
            PreparedStatement statement1=connection.prepareStatement(query);
            statement1.setString(1,username);
            statement1.setString(2,Password);

            ResultSet resultSet=statement1.executeQuery();

            if (resultSet.next()){
                userExists=true;
            }


        }
        catch (Exception e){
            System.out.println(e);

        }
        return userExists;

    }
    public void read() {
        try {
            getConnection();

            String selectQuery = "SELECT content, Article_ID FROM  news";
            ResultSet rs = statement.executeQuery(selectQuery);
                while (rs.next()) {
                    String content = rs.getString("content");
                    int ID=rs.getInt("Article_ID");
                    details.put(ID,content);

                }

                rs.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (Exception e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }




    public void insertArticle() {
        try {

            getConnection();


            scraping.news("https://www.newswire.lk/category/business/");
            scraping.news("https://www.newswire.lk/category/international-news/");
            scraping.news("https://www.newswire.lk/category/sports/");

            String sql = "INSERT INTO news (Article_Name, content, Article_ID) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int Article_ID = 1;
            for (String Article_Name : scraping.body_list.keySet()) {
                String content = scraping.body_list.get(Article_Name);
                preparedStatement.setString(1, Article_Name);
                preparedStatement.setString(2, content);
                preparedStatement.setInt(3, Article_ID);


                preparedStatement.executeUpdate();

                Article_ID++;
            }


            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public String searchArticle(int ID) {
        String type = "unknown";
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            getConnection();
            statement = connection.createStatement();


            String sql = "SELECT Artiicle_ID FROM biz_news";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int articleId = resultSet.getInt("Artiicle_ID");
                if (articleId == ID) {
                    type = "Business";
                    return type;
                }
            }
            resultSet.close();

            String sql1 = "SELECT Artiicle_ID FROM health";
            resultSet = statement.executeQuery(sql1);
            while (resultSet.next()) {
                int articleId = resultSet.getInt("Artiicle_ID");
                if (articleId == ID) {
                    type = "Health";
                    return type;
                }
            }
            resultSet.close();

            String sql2 = "SELECT Artiicle_ID FROM sports";
            resultSet = statement.executeQuery(sql2);
            while (resultSet.next()) {
                int articleId = resultSet.getInt("Artiicle_ID");
                if (articleId == ID) {
                    type = "Sport";
                    return type;
                }
            }

        } catch (Exception e) {
            System.out.println("Error in searchArticle: " + e.getMessage());
        } finally {

            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (Exception e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }

        return type;
    }


    public void insertSportsNews(){
        try {
            getConnection();

            KeyWordExtraction k2=new KeyWordExtraction();
            k2.catergorize();
            String sql = "INSERT INTO sports (content, Artiicle_ID) VALUES (?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            for (int ID : k2.sports.keySet()) {
                pstmt.setString(1, k2.sports.get(ID));
                pstmt.setInt(2,ID);
                pstmt.executeUpdate();
            }


            pstmt.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void insertHealthNews(){
        try {
            getConnection();

            KeyWordExtraction k3=new KeyWordExtraction();
            k3.catergorize();
            String sql = "INSERT INTO health(content,Artiicle_ID) VALUES (?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            HashMap<Integer,String> health=new HashMap<>(k3.health);

            for (int ID:health.keySet()) {
                pstmt.setString(1, health.get(ID));
                pstmt.setInt(2,ID);
                pstmt.executeUpdate();
            }


            pstmt.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
    public void insertBussinessNews(){
        try{
            getConnection();

            KeyWordExtraction k4=new KeyWordExtraction();
            k4.catergorize();
            String sql="INSERT INTO biz_news(content,Artiicle_ID) VALUES(?,?)";
            PreparedStatement pstm= connection.prepareStatement(sql);
            HashMap<Integer,String> business=new HashMap<>(k4.business);

            for (int ID: business.keySet()){
                pstm.setString(1,business.get(ID));
                pstm.setInt(2,ID);
                pstm.executeUpdate();
            }
            pstm.close();
            connection.close();

        }catch (Exception e){
            System.out.println(e);
        }
    }
    public ObservableList<Article> getArticlesFromDatabase() {
        ObservableList<Article> articles = FXCollections.observableArrayList();
        try {
            getConnection();
            String query = "SELECT Article_Name,content,Article_ID FROM news";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String title = resultSet.getString("Article_Name");
                String content = resultSet.getString("content");
                int ID=resultSet.getInt(3);
                articles.add(new Article(title,content,ID));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articles;
    }


    public void InsertLike(String userName,int Article_ID, String type){
        try{
            getConnection();

            String sql="INSERT INTO user_history(User_Name,Article_ID,type) VALUES(?,?,?)";

            PreparedStatement preparedStatement=connection.prepareStatement(sql);

            preparedStatement.setString(1,userName);
            preparedStatement.setInt(2,Article_ID);
            preparedStatement.setString(3,type);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void readUserHistory(){

        try{
            getConnection();

            String sql="SELECT * FROM user_history";
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                String userName=resultSet.getString("User_Name");
                int ID=resultSet.getInt("Article_ID");
                String Type=resultSet.getString("type");
                ArrayList<Object> subhistoy=new ArrayList<>();
                subhistoy.add(userName);
                subhistoy.add(ID);
                userhistory.add(subhistoy);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void readUser(){

        try{
            getConnection();
            String sql="SELECT * FROM user";
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                String username=resultSet.getString("username");
                String preference=resultSet.getString("preference");
                detailsUser.add(preference);
                userDetails.put(username,detailsUser);
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        CRUD c=new CRUD();
        c.insertArticle();
        c.insertSportsNews();
        c.insertHealthNews();
        c.insertBussinessNews();
    }
}