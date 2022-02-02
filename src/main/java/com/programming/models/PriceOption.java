package com.programming.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PriceOption")
public class PriceOption implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "optionName")
    private String option_name;

    @Column(name = "price")
    private double price;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public PriceOption() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOption_name() {
        return option_name;
    }

    public void setOption_name(String option_name) {
        this.option_name = option_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "PriceOption{" +
                "id=" + id +
                ", option_name='" + option_name + '\'' +
                ", price=" + price +
                '}';
    }
}
