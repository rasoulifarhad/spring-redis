package com.farhad.example.productservice.mapper;

import com.farhad.example.productservice.domain.Product;
import com.farhad.example.productservice.dto.ProductDto;

public class ProductMapper {
    
    public static ProductDto toDto(Product product) {
        return ProductDto.builder()
                            .id(product.getId())
                            .description(product.getDescription())
                            .price(product.getPrice())
                            .quantityAvailable(product.getQtyAvailable())
                        .build();
    }

    public static Product toEntity(ProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQtyAvailable(dto.getQuantityAvailable());
        return product;
    }
}
