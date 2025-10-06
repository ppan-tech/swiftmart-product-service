package com.swiftmart.swmartproductserv.controllers;

import com.swiftmart.swmartproductserv.dtos.ClientReqFakeStoreProductRequestDto;
import com.swiftmart.swmartproductserv.dtos.ProductResponseDto;
import com.swiftmart.swmartproductserv.models.Product;
import com.swiftmart.swmartproductserv.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/swiftmart-products/v1")
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
                products.stream().filter(product -> product.getId() != null)
                        .map(ProductResponseDto::from)
                        .collect(Collectors.toList());

        return productResponseDtos;
    }

    @GetMapping("/products/{id}")
    Product getSingleProduct(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResponseDto> createProduct(
            @RequestBody ClientReqFakeStoreProductRequestDto
                    clientReqFakeStoreProductRequestDto)
    {
        Product product = productService.createProduct(
                clientReqFakeStoreProductRequestDto.getName(),
                clientReqFakeStoreProductRequestDto.getDescription(),
                clientReqFakeStoreProductRequestDto.getPrice(),
                clientReqFakeStoreProductRequestDto.getImageUrl(),
                clientReqFakeStoreProductRequestDto.getCategory()
        );
        return new ResponseEntity<>(ProductResponseDto.from(product), HttpStatus.CREATED);
    }

}
