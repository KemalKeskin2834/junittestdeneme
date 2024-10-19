package com.kemalkeskin.junittestdeneme.repository;

import com.kemalkeskin.junittestdeneme.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    boolean existsByCategoryName(String categoryName);


    Category findByCategoryName(String categoryName);

    //buraya query yazmayı unutma daha sonra category ve product ile ilgili
}
