package com.dange.tanmay.service;

import com.dange.tanmay.common.Event;
import com.dange.tanmay.common.ProductEvent;
import com.dange.tanmay.common.ProductStatus;
import com.dange.tanmay.entity.ProductEntity;
import com.dange.tanmay.repository.ProductEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductService {


    @Autowired
    private ProductEntityRepository repository;

    @Autowired
    private QueueSender sender;

    public ProductEntity createProduct(ProductEntity product) {
        ProductEntity prd = repository.save(product);
        ProductEvent productEvent = getProductEvent(prd, ProductStatus.CREATED);
        sender.send(productEvent);

        return prd;
    }

    private ProductEvent getProductEvent(ProductEntity product, ProductStatus productStatus) {
        ProductEvent productEvent = new ProductEvent();
        productEvent.setProduct_id(product.getProduct_id());
        productEvent.setProductName(product.getProductName());
        productEvent.setProductPrice(product.getProductPrice());
        productEvent.setStatus(productStatus);
        return productEvent;
    }

    public ProductEntity updateProduct(ProductEntity product) {
        ProductEntity prd = repository.findById(product.getProduct_id()).orElseThrow(RuntimeException::new);
        prd.setProductName(product.getProductName());
        prd.setProductPrice(product.getProductPrice());

        ProductEvent productEvent = getProductEvent(prd, ProductStatus.UPDATED);
        sender.send(productEvent);

        return repository.save(prd);
    }

    public void deleteProduct(Long productId) {
        ProductEntity prd = repository.findById(productId).orElseThrow(RuntimeException::new);
        ProductEvent productEvent = getProductEvent(prd, ProductStatus.DELETED);
        sender.send(productEvent);
        repository.deleteById(productId);
    }


}