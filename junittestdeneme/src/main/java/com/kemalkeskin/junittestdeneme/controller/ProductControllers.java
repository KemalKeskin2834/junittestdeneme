package com.kemalkeskin.junittestdeneme.controller;

import com.kemalkeskin.junittestdeneme.model.Product;
import com.kemalkeskin.junittestdeneme.service.abstracts.ProductService;
import com.kemalkeskin.junittestdeneme.service.dtoS.CreateProductRequest;
import com.kemalkeskin.junittestdeneme.service.dtoS.ProductResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductControllers {

    @Autowired
    private ProductService productService;


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> listProduct() {
        return productService.listProduct();
    }

    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getById(@PathVariable int id) {
       return  productService.getById(id);
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@Valid @RequestBody CreateProductRequest createProductRequest) {
        this.productService.add(createProductRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @PathVariable int id, @RequestBody CreateProductRequest createProductRequest) {
        this.productService.update(id,createProductRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        this.productService.delete(id);
    }

    @GetMapping("/by-price-between")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> listPriceBetween(@RequestParam double minPrice, @RequestParam double maxPrice){
        return productService.listPriceBetween(minPrice,maxPrice);

    }

    @GetMapping("/sorted-by-price/asc")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> findAllOrderByPrice(){
        return  productService.findAllOrderByPriceAsc();
    }

    @GetMapping("/sorted-by-price/desc")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> findAllOrderByPriceDesc(){
        return productService.findAllOrderByPriceDesc();
    }
}
