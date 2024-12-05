package com.example.demo12;


import java.util.HashMap;
import java.util.List;

public class Article {
   private String title;
   private String content;
   private int Article_ID;
   private String category;
   Scraping scraping=new Scraping();
    CRUD crud=new CRUD();
    Admin admin=new Admin();
    User user=new User();
     public List<User> readers;

    public String getCategory() {
        return category;
    }
    public Article(){

    }

    public Article(String content, int article_ID) {
        this.content = content;
        Article_ID = article_ID;
    }

    public Article(String title, String category) {
        this.title = title;
        this.category = category;
    }

    public void setCategory(String category) {
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
    }

    public void scrapeArticle(){
        System.out.println("hii");
        scraping.news("https://www.newswire.lk/category/business/");
        scraping.news("https://www.newswire.lk/category/international-news/");
        scraping.news("https://www.newswire.lk/category/sports/");

    }


    public void insertArticle(){
        CRUD crud=new CRUD();
        scrapeArticle();

        int Article_ID = 1;
        for (String Article_Name : scraping.body_list.keySet()) {
            String content = scraping.body_list.get(Article_Name);
            setArticle_ID(Article_ID);
            setTitle(Article_Name);
            setContent(content);
            crud.insertArticle(getTitle(),getContent(),getArticle_ID());
            Article article=new Article(getTitle(),getContent(),getArticle_ID());
            admin.addArticle(article);

            Article_ID++;
        }
    }

    public void insertSports(){

        KeyWordExtraction k2=new KeyWordExtraction();
        k2.catergorize();

        for (int ID : k2.sports.keySet()) {
            setContent(k2.sports.get(ID));
            setArticle_ID(ID);
            crud.insertSportsNews(getContent(),getArticle_ID());
        }

    }

    public void insertBusiness(){
        KeyWordExtraction k4=new KeyWordExtraction();
        k4.catergorize();

        for (int ID: k4.business.keySet()){
            setContent(k4.business.get(ID));
            setArticle_ID(ID);
            crud.insertBussinessNews(getContent(),getArticle_ID());
        }
    }
    public void insertHealth(){
        KeyWordExtraction k3=new KeyWordExtraction();
        k3.catergorize();
        HashMap<Integer,String> health=new HashMap<>(k3.health);

        for (int ID:health.keySet()) {
            setContent(health.get(ID));
            setArticle_ID(ID);
            crud.insertHealthNews(getContent(),getArticle_ID());
        }
    }
    public void readUser(User user){
        readers.add(user);
        user.readArticle(user.article);

    }

}
