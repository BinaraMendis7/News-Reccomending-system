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
        categoryKeywords.put("Buissiness",Arrays.asList("business", "enterprise", "finance", "economy", "trade", "commerce", "investment", "stocks", "market", "shares", "revenue", "profit", "loss", "expenses", "capital", "assets", "liabilities", "management", "leadership", "strategy", "corporation", "startup", "entrepreneurship", "brand", "branding", "marketing", "advertising", "sales", "customer", "retail", "e-commerce", "supply chain", "logistics", "procurement", "merger", "acquisition", "innovation", "disruption", "fintech", "blockchain", "digital currency", "cryptocurrency", "venture capital", "angel investor", "fundraising", "partnership", "globalization", "diversification", "corporate culture", "growth", "expansion", "downsizing", "outsourcing", "insourcing", "automation", "technology", "AI", "data analytics", "cloud computing", "cybersecurity", "sustainability", "green business", "corporate governance", "compliance", "ethics", "workforce", "productivity", "forecast", "analysis", "reporting", "KPIs", "budgeting", "auditing", "risk management", "cash flow", "dividends", "equity", "ROI", "performance", "negotiation", "pricing", "distribution", "regulations", "taxation", "accounting", "financial planning", "credit", "debt", "interest rate", "economic policy", "international trade", "export", "import", "tariffs", "supply and demand", "consumer behavior", "competition", "monopoly", "oligopoly", "valuation", "IPO", "stock market", "shareholder", "stakeholder", "franchise", "joint venture", "marketing strategy", "digital marketing", "SEO", "content marketing", "lead generation", "customer relationship", "CRM", "supply chain management", "logistical efficiency", "business operations", "operational excellence", "performance metrics", "strategic planning", "corporate identity", "organizational structure", "employee engagement", "HR management", "talent acquisition", "employee retention", "diversity", "inclusion", "CSR", "stakeholder value", "business ethics", "legal compliance", "international business", "economic growth", "business cycle", "market research", "industry trends", "consumer trends", "branding strategy", "product launch", "monetization", "B2B", "B2C", "customer acquisition", "customer retention", "reputation management", "crisis management", "competitive advantage", "pricing strategy", "innovation strategy", "market penetration", "market segmentation", "target audience", "business intelligence", "business analytics", "data-driven decisions", "process improvement", "lean management", "Six Sigma", "project management", "change management", "business transformation", "organizational behavior", "corporate social responsibility", "ethics policy", "internal controls", "financial health", "economic indicators", "market capitalization", "brand loyalty", "sales funnel", "economic downturn", "recession", "recovery", "entrepreneurial mindset", "venture creation", "cash management", "revenue streams", "business partnerships", "collaboration", "strategic alliances", "business models", "market dominance", "industry leadership","economic growth", "economic recession", "financial markets", "stock market", "investment banking", "corporate finance", "business strategy", "business model", "supply chain management", "human resources", "marketing", "sales", "product development", "business ethics", "corporate social responsibility", "entrepreneurship", "small business", "startups", "industry trends", "market analysis"));


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
