package com.shopping.Mini.Project.service;

import com.shopping.Mini.Project.entity.Cart;
import com.shopping.Mini.Project.entity.Order;
import com.shopping.Mini.Project.entity.OrderItem;
import com.shopping.Mini.Project.entity.Product;
import com.shopping.Mini.Project.repository.OrderRepository;
import com.shopping.Mini.Project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public Order orderCreated(Order order){
        List<OrderItem> orderItems = order.getOrderItems();
        int quantity;
        long productid;
        double price;
        double totalPrice=0;

        for (OrderItem list : orderItems) {
            quantity=list.getQuantity();
            productid=list.getProduct_id();
            Product product = productRepository.findById(productid).get();
            price=product.getPrice();
            totalPrice = (quantity * price) +totalPrice;
        }
        order.setTotalPrice(totalPrice);
        return orderRepository.save(order);
    }


}
