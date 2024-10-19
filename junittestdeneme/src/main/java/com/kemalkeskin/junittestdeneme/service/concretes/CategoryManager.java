package com.kemalkeskin.junittestdeneme.service.concretes;

import com.kemalkeskin.junittestdeneme.core.mapper.ModelMapperService;
import com.kemalkeskin.junittestdeneme.model.Category;
import com.kemalkeskin.junittestdeneme.repository.CategoryRepository;
import com.kemalkeskin.junittestdeneme.service.abstracts.CategoryService;
import com.kemalkeskin.junittestdeneme.service.businessRules.CategoryBusinessRules;
import com.kemalkeskin.junittestdeneme.service.dtoS.CategoryResponse;
import com.kemalkeskin.junittestdeneme.service.dtoS.CreateCategoryRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapperService  modelMapperService;

    @Autowired
    private CategoryBusinessRules categoryBusinessRules;


    @Override
    public List<CategoryResponse> listCategories() {
        List<Category>categories=categoryRepository.findAll();
        return categories.stream().map(category -> modelMapperService.forResponse().map(category,CategoryResponse.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getById(int id) {
     categoryBusinessRules.isFoundId(id);
     Category category=categoryRepository.findById(id).get();
     return modelMapperService.forResponse().map(category,CategoryResponse.class);
    }

    @Override
    public void add(CreateCategoryRequest createCategoryRequest) {
        categoryBusinessRules.isLengthCategoryName(createCategoryRequest.getCategoryName());
        categoryBusinessRules.isRepeatCategoryName(createCategoryRequest.getCategoryName());
       Category category=modelMapperService.forRequest().map(createCategoryRequest,Category.class);
       this.categoryRepository.save(category);
    }

    @Override
    public void update(int id, CreateCategoryRequest createCategoryRequest) {
        categoryBusinessRules.isLengthCategoryName(createCategoryRequest.getCategoryName());
        categoryBusinessRules.isFoundId(id);
        categoryBusinessRules.isRepeatCategoryName(createCategoryRequest.getCategoryName());
        Optional<Category> category=categoryRepository.findById(id);
        Category categoryUpdate=category.get();
        categoryUpdate.setCategoryName(createCategoryRequest.getCategoryName());
        modelMapperService.forRequest().map(createCategoryRequest,Category.class);
        categoryRepository.save(categoryUpdate);
    }

    @Override
    public void delete(int id) {
        categoryBusinessRules.isFoundId(id);
        this.categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponse findByCategoryName(String categoryName) {
        Category category=categoryRepository.findByCategoryName(categoryName);
       return modelMapperService.forResponse().map(category,CategoryResponse.class);
    }
}
