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
    ProductResponseDto getSingleProduct(@PathVariable("id") Long id){
        Product product = productService.getProductById(id);
        return ProductResponseDto.from(product);
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

    //This idempotent method will either create a new product or update an existing product of with that id exists.
    //If the product with the given id does not exist, it will create a new product
    //with the given id and details from the request body.
    //If the product with the given id exists, it will update the product
    //with the details from the request body.
    //In both cases, it will return the created or updated product details.
    //Note : Here it is the client's responsibility to ensure that the id is unique.Unlike POST, which generates a new id for each request.
    @PutMapping("/products/{id}")
    public ProductResponseDto replaceProduct(@PathVariable("id") long id,
                                             @RequestBody ClientReqFakeStoreProductRequestDto
                                                     clientReqFakeStoreProductRequestDto)
    {
        Product product = productService.updateProduct(
                id,
                clientReqFakeStoreProductRequestDto.getName(),
                clientReqFakeStoreProductRequestDto.getDescription(),
                clientReqFakeStoreProductRequestDto.getPrice(),
                clientReqFakeStoreProductRequestDto.getImageUrl(),
                clientReqFakeStoreProductRequestDto.getCategory()
        );

        return ProductResponseDto.from(product);
    }
}
