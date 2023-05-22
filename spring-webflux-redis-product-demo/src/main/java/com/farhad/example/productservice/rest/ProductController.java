package com.farhad.example.productservice.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farhad.example.productservice.dto.ProductDto;
import com.farhad.example.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;

    @PostMapping("/products")
    public Mono<ProductDto> create(@RequestBody ProductDto productDto) {
        return this.productService.create(productDto);
    }

    @GetMapping("/products/{id}")
    public Mono<ProductDto> byId(@PathVariable("id") Integer id) {
        return this.productService.getProductById(id);
    }

    @PutMapping("/products/{id}")
    public Mono<Void> updateById(@PathVariable("id") Integer id, @RequestBody Mono<ProductDto> mono) {
        return this.productService.updateProductById(id, mono);
    }
}
