package com.example.demo12;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                // Prepare the statement to insert a new user
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, password);

                // Execute the insert
                preparedStatement.executeUpdate();
                System.out.println("User inserted successfully.");

                // Close the prepared statement
                preparedStatement.close();
            }

            // Close the ResultSet
            oldUsernames.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void read(){
        try{
            getConnection();
        }
        catch (Exception e){

        }
    }
}