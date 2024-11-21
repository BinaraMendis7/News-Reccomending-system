package com.example.demo12;

import java.io.IOException;
import java.util.*;

public class KeyWordExtraction {

    public static void main(String[] args) throws IOException {
        CRUD crud=new CRUD();
        crud.read();

        List<String> documents = new ArrayList<>(crud.Content); // Extract values (content) into a list


        Map<String, List<String>> categoryKeywords = new HashMap<>();
        categoryKeywords.put("sports", Arrays.asList("football", "team", "match", "goal", "championship", "athlete", "competition", "tournament", "league","World Cup","Champions","T20"));
        categoryKeywords.put("technology", Arrays.asList("technology", "tech", "innovation", "smartphone", "AI"));
        categoryKeywords.put("politics", Arrays.asList("politician", "government", "nation", "policy", "reforms"));
        categoryKeywords.put("health", Arrays.asList("health", "wellness", "fitness", "nutrition", "medicine"));


        for (int i = 0; i < documents.size(); i++) {
            String category = categorizeDocument(documents.get(i), categoryKeywords);
            System.out.println("Document " + (i + 1) + " is categorized as: " + category);
        }
    }

    public static String categorizeDocument(String document, Map<String, List<String>> categoryKeywords) {
        // Extract keywords from the document
        Set<String> keywords = extractKeywords(document);

        // Score matching keywords with predefined categories
        Map<String, Integer> categoryScores = new HashMap<>();
        for (String category : categoryKeywords.keySet()) {
            int score = 0;
            for (String keyword : keywords) {
                if (categoryKeywords.get(category).contains(keyword.toLowerCase())) { // Lowercase comparison
                    score++;
                }
            }
            categoryScores.put(category, score);
        }


        return categoryScores.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Uncategorized");
    }

    public static Set<String> extractKeywords(String document) {

        String[] tokens = document.toLowerCase().split("\\W+");


        return new HashSet<>(Arrays.asList(tokens));
    }
}
