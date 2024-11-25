package com.example.demo12;

public class Article {
   private String title;
   private String content;
   private int Article_ID;
   private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Article(String title, String content, int article_ID, String category) {
        this.title = title;
        this.content = content;
        Article_ID = article_ID;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getArticle_ID() {
        return Article_ID;
    }

    public void setArticle_ID(int article_ID) {
        Article_ID = article_ID;
    }

    public Article(String title, String content, int article_ID) {
        this.title = title;
        this.content = content;
        Article_ID = article_ID;
    }

    public Article(int article_ID){
        Article_ID=article_ID;
    }}
