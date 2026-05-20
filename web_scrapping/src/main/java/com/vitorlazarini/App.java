package com.vitorlazarini;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.vitorlazarini.entities.Sku;

public class App {
    public static void main(String[] args) {
        // URL da página
        String url = "https://infosimples.com/vagas/desafio/stellarcraft/product.html";
        List<Sku> skus = new ArrayList<>();

        try {
            // URL da página com os dados
            Document document = Jsoup.connect(url).get();

            // Seletor de CSS
            
            //Title
            Element titleElement = document.getElementById("product_title");
            String title = titleElement.text();
            System.out.println("Title: " + title);

            //Brand
            Element brandElement = document.selectFirst(".product-brand");
            String brand = brandElement.text();
            System.out.println("Brand: " + brand);

            //Categories


            //Description
            Element descriptionElement = document.getElementById("tab-description");
            String description = descriptionElement.text();
            System.out.println("Description: " + description);

            //Categoria SKU
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

        } catch (Exception e) {
            System.out.println("Erro ao fazer o scraping: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
