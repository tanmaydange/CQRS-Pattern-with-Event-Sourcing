package com.dange.tanmay.entity;

import com.dange.tanmay.common.ProductStatus;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Table(name="PRODUCT")
@ToString
@Data
@Entity
public class ProductEntity {

    @Id
    @Column(name = "product_id", nullable = false)
    private Long product_id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private Double productPrice;

}
