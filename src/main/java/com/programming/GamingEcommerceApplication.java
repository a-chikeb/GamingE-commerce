package com.programming;

import com.programming.models.Category;
import com.programming.models.Product;
import com.programming.repositories.CategoryRepository;
import com.programming.repositories.ColorRepository;
import com.programming.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@SpringBootApplication
public class GamingEcommerceApplication{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(GamingEcommerceApplication.class, args);
    }

    /*@Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("https://pcmultigaming.com/","http://pcmultigaming.com/","https://pcmultigaming.com","http://pcmultigaming.com"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
     */
    @Bean
    public CorsFilter corsFilter() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        //config.setAllowCredentials(true); // you USUALLY want this
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }


    /*
    // implements CommandLineRunner
    @Override
    public void run(String... args) throws Exception {

        Category category = categoryRepository.findByName("Disque Dur HDD");

        productRepository.save(new Product(
                "1TB HDD WD GREEN",
                "",
                "./assets/amazon/hdd/2_1.jpg,./assets/amazon/hdd/2_2.jpg,./assets/amazon/hdd/2_3.jpg,./assets/amazon/hdd/2_4.jpg,./assets/amazon/hdd/2_5.jpg",
                "",
                2,
                "<table class=\"a-normal a-spacing-micro\">  <tbody><tr class=\"a-spacing-small po-digital_storage_capacity\"> <td class=\"a-span3\"> <span class=\"a-size-base a-text-bold\">Capacité du stockage numérique</span> </td> <td class=\"a-span9\">    <span class=\"a-size-base\">1000 Go</span>   </td> </tr>  <tr class=\"a-spacing-small po-compatible_devices\"> <td class=\"a-span3\"> <span class=\"a-size-base a-text-bold\">Compatibilité du périphérique</span> </td> <td class=\"a-span9\">    <span class=\"a-size-base\">Ordinateur</span>   </td> </tr>  <tr class=\"a-spacing-small po-brand\"> <td class=\"a-span3\"> <span class=\"a-size-base a-text-bold\">Marque</span> </td> <td class=\"a-span9\">    <span class=\"a-size-base\">Western Digital</span>   </td> </tr>  <tr class=\"a-spacing-small po-model_name\"> <td class=\"a-span3\"> <span class=\"a-size-base a-text-bold\">séries</span> </td> <td class=\"a-span9\">    <span class=\"a-size-base\">WD Green</span>   </td> </tr>  <tr class=\"a-spacing-small po-connectivity_technology\"> <td class=\"a-span3\"> <span class=\"a-size-base a-text-bold\">\tType de connecteur\t</span> </td> <td class=\"a-span9\">    <span class=\"a-size-base\">SATA</span>   </td> </tr>  <tr class=\"a-spacing-small po-hard_disk_form_factor\"> <td class=\"a-span3\"> <span class=\"a-size-base a-text-bold\">Facteur de forme du disque dur</span> </td> <td class=\"a-span9\">    <span class=\"a-size-base\">3.5 Pouces</span>   </td> </tr>  <tr class=\"a-spacing-small po-hard_disk.size\"> <td class=\"a-span3\"> <span class=\"a-size-base a-text-bold\">Taille du disque dur</span> </td> <td class=\"a-span9\">    <span class=\"a-size-base\">1000 Go</span>   </td> </tr>  <tr class=\"a-spacing-small po-form_factor\"> <td class=\"a-span3\"> <span class=\"a-size-base a-text-bold\">Facteur de forme</span> </td> <td class=\"a-span9\">    <span class=\"a-size-base\">3.5</span>   </td> </tr>  <tr class=\"a-spacing-small po-cache_memory.installed_size\"> <td class=\"a-span3\"> <span class=\"a-size-base a-text-bold\">Taille du cache</span> </td> <td class=\"a-span9\">    <span class=\"a-size-base\">64 Modificateur inconnu</span>   </td> </tr>  <tr class=\"a-spacing-small po-item_weight\"> <td class=\"a-span3\"> <span class=\"a-size-base a-text-bold\">Poids du produit</span> </td> <td class=\"a-span9\">    <span class=\"a-size-base\">1.61 Livres</span>   </td> </tr>  </tbody></table>",
                390,
                category));

    }

     */
}
