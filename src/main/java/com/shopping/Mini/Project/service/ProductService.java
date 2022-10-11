package com.shopping.Mini.Project.service;

import com.shopping.Mini.Project.entity.Product;
import com.shopping.Mini.Project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductServiceInterface {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product productSave(Product product)
    {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }
    @Override
    public List<Product> allProductList()
    {

        return productRepository.findAll();
    }
    @Override
    public String deleteProductById(Long id) {
        Optional<Product> product=productRepository.findById(id);
        if (product!= null){
            productRepository.deleteById(id);
            return "valid";
        }
        else {
            return null;
        }
    }
    public Product updateProd(Product product) {
        Product product1 = productRepository.findById(product.getId()).get();
        product1.setProductName(checkNull(product1.getProductName(), product.getProductName()));
        product1.setPrice(Double.valueOf(checkNull(String.valueOf(product1.getPrice()), String.valueOf(product.getPrice()))));
        product1.setDiscount(checkNull(product1.getDiscount(), product.getDiscount()));
//        product1.setActualPrice(checkNull(product1.getActualPrice(), product.getActualPrice()));
//        product1.setQuantity(checkNull(product1.getQuantity(), product.getQuantity()));
      //  product1.setProductDese(checkNull(product1.getProductDese(), product.getProductDese()));
        return productRepository.save(product1);
    }

    private String checkNull(String product1, String product) {
        if (product == null)
            return product1;
        return product;
    }

}
