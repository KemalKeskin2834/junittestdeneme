package com.kemalkeskin.junittestdeneme.service.businessRules;

import com.kemalkeskin.junittestdeneme.core.exception.BusinessException;
import com.kemalkeskin.junittestdeneme.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoryBusinessRules {

    @Autowired
    private CategoryRepository categoryRepository;


    public void isLengthCategoryName(String categoryName){
        if(categoryName.length()<2){
            throw new BusinessException("The category name must be at least 5 characters long");
        }
    }


    public void isRepeatCategoryName(String categoryName){
        if(categoryRepository.existsByCategoryName(categoryName)){
            throw new BusinessException("this name avaliable");
        }
    }


    public void isFoundId(int id){
        if(!categoryRepository.findById(id).isPresent()){
            throw  new BusinessException("don't found id");
        }
    }
}
