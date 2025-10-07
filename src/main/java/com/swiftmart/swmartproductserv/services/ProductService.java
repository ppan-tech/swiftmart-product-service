package com.swiftmart.swmartproductserv.services;

import com.swiftmart.swmartproductserv.exceptions.ProductNotFoundException;
import com.swiftmart.swmartproductserv.models.Product;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product createProduct(String name, String description, double price, String imageUrl, String category);
    Product updateProduct(long id, String name, String description, double price, String imageUrl, String category);
    void deleteProduct(Long id);
    Product applyPatchToProduct(long id, JsonPatch patch) throws JsonPatchException, JsonProcessingException;
}
