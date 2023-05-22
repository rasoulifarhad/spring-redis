package com.farhad.example.productservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    
    @Id
    private Integer id ;
    private String description;
    private Integer price;
    private Integer qtyAvailable;
}
