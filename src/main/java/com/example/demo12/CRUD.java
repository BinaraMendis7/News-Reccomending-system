package com.example.demo12;



import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class CRUD extends database {

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

    public void insertArticle() {
        try {
            getConnection();

            Scraping s = new Scraping();
            s.news("https://www.newswire.lk/category/business/");

            String sql = "INSERT INTO biz_news (title, content) VALUES (?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            for (String title : s.body_list.keySet()) {
                String title1 = title;
                String content = s.body_list.get(title);

                pstmt.setString(1, title1);
                pstmt.setString(2, content);

                pstmt.executeUpdate();
            }

            pstmt.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void inertInternationalNews(){
        try {
            getConnection();

            Scraping s = new Scraping();
            s.news("https://www.newswire.lk/category/international-news/");

            String sql = "INSERT INTO international_news (title, content) VALUES (?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            for (String title : s.body_list.keySet()) {
                String title1 = title;
                String content = s.body_list.get(title);

                pstmt.setString(1, title1);
                pstmt.setString(2, content);

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

            Scraping s = new Scraping();
            s.news("https://www.newswire.lk/category/sports/");

            String sql = "INSERT INTO sports (title, content) VALUES (?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            for (String title : s.body_list.keySet()) {
                String title1 = title;
                String content = s.body_list.get(title);

                pstmt.setString(1, title1);
                pstmt.setString(2, content);

                pstmt.executeUpdate();
            }

            pstmt.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
    public static void main(String[] args) {
        CRUD c=new CRUD();
        c.insertArticle();
        c.inertInternationalNews();
        c.insertSportsNews();

    }

}