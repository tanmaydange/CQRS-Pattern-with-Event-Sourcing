package com.dange.tanmay.service;

import com.dange.tanmay.common.Event;
import com.dange.tanmay.common.ProductEvent;
import com.dange.tanmay.common.ProductStatus;
import com.dange.tanmay.entity.ProductEntity;
import com.dange.tanmay.repository.ProductEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductEntityRepository repository;

    public ProductEntity createProduct(ProductEntity product) {
        return repository.save(product);
    }

    public ProductEntity updateProduct(ProductEntity product) {
        ProductEntity prd = repository.findById(product.getProduct_id()).orElseThrow(RuntimeException::new);
        prd.setProductName(product.getProductName());
        prd.setProductPrice(product.getProductPrice());
        return repository.save(prd);
    }

    public void deleteProduct(Long productId) {
        ProductEntity prd = repository.findById(productId).orElseThrow(RuntimeException::new);
        repository.deleteById(productId);
    }


    public List<ProductEntity> getAllProducts(){
        return repository.findAll();
    }

    public ProductEntity getProduct(Long productId){
        return repository.findById(productId).orElseThrow(() -> new RuntimeException("Product Not found for the given ID"));
    }

}