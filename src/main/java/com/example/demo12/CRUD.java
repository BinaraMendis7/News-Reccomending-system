package com.example.demo12;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CRUD extends database {
    Scraping scraping=new Scraping();


    ArrayList<String> Content=new ArrayList<>();


    public void insert(String query, String name, String username, String password) {
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

            String selectQuery = "SELECT content FROM  news";
            ResultSet rs = statement.executeQuery(selectQuery);
                while (rs.next()) {
                    String content = rs.getString("content");
                    Content.add(content);

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

            Scraping s = new Scraping();
            s.news("https://www.newswire.lk/category/business/");
            s.news("https://www.newswire.lk/category/international-news/");
            s.news("https://www.newswire.lk/category/sports/");

            String sql = "INSERT INTO news (Article_Name, content) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for (String title : s.body_list.keySet()) {
                String title1 = title;
                String content = s.body_list.get(title);


                preparedStatement.setString(1, title1);
                preparedStatement.setString(2, content);

                preparedStatement.executeUpdate();
            }



            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void inertPoliticalNews(){
        try {
            getConnection();
            KeyWordExtraction k1=new KeyWordExtraction();
            k1.catergorize();

            String sql = "INSERT INTO politics(content) VALUES (?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            for (String content : k1.politics) {
                pstmt.setString(1, content);
                pstmt.executeUpdate();
            }



            pstmt.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }


    }
    public void insertSportsNews(){
        try {
            getConnection();

            KeyWordExtraction k2=new KeyWordExtraction();
            k2.catergorize();
            String sql = "INSERT INTO sports (content) VALUES (?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            for (String content : k2.sport) {
                pstmt.setString(1, content);
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
            String sql = "INSERT INTO health(content) VALUES (?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            for (String content : k3.health) {
                pstmt.setString(1, content);
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
            String sql="INSERT INTO biz_news(content) VALUES(?)";
            PreparedStatement pstm= connection.prepareStatement(sql);

            for (String content: k4.bussiness){
                pstm.setString(1,content);
                pstm.executeUpdate();
            }
            pstm.close();
            connection.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        CRUD c=new CRUD();
        c.insertHealthNews();
        c.insertSportsNews();
        c.inertPoliticalNews();
        c.insertBussinessNews();


    }

}