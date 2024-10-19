package com.kemalkeskin.junittestdeneme.service.dtoS;

import com.kemalkeskin.junittestdeneme.model.Category;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductResponse {

    private int id;


    private String name;


    private double price;


    private int stockAmount;


    private String categoryName;
}
