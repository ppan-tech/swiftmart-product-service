package com.swiftmart.swmartproductserv.dtos;

import com.swiftmart.swmartproductserv.models.Product;
import com.swiftmart.swmartproductserv.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreResponseDto
{
    private long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;

    public Product toProduct()
    {
        Product product = new Product();
        product.setId(id);
        product.setName(title);
        product.setDescription(description);
        product.setImageUrl(image);
        product.setPrice(price);

        Category category = new Category();
        category.setName(title);

        product.setCategory(category);

        return product;
    }
}