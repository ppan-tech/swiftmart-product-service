package com.swiftmart.swmartproductserv.services;

import com.swiftmart.swmartproductserv.dtos.FakeStoreRequestDto;
import com.swiftmart.swmartproductserv.dtos.FakeStoreResponseDto;
import com.swiftmart.swmartproductserv.exceptions.ProductNotFoundException;
import com.swiftmart.swmartproductserv.models.Category;
import com.swiftmart.swmartproductserv.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import java.util.ArrayList;
import java.util.List;

@Service("fakestoreproductservice")
public class FakeStoreProductServiceImpl implements ProductService {

    private final String baseUrl;
    private final RestTemplate restTemplate ;

    public FakeStoreProductServiceImpl(RestTemplate restTemplate,
                                       @Value("${external.fakestore-service.base-url}") String baseUrl){
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @Override
    public Product getProductById(Long id) {
        FakeStoreResponseDto response = restTemplate.getForObject(
                baseUrl + "/products/" + id,
                FakeStoreResponseDto.class);

        if(response == null){
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }

        return response.toProduct();
    }

    //Initial Testing purpose:
    /*@Override
    public List<Product> getAllProducts() {
        Product p1 = new Product(1L, "Product 1", "Description 1", 10.0, new Category(1L,"Category 1"), "image1.jpg");
        Product p2 = new Product(2L, "Product 2", "Description 2", 20.0, new Category(2L,"Category 2"), "image2.jpg");
        Product p3 = new Product(3L, "Product 3", "Description 3", 30.0, new Category(3L,"Category 3"), "image3.jpg");
        //return null;
        return List.of(p1,p2,p3);
    }*/

    @Override
    public List<Product> getAllProducts() {
        //https://fakestoreapi.com/products

        FakeStoreResponseDto[] fakeStoreProductDTOS = restTemplate.getForObject(
                baseUrl + "/products",
                FakeStoreResponseDto[].class);

        List<Product> products = new ArrayList<>();

        for(FakeStoreResponseDto fakeStoreProductDTO: fakeStoreProductDTOS){
            products.add(fakeStoreProductDTO.toProduct());
        }
        return products;
    }

    @Override
    public Product createProduct(String name, String description, double price, String imageUrl, String category)
    {
        FakeStoreRequestDto fakeStoreRequestDto = createDtoFromParams(name, description, price, imageUrl, category);

        FakeStoreResponseDto fakeStoreResponseDto = restTemplate.postForObject(
                baseUrl + "/products",
                fakeStoreRequestDto,
                FakeStoreResponseDto.class);

        return fakeStoreResponseDto.toProduct();
    }

    private FakeStoreRequestDto createDtoFromParams(String name, String description, double price, String imageUrl, String category)
    {
        FakeStoreRequestDto fakeStoreRequestDto = new FakeStoreRequestDto();
        fakeStoreRequestDto.setTitle(name);
        fakeStoreRequestDto.setDescription(description);
        fakeStoreRequestDto.setPrice(price);
        fakeStoreRequestDto.setImage(imageUrl);
        fakeStoreRequestDto.setCategory(category);
        return fakeStoreRequestDto;
    }

    @Override
    public Product updateProduct(long id, String name, String description, double price, String imageUrl, String category) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
