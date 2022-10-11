package com.shopping.Mini.Project.repository;


import com.shopping.Mini.Project.entity.Cart;
import com.shopping.Mini.Project.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    //Cart findAll(Cart cart);

    //List<Cart> findAllByUserOrderByCreatedDateDesc(Customer customer);
     // List<Cart> deleteByUser(Customer customer);

}

