package com.kemalkeskin.junittestdeneme.service.abstracts;

import com.kemalkeskin.junittestdeneme.service.dtoS.CategoryResponse;
import com.kemalkeskin.junittestdeneme.service.dtoS.CreateCategoryRequest;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse>listCategories();

    CategoryResponse getById(int id);

    void add(CreateCategoryRequest createCategoryRequest);

    void update(int id,CreateCategoryRequest createCategoryRequest);

    void delete(int id);

    CategoryResponse findByCategoryName(String categoryName);

}
