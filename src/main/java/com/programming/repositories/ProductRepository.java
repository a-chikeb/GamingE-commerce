package com.programming.repositories;

import com.programming.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findProductByCategory_Name(String category);
    List<Product> findProductByNameContaining(String name);
    Product findProductById(Long id);
}
