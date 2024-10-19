package com.kemalkeskin.junittestdeneme.repository;

import com.kemalkeskin.junittestdeneme.model.Category;
import com.kemalkeskin.junittestdeneme.model.Product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    public void productRepository_findAll_returnListProducts(){
        Category category=new Category();
        category.setCategoryName("stationary");
        categoryRepository.save(category);

        Product product=new Product();
        product.setName("pencil");
        product.setPrice(30);
        product.setStockAmount(10);
        product.setCategory(category);


        Product product1=new Product();
        product1.setName("pencil");
        product1.setPrice(40);
        product1.setStockAmount(10);
        product1.setCategory(category);



        Product productSave=productRepository.save(product);
        Product productSaved1=productRepository.save(product1);

        List<Product>list=productRepository.findAll();

        Assertions.assertThat(list).isNotNull();
        Assertions.assertThat(list).hasSize(2);
    }

    @Test
    public void productRepository_findById_returnProduct(){

        Category category=new Category();
        category.setCategoryName("stationary");

        Product product=new Product();
        product.setName("pencil");
        product.setPrice(40);
        product.setStockAmount(10);
        product.setCategory(category);

        productRepository.save(product);

        Optional <Product> productGetById=productRepository.findById(1);

        Assertions.assertThat(productGetById).isPresent();

    }

    @Test
    public void productRepository_save_return(){
        Category category=new Category();
        category.setCategoryName("stationary");
        categoryRepository.save(category);

        Product product=new Product();
        product.setName("pencil");
        product.setPrice(30);
        product.setStockAmount(10);
        product.setCategory(category);


        Product productSave=productRepository.save(product);


        List<Product>list=productRepository.findAll();

        Assertions.assertThat(list).hasSize(1);
    }

    @Test
    public void productRepository_update_returnProductUpdate(){
        Category category=new Category();
        category.setCategoryName("stationary");
        categoryRepository.save(category);

        Product product=new Product();
        product.setName("pencil");
        product.setPrice(30);
        product.setStockAmount(10);
        product.setCategory(category);
        Product productSave=productRepository.save(product);

        productSave.setName("notebook");
        productRepository.save(productSave);

        Assertions.assertThat(productSave.getName()).isEqualTo("notebook");

    }

    @Test
    public void productRepository_deleteById_productDelete(){
        Category category=new Category();
        category.setCategoryName("stationary");
        categoryRepository.save(category);

        Product product=new Product();
        product.setName("pencil");
        product.setPrice(30);
        product.setStockAmount(10);
        product.setCategory(category);

        Product productSave=productRepository.save(product);

        productRepository.delete(productSave);

        Optional<Product>productDelete = productRepository.findById(productSave.getId());
        Assertions.assertThat(productDelete).isNotPresent();
    }


}
