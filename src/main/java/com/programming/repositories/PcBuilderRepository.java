package com.programming.repositories;

import com.programming.models.Basket;
import com.programming.models.PcBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PcBuilderRepository extends JpaRepository<PcBuilder,Long> {
    List<PcBuilder> findByUserId(Integer id);
    PcBuilder findById(Integer id);
}

