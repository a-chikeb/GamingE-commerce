package com.programming.controllers;

import com.programming.models.Category;
import com.programming.models.Color;
import com.programming.models.Product;
import com.programming.repositories.BasketRepository;
import com.programming.repositories.CategoryRepository;
import com.programming.repositories.ColorRepository;
import com.programming.repositories.ProductRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ColorRepository colorRepository;


    @GetMapping("/page/{page}")
    public List<Product> getAllProducts(@PathVariable("page") int page){
        Pageable paging = PageRequest.of(page-1, 20);
        return productRepository.findBy(paging);
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
    public void addProduct(@RequestParam("image") MultipartFile[] files,
                           @RequestParam("name") String name,
                           @RequestParam("description") String description,
                           @RequestParam("price") Integer price,
                           @RequestParam("total") Integer total,
                           @RequestParam("category_id") Integer category_id,
                           @RequestParam("overview") String overview) throws IOException {


        Category category = categoryRepository.findById(Long.valueOf(category_id)).get();

        Product product = new Product();
        product.setCategory(category);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setTotal(total);
        product.setOverview(overview);


        //random number for don't duplicate file
        int min = 100;
        int max = 100000;
        int nb = (int)Math.floor(Math.random()*(max-min+1)+min);


        StringBuilder image = new StringBuilder();
        for (MultipartFile file : files) {
            //Save Image
            String folder = "src/main/resources/static/images/";

            Path path = Paths.get(folder + nb + file.getOriginalFilename());

            Files.write(path,file.getBytes());

            image.append(folder).append(nb).append(file.getOriginalFilename()).append(",");
        }

        product.setImages(String.valueOf(image));

        productRepository.save(product);

        System.out.println(product);

    }


    @PostMapping("/delete/{id}")
    @Transactional
    public void deleteProduct(@PathVariable int id){
        productRepository.delete(productRepository.findById((long) id).get());
        basketRepository.deleteByProductId(id);
        System.out.println("product deleted successfully");

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
