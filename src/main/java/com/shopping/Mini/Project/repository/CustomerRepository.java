package com.shopping.Mini.Project.repository;

import com.shopping.Mini.Project.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository <Customer,Long> {

    public Optional<Customer> findById(Integer id);

    public boolean existsByEmail(String email);

    public boolean existsByPhoneNo(String contactNo);

    Customer findByEmail(String email);

}
