package com.example.demo12;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;

public class Scraping {
    HashMap<String, String> body_list = new HashMap<>();

    public String news(String url) {
        ArrayList<String> links = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).get();


            Elements newsLinks = document.select(".entry-grid-content a");
            for (Element link : newsLinks) {
                String navLink = link.attr("href");
                links.add(navLink);
            }
            for (String sublink : links) {
                try {
                    Document document1 = Jsoup.connect(sublink).get();

                    String title = document1.select(".entry-header h1").text();


                    Elements bodyLinks = document1.select("p");
                    StringBuilder content = new StringBuilder();
                    for (Element body : bodyLinks) {
                        content.append(body.text()).append("\n");
                    }

                    body_list.put(title, content.toString());

                } catch (Exception e) {
                    System.out.println("Error fetching article content: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return url;
    }
}
