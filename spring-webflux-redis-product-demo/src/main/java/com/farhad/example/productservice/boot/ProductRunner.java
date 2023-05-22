package com.farhad.example.productservice.boot;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.farhad.example.productservice.domain.Product;
import com.farhad.example.productservice.mapper.ProductMapper;
import com.farhad.example.productservice.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Configuration
@Slf4j
public class ProductRunner {
    
    @Bean
    public ApplicationRunner demo(ProductRepository productRepository) {

        return args -> {

            // save a few products
            List<Integer> savedProductIds =
                        productRepository.saveAll(
                                            Arrays.asList(
                                                new Product(null, "Product994",2201,5411),
                                                new Product(null, "Product995",8654,160),
                                                new Product(null, "Product996",2633,9922),
                                                new Product(null, "Product997",5220,3223)))
                                            .map(ProductMapper::toDto)
                                            .map(dto -> dto.getId())
                                            .collectList()
                                            .block(Duration.ofSeconds(20));
            // fetch a product
            Flux.fromIterable(savedProductIds)
                    .flatMap(id -> productRepository
                                            .findById(id))
                    .map(ProductMapper::toDto)
                    .doOnNext(dto -> log.info("Product fetched: {}", dto) )
                    .blockLast(Duration.ofSeconds(8));
                
                
            // productRepository.findById(null)
        };
    }
}
