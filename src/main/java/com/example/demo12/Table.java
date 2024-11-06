package com.example.demo12;

public class Table {
    private int Article_ID;
    private String Type;
    private String Article_Name;

    public Table(int article_ID, String type, String article_Name) {
        Article_ID = article_ID;
        Type = type;
        Article_Name = article_Name;
    }

    public int getArticle_ID() {
        return Article_ID;
    }

    public String getType() {
        return Type;
    }

    public String getArticle_Name() {
        return Article_Name;
    }
}
