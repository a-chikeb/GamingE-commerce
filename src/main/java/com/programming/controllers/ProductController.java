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
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
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



    /*@PostMapping("/add")
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


     */

    @PostMapping("/add")
    public void addProduct(@RequestParam("image") MultipartFile[] files,
                           @RequestParam("name") String name,
                           @RequestParam("description") String description,
                           @RequestParam("price") Integer price,
                           @RequestParam("total") Integer total,
                           @RequestParam("category_id") Integer category_id,
                           @RequestParam("overview") String overview) throws IOException {



        //Hostinger Ftp variable
        String server = "51.255.126.128";
        int port = 21;
        String user = "u421594952";
        String pass = "Aymenchikebftp2022";
        FTPClient ftpClient = new FTPClient();



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

            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);


            InputStream inputStream = new ByteArrayInputStream(file.getBytes());

            System.out.println("Start uploading first file");
            boolean done = ftpClient.storeFile("oussama_images/"+nb+file.getOriginalFilename(), inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The first file is uploaded successfully.");
            }

            image.append("oussama_images/").append(nb).append(file.getOriginalFilename()).append(",");
        }

        product.setImages(String.valueOf(image));

        productRepository.save(product);

        System.out.println(product);

    }

    @GetMapping("/test/test")
    public void uploadFileInFtp(){
        String server = "51.255.126.128";
        int port = 21;
        String user = "u421594952";
        String pass = "Aymenchikebftp2022";
        FTPClient ftpClient = new FTPClient();
        try {

            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.changeWorkingDirectory("/assets");
            //ftpClient.set

            // APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = new File("src/main/resources/static/images/face1.jpg");

            String firstRemoteFile = "face1.jpg";
            InputStream inputStream = new FileInputStream(firstLocalFile);

            System.out.println("Start uploading first file");
            boolean done = ftpClient.storeFile("oussama_images/"+firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The first file is uploaded successfully.");
            }


        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @PostMapping("/delete/{id}")
    @Transactional
    public void deleteProduct(@PathVariable int id){
        productRepository.delete(productRepository.findById((long) id).get());
        basketRepository.deleteByProductId(id);
        System.out.println("product deleted successfully");
    }


    @PostMapping("/update/{id}")
    public void updateProduct(@PathVariable int id,
                              @RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("price") Integer price,
                              @RequestParam("total") Integer total,
                              @RequestParam("category_id") Integer category_id,
                              @RequestParam("overview") String overview){
        Product product = productRepository.findProductById((long) id);
        product.setTotal(total);
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setCategory(categoryRepository.findById(Long.valueOf(category_id)).get());
        product.setOverview(overview);

        productRepository.save(product);

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
