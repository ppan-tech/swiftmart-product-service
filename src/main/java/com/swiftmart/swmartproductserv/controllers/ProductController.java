package com.swiftmart.swmartproductserv.controllers;

import com.swiftmart.swmartproductserv.services.ProductService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

}
