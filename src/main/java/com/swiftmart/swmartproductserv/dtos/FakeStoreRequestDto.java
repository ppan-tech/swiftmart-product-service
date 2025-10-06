package com.swiftmart.swmartproductserv.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//This is as per the fields received from fakestoreapi.com.
public class FakeStoreRequestDto
{
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
}