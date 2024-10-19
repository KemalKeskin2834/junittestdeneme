package com.kemalkeskin.junittestdeneme.service.abstracts;

import com.kemalkeskin.junittestdeneme.model.Product;
import com.kemalkeskin.junittestdeneme.service.dtoS.CreateProductRequest;
import com.kemalkeskin.junittestdeneme.service.dtoS.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse>listProduct();

    ProductResponse getById(int id);

    void add(CreateProductRequest createProductRequest);

    void update(int id,CreateProductRequest createProductRequest);

    void delete(int id);

    List<ProductResponse>listPriceBetween(double minPrice,double maxPrice);

    List<ProductResponse> findAllOrderByPriceAsc();

    List<ProductResponse> findAllOrderByPriceDesc();
}
