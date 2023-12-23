package com.dange.tanmay.common;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data
public class ProductEvent implements Event,Serializable {
    private static final long serialVersionUID = -6470190945414208496L;
    private Long product_id;
    private String productName;
    private Double productPrice;
    private ProductStatus status;

}
