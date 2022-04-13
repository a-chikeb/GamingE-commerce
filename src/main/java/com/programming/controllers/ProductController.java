package com.programming.controllers;

import com.programming.models.Category;
import com.programming.models.Color;
import com.programming.models.Product;
import com.programming.repositories.CategoryRepository;
import com.programming.repositories.ColorRepository;
import com.programming.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
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

    @GetMapping("/category/{category}/{page}")
    public List<Product> findProductByCategory(@PathVariable("category") String category,@PathVariable("page") int page){

        //System.out.println("method findProductByCategory");


        //int size = productRepository.findProductByCategory_Name(category).size();
        //int from = page > 0 ? (page-1) * 20 : 0;
        //int to = page * 20;

        //System.out.println("Page "+page);
        //System.out.println("Search Category From "+from+" to "+to);
        category = category.replace("-"," ");
        Pageable paging = PageRequest.of(page-1, 20);

        return productRepository.findProductByCategory_Name(category,paging);
    }



    //  category/Moniteur-Et-Ecran-Gamer/12
    @GetMapping("/category/{category}/size/{size}")
    public List<Product> getProductByCategoryAndSize(@PathVariable("category") String category,@PathVariable("size") int size){

        category = category.replace("-"," ");

        Pageable paging = PageRequest.of(0, size);

        return productRepository.findProductByCategory_Name(category,paging);
    }


    @GetMapping("/similar/{category}/size/{size}")
    public List<Product> getProductSimilarCategoryAndSize(@PathVariable("category") String category,@PathVariable("size") int size){

        Pageable paging = PageRequest.of(0, size);

        return productRepository.findProductByCategory_Name(category,paging);
    }


    @GetMapping("/parent_category/{category}/size/{size}")
    public List<Product> getProductByParentCategoryAndSize(@PathVariable("category") String category,@PathVariable("size") int size){

        category = category.replace("-"," ");

        Pageable paging = PageRequest.of(0, size);

        return productRepository.findProductByCategory_ParentCategoryOrderByPriceDesc(category,paging);
    }

    @GetMapping("/parent_category/{category}/page/{page}")
    public List<Product> getProductByParentCategoryAndPage(@PathVariable("category") String category,@PathVariable("page") int page){

        System.out.println("parent category");
        category = category.replace("-"," ");
        System.out.println(category);
        System.out.println(page);

        Pageable paging = PageRequest.of(page-1, 20);

        return productRepository.findProductByCategory_ParentCategoryOrderByPriceDesc(category,paging);
    }



    @GetMapping("/search/{name}/{page}")
    public List<Product> searchByNameOrLetter(@PathVariable("name") String name, @PathVariable("page") int page){
        Pageable paging = PageRequest.of(page - 1, 20);
        return productRepository.findProductByNameContaining(name,paging);
    }


    @GetMapping("/{id}")
    public Product getProductDetails(@PathVariable("id") Long id){
        return productRepository.findProductById(id);
    }



    @PostMapping("/add")
    public void addProduct(@RequestBody Map<String,Object> data){

        /*Product product = new Product(data.get("name").toString(),data.get("description").toString(),data.get("images").toString(),data.get("brand_images").toString(),Integer.parseInt(data.get("total").toString()),data.get("overview").toString());

        Category cat = categoryRepository.findByName(data.get("category").toString());
        product.setCategory(cat);
        
         */
        //System.out.println(data.get("colors"));


        //productRepository.save(product);
        //System.out.println(product);

    }



    //Func =>Get Count Of Searched Products
    @GetMapping("/search/{name}/count")
    public Integer searchGetCountProduct(@PathVariable("name") String name){
        return productRepository.findProductByNameContaining("%"+name+"%").size();
    }
    //Func =>Get Count Of Category Products

    @GetMapping("/category/{name}/count")
    public Integer getCategoryProductCount(@PathVariable("name") String name){
        name = name.replace("-"," ");
        return productRepository.findProductByCategory_Name(name).size();
    }

    @GetMapping("/parent_category/{name}/count")
    public Integer getParentCategoryProductCount(@PathVariable("name") String name){
        name = name.replace("-"," ");
        return productRepository.findProductByCategory_ParentCategory(name).size();
    }


    //Func =>Get Product by filter (+pageable)
    //Name
    //Price Low To High
    //Price High To Low
    //New Arrivals
    @GetMapping("/search/{name}/{page}/filter={by}")
    public List<Product> searchByNameOrLetterFilterBy(@PathVariable("name") String name, @PathVariable("page") int page,@PathVariable("by") String by){
        Pageable paging = PageRequest.of(page - 1, 20);

        switch (by) {
            case "name":
                return productRepository.findProductByNameContainingOrderByNameAsc(name, paging);
            case "high-to-low":
                return productRepository.findProductByNameContainingOrderByPriceDesc(name, paging);
            case "low-to-high":
                return productRepository.findProductByNameContainingOrderByPriceAsc(name, paging);
            default:
                return productRepository.findProductByNameContaining(name, paging);
        }

    }




}
