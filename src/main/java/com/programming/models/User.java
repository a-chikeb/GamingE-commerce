package com.programming.models;


import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;


    @Nullable
    @OneToMany(targetEntity=Basket.class, mappedBy="user",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Basket> basket;


    @Nullable
    @OneToMany(targetEntity=PcBuilder.class, mappedBy="user",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PcBuilder> pcBuilders;


    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Nullable
    public Set<Basket> getBasket() {
        return basket;
    }

    public void setBasket(@Nullable Set<Basket> basket) {
        this.basket = basket;
    }

    @Nullable
    public Set<PcBuilder> getPcBuilders() {
        return pcBuilders;
    }

    public void setPcBuilders(@Nullable Set<PcBuilder> pcBuilders) {
        this.pcBuilders = pcBuilders;
    }
}
