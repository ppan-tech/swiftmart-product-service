package com.swiftmart.swmartproductserv.services;

import com.swiftmart.swmartproductserv.models.Category;
import com.swiftmart.swmartproductserv.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import java.util.ArrayList;
import java.util.List;

@Service("fakestoreproductservice")
public class FakeStoreProductServiceImpl implements ProductService {

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        Product p1 = new Product(1L, "Product 1", "Description 1", 10.0, new Category(1L,"Category 1"), "image1.jpg");
        Product p2 = new Product(2L, "Product 2", "Description 2", 20.0, new Category(2L,"Category 2"), "image2.jpg");
        Product p3 = new Product(3L, "Product 3", "Description 3", 30.0, new Category(3L,"Category 3"), "image3.jpg");
        //return null;
        return List.of(p1,p2,p3);
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
