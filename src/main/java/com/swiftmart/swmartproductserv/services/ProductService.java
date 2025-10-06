package com.swiftmart.swmartproductserv.services;

import com.swiftmart.swmartproductserv.models.Product;
import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product createProduct(String name, String description, double price, String imageUrl, String category);
    Product updateProduct(long id, String name, String description, double price, String imageUrl, String category);
    void deleteProduct(Long id);

}
