package com.farhad.example.productservice.boot;

import java.time.Duration;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;

import com.farhad.example.productservice.domain.Product;
import com.farhad.example.productservice.dto.ProductDto;
import com.farhad.example.productservice.mapper.ProductMapper;
import com.farhad.example.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductLoader {
    
    private final ReactiveRedisConnectionFactory factory ;
    private final ReactiveRedisOperations<Integer,ProductDto> productOps;
    private final ProductRepository productRepository;

    @PostConstruct
    public void loadData(){
        productRepository.saveAll(
                            Arrays.asList(
                                new Product(null, "Product998", 6123, 7514),
                                new Product(null, "Product999", 1080, 3254),
                                new Product(null, "Product1000", 4139, 5677)))
                        .blockLast(Duration.ofSeconds(10));

        log.info("fetch all products!");
        log.info("--------------------");
        productRepository.findAll()
                               .map(ProductMapper::toDto) 
                               .doOnNext(dto -> log.info("{}", dto)) 
                               .blockLast(Duration.ofSeconds(100));

    }
}
