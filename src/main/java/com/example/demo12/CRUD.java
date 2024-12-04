package com.example.demo12;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;


public class CRUD extends database {


    HashMap<Integer,String> details=new HashMap<>();
    HashMap<String,ArrayList<String>> userDetails=new HashMap<>();
    ArrayList<String> detailsUser;
    HashMap<Integer,String> userHistory=new HashMap<>();
    HashMap<String,ArrayList<Integer>> articlesLikedByUser;
    ArrayList<Integer> listOfArticles;
    HashMap<Integer,String> typesOfArticles=new HashMap<>();
    ArrayList<Integer> listOfTypes;





    public void insert(String name, String username, String password, List<String> preferences) {
        try {

            getConnection();


            ResultSet oldUsernames = statement.executeQuery("SELECT username FROM user");

            boolean userExists = false;
            while (oldUsernames.next()) {
                if (oldUsernames.getString("username").equals(username)) {
                    userExists = true;
                    break;
                }
            }

            if (!userExists) {
                String query = "INSERT INTO USER (name, username, password, preference) VALUES (?, ?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, password);
                preparedStatement.setString(4, String.valueOf(preferences));


                preparedStatement.executeUpdate();
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("The User is added Successfully");

                Optional<ButtonType> result=alert.showAndWait();

                if (result.get()==ButtonType.OK){
                    mainController mainController=new mainController();
                    mainController.loadSigninPage();

                }


                preparedStatement.close();
            }
            else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setContentText("The User Name you Entered is already in the system");
                Optional<ButtonType> result=alert.showAndWait();

                if (result.get()==ButtonType.OK){
                    mainController mainController=new mainController();
                    mainController.loadSignuppage();

                }


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




    public void insertArticle(String Title, String content, int ID) {
        try {


            getConnection();
            String sql = "INSERT INTO news (Article_Name, content, Article_ID) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, Title);
            preparedStatement.setString(2, content);
            preparedStatement.setInt(3, ID);
            preparedStatement.executeUpdate();



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


    public void insertSportsNews(String content, int ID){
        try {
            getConnection();
            String sql = "INSERT INTO sports (content, Artiicle_ID) VALUES (?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);


            pstmt.setString(1, content);
            pstmt.setInt(2,ID);
            pstmt.executeUpdate();



            pstmt.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void insertHealthNews(String content, int ID){
        try {
            getConnection();

            String sql = "INSERT INTO health(content,Artiicle_ID) VALUES (?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);


            pstmt.setString(1, content);
            pstmt.setInt(2,ID);
            pstmt.executeUpdate();



            pstmt.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
    public void insertBussinessNews(String content, int ID){
        try{
            getConnection();


            String sql="INSERT INTO biz_news(content,Artiicle_ID) VALUES(?,?)";
            PreparedStatement pstm= connection.prepareStatement(sql);
            pstm.setString(1,content);
            pstm.setInt(2,ID);
            pstm.executeUpdate();

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


    public void InsertLike(String userName,int Article_ID, String type,String title){
        try{
            getConnection();

            String sql="INSERT INTO user_history(User_Name,Article_ID,type,title) VALUES(?,?,?,?)";

            PreparedStatement preparedStatement=connection.prepareStatement(sql);

            preparedStatement.setString(1,userName);
            preparedStatement.setInt(2,Article_ID);
            preparedStatement.setString(3,type);
            preparedStatement.setString(4,title);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public ObservableList<Article> readSport(){
        ObservableList<Article> categorizeSpotArticle=FXCollections.observableArrayList();
        try{
            getConnection();
            String sql="SELECT content,Artiicle_ID FROM sports";
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                int ID=resultSet.getInt("Artiicle_ID");
                String content=resultSet.getString("content");
                categorizeSpotArticle.add(new Article(content, ID));

            }
        }
        catch (Exception e){
            System.out.println(e);

        }
        return categorizeSpotArticle;
    }
    public ObservableList<Article> readBiz() {
         ObservableList<Article> categorizeBizArticle= FXCollections.observableArrayList();
        try {
            getConnection();
            String sql = "SELECT content, Artiicle_ID FROM biz_news";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String content = resultSet.getString("content");
                int ID = resultSet.getInt("Artiicle_ID");

                categorizeBizArticle.add(new Article(content, ID));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return categorizeBizArticle;
    }
    public ObservableList<Article> readHealth(){
        ObservableList<Article> categorizeHealthArticle= FXCollections.observableArrayList();
        try{
            getConnection();
            String sql="SELECT content,Artiicle_ID FROM health";
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                String content=resultSet.getString("content");
                int ID=resultSet.getInt("Artiicle_ID");
                categorizeHealthArticle.add(new Article(content,ID));
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return categorizeHealthArticle;
    }



    public void readUserHistory(){

        try{
            articlesLikedByUser = new HashMap<>();
            userHistory = new HashMap<>();
            getConnection();

            String sql="SELECT User_Name,Article_ID,type FROM user_history";
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                String userName=resultSet.getString("User_Name");
                int ID=resultSet.getInt("Article_ID");
                String Type=resultSet.getString("type");
                userHistory.put(ID,userName);
                typesOfArticles.put(ID,Type);
            }

            Collection<String> usernames=userHistory.values();
            for (String Username: usernames){
                listOfArticles = new ArrayList<>();
                for (int ID: userHistory.keySet()){
                    if (userHistory.get(ID).equals(Username)){
                        listOfArticles.add(ID);
                    }
                }
                articlesLikedByUser.put(Username,listOfArticles);

            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public ObservableList<Article> readUserHistory(User user){
        ObservableList<Article> userRead=FXCollections.observableArrayList();
        try{

            getConnection();
            String query = "SELECT type,title FROM user_history WHERE User_Name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String type=resultSet.getString("type");
                String title=resultSet.getString("title");
                userRead.add(new Article(title,type));
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return userRead;
    }
    public void readUser(){

        try{
            getConnection();
            String sql="SELECT * FROM user";
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                String username=resultSet.getString("username");
                String preference=resultSet.getString("preference");
                detailsUser=new ArrayList<>();
                detailsUser.add(preference);
                userDetails.put(username,detailsUser);
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }
    final List<String> list=Arrays.asList("news","sports","health","biz_news");
    public void deleteArticle(){
        try{
            getConnection();

            for (String table: list){
                String sql1 = "TRUNCATE TABLE " + table;
                statement.executeUpdate(sql1);

            }
            connection.close();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Input");
            alert.setContentText("All articles were Deleted");
            alert.showAndWait();

        }catch (Exception e){
            System.out.println(e);
        }
    }
}