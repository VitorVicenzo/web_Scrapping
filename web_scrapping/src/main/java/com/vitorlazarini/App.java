package com.vitorlazarini;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitorlazarini.entities.Reviews;
import com.vitorlazarini.entities.Sku;

public class App {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static void main(String[] args) {

        // Arquivo destino do JSON
        File arquivo = new File("produto.json");

        // URL da página
        String url = "https://infosimples.com/vagas/desafio/stellarcraft/product.html";

        // Listas
        List<Sku> skus = new ArrayList<>();
        List<Reviews> reviews = new ArrayList<>();

        try {

            // URL da página com os dados + parse
            Document document = Jsoup.connect(url).get();

            // Seletor de CSS

            // Title
            Element titleElement = document.getElementById("product_title");
            String title = titleElement.text();
            System.out.println("Title: " + title);
            System.out.println();

            // Brand
            Element brandElement = document.selectFirst(".product-brand");
            String brand = brandElement.text();
            System.out.println("Brand: " + brand);
            System.out.println();

            // Categories
            Elements categorieElement = document.select(".breadcrumb-bar");
            String[] categories = new String[categorieElement.size()];
            for (int i = 0; i < categorieElement.size(); i++) {
                categories[i] = categorieElement.text();
                System.out.println("Categories: {" + categories[i] + " }");
            }
            List<String> cleanCategories = Arrays.stream(categories).flatMap(categorie -> Arrays.stream(categorie.split(" › "))).distinct().collect(Collectors.toList());
            System.out.println();

            // Description
            Element descriptionElement = document.getElementById("tab-description");
            String description = descriptionElement.text();
            System.out.println("Description: " + description);
            System.out.println();

            // SKU
            Elements products = document.select(".variant-btn");

            for (Element product : products) {

                // Nome
                Elements nameElement = product.select(".vname");
                String name = nameElement != null ? nameElement.text() : "N/A";

                // Preço atual
                Elements precoAtualElement = product.select(".vprice");
                String current_price = precoAtualElement != null ? (precoAtualElement.text()) : null;

                // Preço antigo
                Elements precoAntigoElement = product.select(".vprice-old");
                String old_price = precoAntigoElement != null ? (precoAntigoElement.text()) : null;

                // Verificar disponibilidade em estoque
                Elements disponibilidadeElement = product.select(".vunavail");
                Boolean available = disponibilidadeElement.isEmpty();
                skus.add(new Sku(name, current_price, old_price, available));
            }
            System.out.println(skus);
            System.out.println();

            // Specifications
            Elements specificationElement = document.select("#tab-specs .specs-table tbody tr");
            String specifications[][] = new String[specificationElement.size()][2];
            int index = 0;
            for (Element spec : specificationElement) {

                String label = spec.select("td:first-child").text().trim();
                String value = spec.select("td:nth-child(2)").text().trim();

                if (!label.isEmpty()) {
                    specifications[index][0] = label;
                    specifications[index][1] = value;
                    index++;
                }
            }
            System.out.println(Arrays.deepToString(specifications));
            System.out.println();

            // Reviews
            Elements reviewElements = document.select("#tab-reviews .review-card");

            for (Element review : reviewElements) {
                
                //Nome
                Elements nameReview = review.select(".reviewer-name");
                String reviewName = nameReview != null ?  (nameReview.text()) : null;

                //Data
                Elements dateReview = review.select(".reviewer-date");
                String reviewDate = dateReview != null ? (dateReview.text()) : null;

                //Avaliação
                Elements scoreReview = review.select(".review-stars");
                String reviewScores = (scoreReview.text().trim());
                Integer score = (int) reviewScores.chars().filter(ch -> ch == '★').count();

                //Texto da avaliação
                Elements textReview = review.select(".review-text");
                String reviewText = textReview != null ? (textReview.text()) : null;

                reviews.add(new Reviews(reviewName.toString(), reviewDate.toString(), score , reviewText.toString()));
            }
            System.out.println(reviews);

            Element averageScore = document.selectFirst(".avg-score");
            Float reviews_average_score = Float.parseFloat(averageScore.text());
            System.out.println();
            System.out.println("Average score: " + reviews_average_score);

            // Montando o JSON
             Map<String, Object> dadosJson = new LinkedHashMap<>();
            dadosJson.put("title", title);
            dadosJson.put("brand", brand);
            dadosJson.put("categories", cleanCategories);
            dadosJson.put("description", description);
            dadosJson.put("skus", skus);
            dadosJson.put("specifications", specifications);
            dadosJson.put("reviews", reviews);
            dadosJson.put("reviews_average_score", reviews_average_score);
            dadosJson.put("url", url);

            MAPPER.writerWithDefaultPrettyPrinter().writeValue(arquivo, dadosJson);
            
        } catch (Exception e) {
            System.out.println("Erro ao fazer o scraping: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
