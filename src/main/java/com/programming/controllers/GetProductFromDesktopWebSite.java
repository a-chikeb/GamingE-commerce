package com.programming.controllers;

import com.programming.models.Category;
import com.programming.models.Product;
import com.programming.repositories.CategoryRepository;
import com.programming.repositories.ProductRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
/*
@RestController
@RequestMapping("/api/v1/scraping")
public class GetProductFromDesktopWebSite {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String getProduct() throws IOException {
        //Carte Mere Amd
        String url = "https://desktop.ma/18-config-pc-gamer-intel";


        Document doc = Jsoup.connect(url).get();

        Elements elements = doc.select("#center_column > ul > li");

        for (Element el:elements) {
            String productUrl = el.getElementsByClass("product-name").select("a").attr("href");
            getProductDetails(productUrl);
            System.out.println("--------------");
        }

        return "";


    }

    void getProductDetails(String url) throws IOException {
        Category cat = categoryRepository.findByName("Config PC Gamer INTEL");

        Document doc = Jsoup.connect(url).get();



        String productName = doc.select("#center_column > div:nth-child(2) > div > div.pb-center-column.col-xs-12.col-sm-7.col-md-6 > h1").text().replace("| DESKTOP.MA","");
        String reference = doc.select("#product_reference > span").text();
        String etat = doc.select("#product_condition > span").text();

        String shortDescription = "";
        if(doc.select("#short_description_content > p > span").text().isBlank()){
            shortDescription = doc.select("#short_description_content > p").text();
        }else{
            shortDescription = doc.select("#short_description_content > p > span").text();
        }

        String price = doc.select("#our_price_display").attr("content");

        Elements images = doc.select("#thumbs_list_frame > li");

        String imgs = "";

        for (Element img : images) {
            imgs = imgs + img.select("a").attr("href")+",";
        }

        String description = doc.select("#ttmoreinfo > li > div").html();

        Product product = new Product();

        product.setName(productName);
        product.setDescription(shortDescription);
        product.setOverview(description);
        product.setReference(reference);
        product.setPrice(price);
        product.setEtat(etat);
        product.setImages(imgs);
        product.setTotal(10);
        product.setCategory(cat);

        productRepository.save(product);

    }
}
*/