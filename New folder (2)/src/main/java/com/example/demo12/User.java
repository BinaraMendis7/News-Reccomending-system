package com.example.demo12;

import javafx.collections.ObservableList;

import java.util.List;

public class User {
    private String username;
    private String name;
    private String password;
    private List<String> preferredCategories;
    private List<Article> articles;
    private String typeToRecomend;
    private ObservableList<Article> userHistory;
    Article article;
    public List<Article> readArtcles;

    public ObservableList<Article> getUserHistory() {
        return userHistory;
    }

    public void setUserHistory(ObservableList<Article> userHistory) {
        this.userHistory = userHistory;
    }

    public List<Article> getArticles() {
        return articles;
    }
    public User(){
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }



    public String getTypeToRecomend(){
        return typeToRecomend;
    }
    public void addLikedArticle(Article article){
        articles.add(article);

    }

    public void setTypeToRecomend(String typeToRecomend) {
        this.typeToRecomend = typeToRecomend;
    }

    public User(String username, String typeToRecomend) {
        this.username = username;
        this.typeToRecomend = typeToRecomend;
    }

    public List<String> getPreferredCategories() {
        return preferredCategories;
    }

    public void setPreferredCategories(List<String> preferredCategories) {
        this.preferredCategories = preferredCategories;
    }

    public User(String username, String name, String password,List<String> preferredCategories) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.preferredCategories=preferredCategories;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public User(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void siginUp(){
        CRUD update = new CRUD();
        update.insert(getName(),getUsername(),getPassword(),getPreferredCategories());
    }
    public boolean SignIn(){
        String username=getUsername();
        String password=getPassword();
        CRUD crud=new CRUD();
        if (crud.read(username,password)==true){
            return true;
        }
        else{
            return false;
        }

    }
    CRUD crud=new CRUD();

    public void like(Article article){
        String title=article.getTitle();
        int ID=article.getArticle_ID();
        String type=crud.searchArticle(ID);
        crud.InsertLike(getUsername(), ID, type,title);
    }

    public void viewHistory(User user){
        setUserHistory(crud.readUserHistory(user));
    }

    public void readArticle(Article article) {
        readArtcles.add(article);
        article.readUser(this);

    }




}
