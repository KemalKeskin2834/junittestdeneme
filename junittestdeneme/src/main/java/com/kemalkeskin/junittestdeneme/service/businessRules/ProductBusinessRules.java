package com.kemalkeskin.junittestdeneme.service.businessRules;

import com.kemalkeskin.junittestdeneme.core.exception.BusinessException;
import com.kemalkeskin.junittestdeneme.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductBusinessRules {

    @Autowired
    private ProductRepository productRepository;

    public void isFoundId(int id){
        if(!productRepository.findById(id).isPresent()){
            throw  new BusinessException("don't found id");
        }
    }

    public void isNullPriceBetween(double minPrice,double maxPrice){

        if(productRepository.findByPriceBetween(minPrice, maxPrice).isEmpty()){
            throw  new BusinessException("There are no products within the specified price range");
        }
    }
}
