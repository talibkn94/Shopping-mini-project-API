package com.shopping.Mini.Project.service;

import com.shopping.Mini.Project.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductServiceInterface {
    public Product productSave(Product product);
    public Optional<Product> findProductById(Long id);
    public List<Product> allProductList();
    public String deleteProductById(Long id);

}
