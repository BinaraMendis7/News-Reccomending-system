package com.example.demo12;

import javafx.collections.ObservableList;


import java.util.*;

public class Recommend {
    CRUD crud = new CRUD();
    static ArrayList<Article> recommendedArticles;

    public void Recommend(User user) {
        crud.readUser();
        crud.readUserHistory();
        recommendedArticles =new ArrayList<>();

        for (String username : crud.articlesLikedByUser.keySet()) {
            if (username.equals(user.getUsername())){
                ArrayList<Integer> ID_list = crud.articlesLikedByUser.get(username);
                int biz_count = 0;
                int health_count = 0;
                int sports_count = 0;

                for (int ID : ID_list) {
                    String type = crud.searchArticle(ID);
                    if (type.equals("Business")) {
                        biz_count++;
                    } else if (type.equals("Health")) {
                        health_count++;
                    } else if (type.equals("Sport")) {
                        sports_count++;
                    }
                }

                HashMap<String, Integer> counts = new HashMap<>();
                counts.put("Business", biz_count);
                counts.put("Health", health_count);
                counts.put("Sport", sports_count);

                int maximum = max(counts);
                String typeToRecommend = searchForType(counts, maximum);
                user.setTypeToRecomend(typeToRecommend);

                recommendedArticles = getRecommendedArticles(username, user.getTypeToRecomend());
                System.out.println(recommendedArticles);
                System.out.println(recommendedArticles.size());
            }
        }

    }

    public String searchForType(HashMap<String, Integer> counts, int maximum) {
        String type = null;
        for (String category : counts.keySet()) {
            if (counts.get(category) == maximum) {
                type = category;
                break;
            }
        }
        return type;
    }

    public int max(HashMap<String, Integer> counts) {
        Collection<Integer> Count = counts.values();
        int max = Integer.MIN_VALUE;
        for (int count : Count) {
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    public ArrayList<Article> getRecommendedArticles(String username, String category) {
        crud.readUserHistory();
        ObservableList<Article> allArticles = crud.getArticlesFromDatabase();
        Collection<ArrayList<Integer>> readArticleIDs = crud.articlesLikedByUser.values();
        ArrayList<Article> recommendedArticles = new ArrayList<>();
        for (Article article : allArticles) {
            if (crud.searchArticle(article.getArticle_ID()).equals(category) && !readArticleIDs.contains(article.getArticle_ID())) {
                recommendedArticles.add(article);
            }
        }

        return recommendedArticles;
    }
}
