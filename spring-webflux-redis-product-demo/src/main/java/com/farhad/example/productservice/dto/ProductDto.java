package com.farhad.example.productservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private Integer id ;
    private String description;
    private Integer price;
    private Integer quantityAvailable;

}
