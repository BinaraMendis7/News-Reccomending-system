package com.example.demo12;

import java.util.List;

public class User {
    private String username;
    private String name;
    private String password;
    private List<String> preferredCategories;
    private List<Integer> likedArticle;
    private List<Article> articles;
    private String typeToRecomend;

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

    public List<Integer> getlikeArticles() {
        return likedArticle;
    }

    public void setReadArticles(List<Integer> readArticles) {
        this.likedArticle = readArticles;
    }


    public User(String username, List<String> preferredCategories, List<Integer> likedArticles) {
        this.username = username;
        this.preferredCategories = preferredCategories;
        this.likedArticle = likedArticles;
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

    /*public void like(){

        for (Article article: getArticles()){
            String title=article.getTitle();
            int ID=article.getArticle_ID();
            String type=crud.searchArticle(ID);
            crud.InsertLike(getUsername(), ID, type,title);
        }
    }*/

}
