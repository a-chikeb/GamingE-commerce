package com.programming.services;

import com.programming.models.User;
import com.programming.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;


    private UserPasswordEncoder userPasswordEncoder;


    @Autowired
    public UserService(UserRepository userRepository,@Lazy UserPasswordEncoder userPasswordEncoder) {
        this.userRepository = userRepository;
        this.userPasswordEncoder = userPasswordEncoder;
    }

    public User findById(int id){
        return userRepository.findById(id).orElseThrow(()-> new NullPointerException("User not found"));
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), Collections.emptyList());
    }


    public void registerUser(User user){
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(userPasswordEncoder.encode(user.getPassword()));

        userRepository.save(newUser);
    }

    public User login(String username, String password){
        UserDetails userDetails = loadUserByUsername(username);

        if(userPasswordEncoder.matches(password,userDetails.getPassword())){
            return userRepository.findByUsername(username);
        }else{
            return null;
        }


    }


}
