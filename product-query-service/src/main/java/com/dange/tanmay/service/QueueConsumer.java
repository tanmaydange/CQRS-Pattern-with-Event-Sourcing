package com.dange.tanmay.service;

import com.dange.tanmay.common.*;
import com.dange.tanmay.entity.ProductEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QueueConsumer {

    @Autowired
    private ProductService productService;

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload ProductEvent event) throws InterruptedException {
        log.info("Message Received"+ event);

        if (event.getStatus() == ProductStatus.CREATED) {
            ProductEntity product = getProductEntity(event);
            productService.createProduct(product);
        } else if (event.getStatus() == ProductStatus.UPDATED){
            ProductEntity product = getProductEntity(event);
            productService.updateProduct(product);
        }else if (event.getStatus() == ProductStatus.DELETED){
            Long productID = event.getProduct_id();
            productService.deleteProduct(productID);
        }
        else{
            log.warn("Unknown Product Status");
        }
        log.info("Processing Complete");
    }

    private ProductEntity getProductEntity(ProductEvent event) {
        ProductEntity product = new ProductEntity();
        product.setProduct_id(event.getProduct_id());
        product.setProductName(event.getProductName());
        product.setProductPrice(event.getProductPrice());
        return product;
    }
}
