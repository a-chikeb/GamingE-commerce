package com.programming.repositories;

import com.programming.models.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket,Long> {
    List<Basket> findByUserId(Integer id);
    Basket findById(Integer id);

    void deleteByProductId(int id);
}

