package com.programming.models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "color")
public class Color implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "colorName")
    private String color_name;

    @Nullable
    @Column(name = "colorImage")
    private String color_image;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;


    public Color() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor_name() {
        return color_name;
    }

    public void setColor_name(String color_name) {
        this.color_name = color_name;
    }

    @Nullable
    public String getColor_image() {
        return color_image;
    }

    public void setColor_image(@Nullable String color_image) {
        this.color_image = color_image;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Color{" +
                "id=" + id +
                ", color_name='" + color_name + '\'' +
                ", color_image='" + color_image + '\'' +
                '}';
    }
}
