package com.programming.repositories;

import com.programming.models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findProductByCategory_Name(String category, Pageable pageable);
    List<Product> findProductByCategory_Name(String category);

    List<Product> findProductByCategory_ParentCategoryOrderByPriceDesc(String name,Pageable pageable);
    List<Product> findProductByCategory_ParentCategory(String category);


    //Search and Filter Function
    List<Product> findProductByNameContaining(String name, Pageable pageable);
    List<Product> findProductByNameContainingOrderByPriceDesc(String name,Pageable pageable);
    List<Product> findProductByNameContainingOrderByPriceAsc(String name,Pageable pageable);
    List<Product> findProductByNameContainingOrderByNameAsc(String name,Pageable pageable);

    List<Product> findProductByNameContaining(String name);

    Product findProductById(Long id);
}
