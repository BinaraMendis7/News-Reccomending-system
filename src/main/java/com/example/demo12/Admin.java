package com.example.demo12;

import java.util.ArrayList;
import java.util.List;

public class Admin{
    private static final String username="Admin1";
    private static final String password="admin";
    private String userName_Entered;
    private String password_entered;

    private List<Article> articles=new ArrayList<>();
    public String getUserName_Entered() {
        return userName_Entered;
    }

    public void setUserName_Entered(String userName_Entered) {
        this.userName_Entered = userName_Entered;
    }
    public String getPassword_entered() {
        return password_entered;
    }

    public void setPassword_entered(String password_entered) {
        this.password_entered = password_entered;
    }

    public String getusername(){
        return username;
    }
    public String getpassword(){
        return password;
    }
    public boolean AdminSignIN(){
        if (getusername().equals(getUserName_Entered())&&getpassword().equals(getPassword_entered())){
            return true;
        }else {
            return false;
        }
    }
    public void addArticle(Article article){
        articles.add(article);

    }
}

