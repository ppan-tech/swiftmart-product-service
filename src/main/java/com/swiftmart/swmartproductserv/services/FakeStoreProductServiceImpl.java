package com.swiftmart.swmartproductserv.services;

import com.swiftmart.swmartproductserv.dtos.FakeStoreRequestDto;
import com.swiftmart.swmartproductserv.dtos.FakeStoreResponseDto;
import com.swiftmart.swmartproductserv.exceptions.OperationException;
import com.swiftmart.swmartproductserv.exceptions.ProductNotFoundException;
import com.swiftmart.swmartproductserv.models.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

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

    //Will use here RestTemplate.exchange() method to send PUT request as its better as there could be many update combinations and can
    //not have so many DTOs, which would be one for each combination.
    //ref:https://www.baeldung.com/spring-rest-template-put
    @Override
    public Product updateProduct(long id, String name, String description, double price, String imageUrl, String category) {
        FakeStoreRequestDto updatedFakeStoreRequestDto = createDtoFromParams(name, description, price, imageUrl, category);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<FakeStoreRequestDto> requestEntity =
                new HttpEntity<>(updatedFakeStoreRequestDto, headers);

        ResponseEntity<FakeStoreResponseDto> responseEntity = restTemplate.exchange(
                baseUrl+"/products/" + id,
                HttpMethod.PUT,
                requestEntity,
                FakeStoreResponseDto.class
        );

        return responseEntity.getBody().toProduct();
    }

    @Override
    public void deleteProduct(Long id) {
        try {
            restTemplate.delete(baseUrl + "/products/" + id);
        } catch (Exception e) {
            throw new OperationException("Delete Operation failed for product-id: " + id + " with error"+e.getMessage());
        }
    }

    @Override
    public Product applyPatchToProduct(long id, JsonPatch patch)
            throws JsonPatchException,
            JsonProcessingException {

        // Get existing product
        Product existingProduct = getProductById(id);

        // Convert Product to JSON Format
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode productNode = objectMapper.valueToTree(existingProduct);

        // Apply Patch
        JsonNode patchedNode = patch.apply(productNode);

        //Convert back to Product
        Product patchedProduct = objectMapper.treeToValue(patchedNode, Product.class);

        return updateProduct(id,
                patchedProduct.getName(),
                patchedProduct.getDescription(),
                patchedProduct.getPrice(),
                patchedProduct.getCategory().getName(),
                patchedProduct.getImageUrl()
        );
    }

}
