package com.kemalkeskin.junittestdeneme.controller;

import com.kemalkeskin.junittestdeneme.service.abstracts.CategoryService;
import com.kemalkeskin.junittestdeneme.service.dtoS.CategoryResponse;
import com.kemalkeskin.junittestdeneme.service.dtoS.CreateCategoryRequest;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
//@RestController
@RequestMapping("/category")
public class CategoryControllers {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponse>listCategory(){
        return categoryService.listCategories();
    }

    @GetMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse getById(@PathVariable int id){
        return categoryService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody CreateCategoryRequest createCategoryRequest){
        this.categoryService.add(createCategoryRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update( @PathVariable int id, @RequestBody CreateCategoryRequest createCategoryRequest){
        this.categoryService.update(id,createCategoryRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id){
    this.categoryService.delete(id);
    }

    @GetMapping("/{categoryName}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse findByCategoryName(@PathVariable String categoryName){
        return categoryService.findByCategoryName(categoryName);
    }

}
