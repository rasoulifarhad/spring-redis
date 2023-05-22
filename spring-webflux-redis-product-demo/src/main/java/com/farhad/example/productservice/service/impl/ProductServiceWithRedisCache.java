package com.farhad.example.productservice.service.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.stereotype.Service;

import com.farhad.example.productservice.dto.ProductDto;
import com.farhad.example.productservice.repository.ProductRepository;

import reactor.core.publisher.Mono;

@Service
@ConditionalOnProperty(name = "cache.enabled", havingValue = "true")
public class ProductServiceWithRedisCache extends ProductServiceWithNoCache {

    private static final String KEY = "products";

    private final ReactiveHashOperations<String,Integer,ProductDto> hashOperations;

    // public ProductServiceWithRedisCache(ProductRepository productRepository) {
    //     super(productRepository);
    // }

    public ProductServiceWithRedisCache(ProductRepository productRepository,
                                        ReactiveHashOperations<String,Integer,ProductDto> hashOperations) {
            super(productRepository);
            this.hashOperations = hashOperations ;
    }

    @Override
    public Mono<ProductDto> getProductById(Integer id) {
        return this.hashOperations
                            .get(KEY, id)
                            .switchIfEmpty(super.getProductById(id))
                            .flatMap(dto -> 
                                    this.hashOperations
                                                .put(KEY, id, dto)
                                                .thenReturn(dto));
    }

    @Override
    public Mono<Void> updateProductById(Integer id, Mono<ProductDto> MonoOfproductdto) {
        return super.updateProductById(id, MonoOfproductdto)
                            .then(this.hashOperations.remove(KEY, id))
                            .then();
    }

    

    
}
