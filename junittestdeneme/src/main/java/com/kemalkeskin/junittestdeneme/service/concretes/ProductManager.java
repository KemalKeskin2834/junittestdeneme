package com.kemalkeskin.junittestdeneme.service.concretes;

import com.kemalkeskin.junittestdeneme.core.mapper.ModelMapperService;
import com.kemalkeskin.junittestdeneme.model.Category;
import com.kemalkeskin.junittestdeneme.model.Product;
import com.kemalkeskin.junittestdeneme.repository.CategoryRepository;
import com.kemalkeskin.junittestdeneme.repository.ProductRepository;
import com.kemalkeskin.junittestdeneme.service.abstracts.ProductService;
import com.kemalkeskin.junittestdeneme.service.businessRules.ProductBusinessRules;
import com.kemalkeskin.junittestdeneme.service.dtoS.CategoryResponse;
import com.kemalkeskin.junittestdeneme.service.dtoS.CreateProductRequest;
import com.kemalkeskin.junittestdeneme.service.dtoS.ProductResponse;
import lombok.AllArgsConstructor;
import org.hibernate.type.TrueFalseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapperService modelMapperService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductBusinessRules productBusinessRules;


    @Override
    public List<ProductResponse> listProduct() {
        List<Product>productList=productRepository.findAll();
        return productList.stream().map(product ->  modelMapperService.forResponse().map(product, ProductResponse.class)).collect(Collectors.toList());
    }

    @Override
    public ProductResponse getById(int id) {
        this.productBusinessRules.isFoundId(id);
        Product product=productRepository.findById(id).get();
        return modelMapperService.forResponse().map(product,ProductResponse.class);
    }

    @Override
    public void add(CreateProductRequest createProductRequest) {

        Product product=this.modelMapperService.forRequest().map(createProductRequest,Product.class);
        this.productRepository.save(product);
    }

    @Override
    public void update(int id, CreateProductRequest createProductRequest) {
        this.productBusinessRules.isFoundId(id);
        Optional<Product>product=productRepository.findById(id);
        Product productUpdate=product.get();
        productUpdate.setName(createProductRequest.getName());
        productUpdate.setPrice(createProductRequest.getPrice());
        productUpdate.setStockAmount(createProductRequest.getStockAmount());
        Category category=categoryRepository.findById((int)createProductRequest.getCategoryId()).orElseThrow();
        modelMapperService.forRequest().map(createProductRequest,Product.class);
        this.productRepository.save(productUpdate);

    }

    @Override
    public void delete(int id) {
        this.productBusinessRules.isFoundId(id);
        this.productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponse> listPriceBetween(double minPrice, double maxPrice) {
        this.productBusinessRules.isNullPriceBetween(minPrice,maxPrice);
        List<Product>productList=productRepository.findByPriceBetween(minPrice,maxPrice);
       return  productList.stream().map(product -> modelMapperService.forResponse().map(product,ProductResponse.class)).collect(Collectors.toList());


    }

    @Override
    public List<ProductResponse> findAllOrderByPriceAsc() {

        List<Product>productList=productRepository.findAllOrderByPriceAsc();
        List<ProductResponse>productResponses=productList.stream().map(product -> modelMapperService.forResponse().map(product,ProductResponse.class)).collect(Collectors.toList());
        return productResponses;

    }

    @Override
    public List<ProductResponse> findAllOrderByPriceDesc() {
        List<Product>productList=productRepository.findAllOrderByPriceDesc();
        List<ProductResponse>productResponses=productList.stream().map(product -> modelMapperService.forResponse().map(product,ProductResponse.class)).collect(Collectors.toList());
        return productResponses;
    }
}
