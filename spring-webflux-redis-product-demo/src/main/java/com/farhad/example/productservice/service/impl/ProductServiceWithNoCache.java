package com.farhad.example.productservice.service.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.farhad.example.productservice.dto.ProductDto;
import com.farhad.example.productservice.mapper.ProductMapper;
import com.farhad.example.productservice.repository.ProductRepository;
import com.farhad.example.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
@ConditionalOnProperty(name = "cache.enabled", havingValue = "false")
@RequiredArgsConstructor
public class ProductServiceWithNoCache implements ProductService {

    private final ProductRepository productRepository;
    
    @Override
    public Mono<ProductDto> getProductById(Integer id) {
        return productRepository
                        .findById(id)
                        .map(ProductMapper::toDto);
    }

    @Override
    public Mono<Void> updateProductById(Integer id, Mono<ProductDto> MonoOfproductdto) {
        return this.productRepository.findById(id)
                        .zipWith(MonoOfproductdto)
                        .doOnNext(tuple -> tuple.getT1().setQtyAvailable(tuple.getT2().getQuantityAvailable()))
                        .map(Tuple2::getT1)
                        .flatMap(this.productRepository::save)
                        .then();
    }

    @Override
    public Mono<ProductDto> create(ProductDto productdto) {
         return Mono
                .just(productdto)
                .map(ProductMapper::toEntity)
                .flatMap(p -> this.productRepository.save(p))
                .map(ProductMapper::toDto);
                // .map()
    }
    
    
}
