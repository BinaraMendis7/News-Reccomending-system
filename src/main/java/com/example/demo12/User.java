package com.example.demo12;

import java.util.List;

public class User {
    private String username;
    private String name;
    private String password;
    List<String> preferredCategories;
    List<Integer> likedArticle;
    String typeToRecomend;

    public String getTypeToRecomend() {
        return typeToRecomend;
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
}
