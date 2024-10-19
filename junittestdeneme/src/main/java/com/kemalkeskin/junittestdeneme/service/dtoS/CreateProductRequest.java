package com.kemalkeskin.junittestdeneme.service.dtoS;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {


    @Size(min = 3,message = "The product name must be at least 3 characters long.")
    private String name;


    @Min(value = 10,message = "The product price must be at least 10")
    private double price;

    @Min(value = 1,message = "The product stock quantity must be at least 1")
    private int stockAmount;


    private int categoryId;
}
