package com.programming.controllers;

import com.programming.models.Basket;
import com.programming.models.PcBuilder;
import com.programming.models.User;
import com.programming.repositories.BasketRepository;
import com.programming.repositories.PcBuilderRepository;
import com.programming.repositories.UserRepository;
import com.programming.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/card")
public class CardController {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PcBuilderRepository pcBuilderRepository;

    @Autowired
    private UserService userService;


    @PostMapping("/add")
    public void addCard(@RequestBody Map<String,String> data){
        Basket basket = new Basket();
        basket.setPrice( Integer.parseInt(data.get("price")) );
        basket.setQuantity( Integer.parseInt(data.get("quantity")) );
        basket.setProductId( Integer.parseInt(data.get("product")) );
        basket.setUser(userRepository.findByUsername(data.get("username")));
        basketRepository.save(basket);
        //System.out.println("add to card work");
    }


    @DeleteMapping
    public void removeProduct(@RequestBody Map<String,Integer> data){
        List<Basket> basketList =  basketRepository.findByUserId(data.get("user_id"));
        for (Basket b:basketList) {

            Long basket_id = Long.parseLong(String.valueOf(data.get("basket_id")));

            System.out.println(basket_id);
            if(b.getProductId()==data.get("product_id") && b.getId().equals(basket_id)){
                basketRepository.delete(b);
            }
        }
        //System.out.println("delete successfully");
    }


    //Add Pc Builder
    @PostMapping("/pcBuilder/add")
    public void addPcBuilder(@RequestBody Map<String,Integer> data){
        System.out.println("-----------");
        PcBuilder pcBuilder = new PcBuilder();
        pcBuilder.setCpu(data.get("cpu"));
        pcBuilder.setFan(data.get("fan"));
        pcBuilder.setHardDrive(data.get("disqueDur"));
        pcBuilder.setSsd(data.get("disqueSsd"));
        pcBuilder.setMotherBoard(data.get("carteMere"));
        pcBuilder.setMemory(data.get("ram"));
        pcBuilder.setCases(data.get("boitier"));
        pcBuilder.setKeyword(data.get("clavier"));
        pcBuilder.setKeywordPack(data.get("clavierPack"));
        pcBuilder.setMouse(data.get("souris"));
        pcBuilder.setHeadsetAndMicrophone(data.get("casque"));
        pcBuilder.setMonitor(data.get("monitor"));
        pcBuilder.setMousePad(data.get("tapisSouris"));
        //pcBuilder.setCaseFan(data.get("ventilateurBoitier"));
        //not yet
        //pcBuilder.setNetworkCard(null);
        //pcBuilder.setOperatingSystem(null);
        //pcBuilder.setOpticalDrive(null);
        //pcBuilder.setSataCable(null);
        //pcBuilder.setAssembly(null);
        //pcBuilder.setSoundCard(null);
        //pcBuilder.setPrinter(null);
        //pcBuilder.setSpeakers(data.get("micro"));
        pcBuilder.setWebcam(data.get("webcam"));
        //pcBuilder.setUsbFlashDrive(data.get("usb"));
        pcBuilder.setPowerSupply(data.get("alimentation"));
        pcBuilder.setGraphicsCard(data.get("carteGraphics"));


        //Optional<User> user = userRepository.findById(data.get("user"));
        //pcBuilder.setUser(userRepository.findByUsername(user.get().getUsername()));

        pcBuilder.setUser(userService.findById(data.get("user")));
        pcBuilder.setDate(new Date());
        pcBuilderRepository.save(pcBuilder);

        System.out.println(data);

    }

    @GetMapping("/pcBuilder/{user}")
    public List<PcBuilder> getPcBuilder(@PathVariable("user") Integer userId){
        return pcBuilderRepository.findByUserId(userId);
    }

    @DeleteMapping("/pcBuilder/delete/{id}")
    public void removePcBuilder(@PathVariable("id") Long id){

        //PcBuilder pcBuilder = pcBuilderRepository.findById(id);

        pcBuilderRepository.deleteById(id);
    }

}
