package com.swiftmart.swmartproductserv.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientReqFakeStoreProductRequestDto
{
    private String name;
    private double price;
    private String description;
    private String imageUrl;
    private String category;
}