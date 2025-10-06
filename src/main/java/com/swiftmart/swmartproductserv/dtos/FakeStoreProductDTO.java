package com.swiftmart.swmartproductserv.dtos;

import com.swiftmart.swmartproductserv.models.Category;
import com.swiftmart.swmartproductserv.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String category;
    private String image;


    public Product toProduct(){
        Product product = new Product();
        product.setId(this.id);
        product.setName(this.title);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setImageUrl(this.image);

        Category category = new Category();
        category.setName(this.category);

        product.setCategory(category);

        return product;
    }
}