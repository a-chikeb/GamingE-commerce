package com.programming.services;

import com.programming.models.Error;
import com.programming.models.User;
import com.programming.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



import java.util.Collections;

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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userEmail = userRepository.findByEmail(email);

        User user = userRepository.findByUsername(userEmail.getUsername());

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), Collections.emptyList());
    }


    public User registerUser(User user){
        User userEmail = userRepository.findByEmail(user.getEmail());
        if(userEmail==null){
            User newUser = new User();
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setEmail(user.getEmail());
            newUser.setUsername(user.getUsername());
            newUser.setPassword(userPasswordEncoder.encode(user.getPassword()));
            userRepository.save(newUser);
            return newUser;
        }else{
            return null;
        }

    }

    public User login(String email, String password){

        UserDetails userDetails = loadUserByUsername(email);

        if(userPasswordEncoder.matches(password,userDetails.getPassword())){
            return userRepository.findByEmail(email);
        }else{
            return null;
        }


    }


}
