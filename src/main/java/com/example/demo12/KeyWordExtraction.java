package com.example.demo12;

import java.io.IOException;
import java.util.*;

public class KeyWordExtraction {
    ArrayList<String> sport=new ArrayList<>();
    ArrayList<String> politics=new ArrayList<>();
    ArrayList<String> health=new ArrayList<>();
    ArrayList<String> bussiness=new ArrayList<>();


    public void catergorize(){
        CRUD crud=new CRUD();
        crud.read();

        List<String> documents = new ArrayList<>(crud.Content);


        Map<String, List<String>> categoryKeywords = new HashMap<>();
        categoryKeywords.put("sports", Arrays.asList("sports", "football", "cricket", "athletics", "sports news", "match", "tournament", "team", "Olympics", "medals", "championship", "league", "players", "coaching", "goal", "soccer", "rugby", "basketball", "sports competition", "game results", "fitness", "stadium","soccer", "baseball", "hockey", "golf", "motorsports", "boxing", "martial arts", "extreme sports", "sports news", "sports commentary", "sports analysis", "sports statistics", "sports records", "sports injuries", "sports scandals", "sports betting", "sports fan culture", "sports merchandise"));
        categoryKeywords.put("health", Arrays.asList("health", "wellness", "fitness", "disease", "healthcare", "medicine", "mental health", "hospital", "doctors", "nutrition", "COVID-19", "pandemic", "health sector", "vaccine", "surgical procedures", "treatment", "exercise", "health tips", "lifestyle","health and wellness, public health, preventive health, holistic health, mental health, physical health, emotional health, spiritual health, well-being, lifestyle, health insurance, healthcare system, heart disease, stroke, cancer, diabetes, Alzheimer's disease, Parkinson's disease, arthritis, asthma, allergies, chronic pain, infectious diseases, autoimmune diseases, genetic disorders, medication, surgery, chemotherapy, radiation therapy, physical therapy, occupational therapy, speech therapy, acupuncture, massage therapy, yoga, meditation, aromatherapy, homeopathy, alternative medicine, nutrition, diet, healthy eating, balanced diet, weight loss, weight gain, obesity, malnutrition, vitamins, minerals, protein, carbohydrates, fats, fiber, hydration, exercise, physical activity, fitness, workout, gym, weightlifting, cardio, yoga, Pilates, running, swimming, cycling, sports, athletic performance, mental health, depression, anxiety, stress, bipolar disorder, schizophrenia, PTSD, OCD, ADHD, suicide prevention, therapy, counseling, psychiatry, psychology, sexual health, sexually transmitted infections (STIs), reproductive health, family planning, contraception, infertility, sexual dysfunction, aging, elderly care, geriatrics, senior health, age-related diseases, dementia, Alzheimer's disease, Parkinson's disease, osteoporosis, substance abuse, addiction, drug abuse, alcohol abuse, smoking, nicotine addiction, environmental health, air pollution, water pollution, noise pollution, climate change, food safety, occupational health"));
        categoryKeywords.put("politics",Arrays.asList("election results", "political campaign", "government policy", "public policy", "international relations", "foreign policy", "political ideology", "political party leader", "political analyst", "political commentator", "political scandal", "political corruption", "political reform", "political activism", "political protest", "political rally"));
        categoryKeywords.put("Buissiness",Arrays.asList("economic growth", "economic recession", "financial markets", "stock market", "investment banking", "corporate finance", "business strategy", "business model", "supply chain management", "human resources", "marketing", "sales", "product development", "business ethics", "corporate social responsibility", "entrepreneurship", "small business", "startups", "industry trends", "market analysis"));


        for (int i = 0; i < documents.size(); i++) {
            String category = categorizeDocument(documents.get(i), categoryKeywords);
            if (category.equals("sports")){
                sport.add(documents.get(i));
            } else if (category.equals("health")) {
                health.add(documents.get(i));
            } else if (category.equals("politics")) {
                politics.add(documents.get(i));
            } else if (category.equals("Buissiness")) {
                bussiness.add(documents.get(i));
            }
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
