package com.programming;

import com.programming.repositories.ColorRepository;
import com.programming.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GamingEcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamingEcommerceApplication.class, args);
    }


}
