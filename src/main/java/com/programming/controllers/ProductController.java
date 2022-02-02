package com.programming.controllers;

import com.programming.models.Category;
import com.programming.models.Color;
import com.programming.models.Product;
import com.programming.repositories.CategoryRepository;
import com.programming.repositories.ColorRepository;
import com.programming.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ColorRepository colorRepository;


    @GetMapping
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/category/{category}")
    public List<Product> findProductByCategory(@PathVariable("category") String category){
        return productRepository.findProductByCategory_Name(category);
    }

    @GetMapping("/search/{name}")
    public List<Product> searchByNameOrLetter(@PathVariable("name") String name){
        return productRepository.findProductByNameContaining(name);
    }

    @GetMapping("/{id}")
    public Product getProductDetails(@PathVariable("id") Long id){
        return productRepository.findProductById(id);
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody Map<String,Object> data){

        /*Product product =
        new Product(data.get("name").toString(),data.get("description").toString(),data.get("images").toString(),data.get("brand_images").toString(),Integer.parseInt(data.get("total").toString()),data.get("overview").toString());

        Category cat = categoryRepository.findByName(data.get("category").toString());
        product.setCategory(cat);
        
         */
        //System.out.println(data.get("colors"));


        //productRepository.save(product);
        //System.out.println(product);

    }
}
