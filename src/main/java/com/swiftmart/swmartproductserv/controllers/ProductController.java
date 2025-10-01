package com.swiftmart.swmartproductserv.controllers;

import com.swiftmart.swmartproductserv.dtos.ProductResponseDto;
import com.swiftmart.swmartproductserv.models.Product;
import com.swiftmart.swmartproductserv.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductResponseDto> getAllProducts()
    {
        List<Product> products = productService.getAllProducts();

        List<ProductResponseDto> productResponseDtos =
                products.stream().map(ProductResponseDto::from)
                        .collect(Collectors.toList());

        return productResponseDtos;
    }

}
