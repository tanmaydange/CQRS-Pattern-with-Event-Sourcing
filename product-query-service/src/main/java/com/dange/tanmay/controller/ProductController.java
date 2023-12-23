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

    @RequestMapping("/getAll")
    public List<ProductEntity> getAllProducts(){
        return productService.getAllProducts();
    }

    @RequestMapping("/get")
    public ProductEntity getProduct(@RequestParam Long productId){
        return productService.getProduct(productId);
    }

}
