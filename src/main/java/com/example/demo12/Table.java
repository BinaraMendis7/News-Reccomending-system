package com.example.demo12;

public class Table {
    private int Article_ID;
    private String Article_Name;

    private String type;


    public Table(int article_ID, String article_Name, String type) {
        Article_ID = article_ID;

        Article_Name = article_Name;
        this.type =type;
    }

    public int getArticle_ID() {
        return Article_ID;
    }

    public void setArticle_ID(int article_ID) {
        Article_ID = article_ID;
    }

    public String getArticle_Name() {
        return Article_Name;
    }

    public void setArticle_Name(String article_Name) {
        Article_Name = article_Name;
    }

    public String getType() {
        return type;
    }

    public void setLink(String type) {
        this.type = type;
    }
}
