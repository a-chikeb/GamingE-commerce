package com.programming.controllers;

import com.programming.models.*;
import com.programming.repositories.BasketRepository;
import com.programming.repositories.PcBuilderRepository;
import com.programming.repositories.ProductRepository;
import com.programming.services.UserPasswordEncoder;
import com.programming.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PcBuilderRepository pcBuilderRepository;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public void registerUer(@RequestBody User user){
        userService.registerUser(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody Map<String,Object> data){

        return userService.login(data.get("username").toString(),data.get("password").toString());

    }

    @GetMapping("/cart/{id}")
    public List<Item> getCartItems(@PathVariable("id") int id){

        List<Item> items = new ArrayList<>();
        for (Basket b:basketRepository.findByUserId(id)) {
            Product productName = productRepository.findProductById((long) b.getProductId());
            int subTotal = Integer.parseInt(b.getPrice())*b.getQuantity();
            Item item = new Item(productName.getName(),
                    b.getColor(),
                    productName.getImages(),
                    b.getPrice(),
                    String.valueOf(subTotal),
                    b.getQuantity());

            items.add(item);
        }
        return items;

    }

    @PostMapping("/pc_builder/add/{id}")
    public void addPcBuilder(@RequestBody PcBuilder pcBuilder,@PathVariable("id") int id){
        User user = userService.findById(id);
        pcBuilder.setDate(new Date());
        pcBuilder.setUser(user);
        pcBuilderRepository.save(pcBuilder);
    }


}
