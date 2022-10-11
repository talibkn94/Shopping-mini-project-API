package com.shopping.Mini.Project.repository;

import com.shopping.Mini.Project.entity.Customer;
import com.shopping.Mini.Project.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Long> {
    //List<Order> findAllByUserOrderByCreatedDateDesc(Customer customer);

}
