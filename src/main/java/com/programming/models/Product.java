package com.programming.models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    //Price Option
    @Nullable
    @OneToMany(targetEntity=PriceOption.class, mappedBy="product",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PriceOption> price_options;

    private String images;

    @Column(name = "brandImages")
    private String brand_images;

    //Colors
    //test CascadeType.PERSIST
    @Nullable
    @OneToMany(targetEntity=Color.class, mappedBy="product",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Color> colors = new HashSet<>();

    private int total;

    private String overview;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    public Product() {
    }

    public Product(String name, String description, String images, String brand_images, int total, String overview) {
        this.name = name;
        this.description = description;
        this.images = images;
        this.brand_images = brand_images;
        this.total = total;
        this.overview = overview;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getBrand_images() {
        return brand_images;
    }

    public void setBrand_images(String brand_images) {
        this.brand_images = brand_images;
    }

    @Nullable
    public Set<Color> getColors() {
        return colors;
    }

    public void setColors(@Nullable Set<Color> colors) {
        this.colors = colors;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }


    @Nullable
    public Set<PriceOption> getPrice_options() {
        return price_options;
    }

    public void setPrice_options(@Nullable Set<PriceOption> price_options) {
        this.price_options = price_options;
    }


    public String getCategory() {
        return category.getName();
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price_options=" + price_options +
                ", images='" + images + '\'' +
                ", brand_images='" + brand_images + '\'' +
                ", colors=" + colors +
                ", total=" + total +
                ", overview='" + overview + '\'' +
                ", category=" + category +
                '}';
    }
}
