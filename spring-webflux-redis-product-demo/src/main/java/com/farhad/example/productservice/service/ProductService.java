package com.farhad.example.productservice.service;

import com.farhad.example.productservice.dto.ProductDto;

import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<ProductDto> getProductById(Integer id) ;

    Mono<ProductDto> create(ProductDto productdto);
    Mono<Void> updateProductById(Integer id, Mono<ProductDto> productdto);
}
