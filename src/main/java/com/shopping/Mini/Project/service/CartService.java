package com.shopping.Mini.Project.service;

import com.shopping.Mini.Project.entity.Cart;
import com.shopping.Mini.Project.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;


    public Cart addToCart(Cart cart){

        return cartRepository.save(cart);

    }
//    public Cart listOfCart( Cart cart)
//    {
//        return cartRepository.findAll(cart);
//    }
public String remove(Long id) {
    if (cartRepository.existsById(id)) {
        cartRepository.deleteById(id);
    }
    return "Cart removed successfully";
}
}





