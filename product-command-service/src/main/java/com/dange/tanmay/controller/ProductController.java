package com.dange.tanmay.controller;

import com.dange.tanmay.entity.ProductEntity;
import com.dange.tanmay.repository.ProductEntityRepository;
import com.dange.tanmay.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    ProductEntity addProduct(@RequestBody ProductEntity product){
        return productService.createProduct(product);
    }


    @PostMapping("/update")
    ProductEntity updateProduct(@RequestBody ProductEntity product){
        return productService.updateProduct(product);
    }


    @DeleteMapping("/delete")
    void deleteProducts(@RequestParam Long productId){
        productService.deleteProduct(productId);
    }

}
