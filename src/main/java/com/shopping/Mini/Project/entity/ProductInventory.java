package com.shopping.Mini.Project.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "Product_Inventory")
public class ProductInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer availableQuantity;
    private Integer totalQuantity;
}
