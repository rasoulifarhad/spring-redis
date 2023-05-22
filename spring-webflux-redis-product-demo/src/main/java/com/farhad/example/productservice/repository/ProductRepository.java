package com.farhad.example.productservice.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.farhad.example.productservice.domain.Product;

import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product,Integer> {

    @Query("SELECT * FROM products WHERE description = :description")
    Flux<Product> findByDescription(String deacription);

    @Query("SELECT * FROM products WHERE price = :price")
    Flux<Product> findByPrice(Integer price);
    
}
