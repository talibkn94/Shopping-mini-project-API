package com.shopping.Mini.Project.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "Product_Details")
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productKey;
    private String productValue;
}
