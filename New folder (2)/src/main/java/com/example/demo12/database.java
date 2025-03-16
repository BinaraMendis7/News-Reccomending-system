package com.example.demo12;

import java.sql.*;

public abstract class database {
    protected String url = "jdbc:mysql://localhost:3306/news_system";
    protected String user = "root";
    protected String password = "SINcostan**123";

    protected Connection connection;
    protected Statement statement;

    protected void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
    }







}