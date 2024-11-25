package com.example.demo12;

public class userHistory {
    private String User_Name;
    private int Article_ID;
    private String type;

    public userHistory(String user_Name, int article_ID, String type) {
        User_Name = user_Name;
        Article_ID = article_ID;
        this.type = type;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public int getArticle_ID() {
        return Article_ID;
    }

    public void setArticle_ID(int article_ID) {
        Article_ID = article_ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}