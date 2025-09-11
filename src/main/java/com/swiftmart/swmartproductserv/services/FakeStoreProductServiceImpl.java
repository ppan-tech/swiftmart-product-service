package com.swiftmart.swmartproductserv.services;

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
        return List.of();
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
