package com.example.demo12;

import java.sql.*;


public class database {


    public void getConnection(String query, String name, String username, String password) {
        String url = "jdbc:mysql://localhost:3306/news_system";
        String User = "root";
        String Password = "SINcostan**123";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, User, Password);
            Statement statement=connection.createStatement();
            ResultSet Old_user_name=statement.executeQuery("SELECT username from user");

            while (Old_user_name.next()){
                if (Old_user_name.getString(1).equals(username)){
                    System.out.println("User name exist");
                    break;
                }
            }


            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);


            preparedStatement.executeUpdate();


            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void read() {
    }
}
