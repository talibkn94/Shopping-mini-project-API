package com.shopping.Mini.Project.service;

import com.shopping.Mini.Project.entity.Customer;

public interface CustomerService {
    public boolean existPhoneNo(String phoneNo);

    public boolean existEmail(String email);


    public Customer getUserById(int id);

    public Customer getUserByEmail(String email);

    Customer updateCs(Customer customer);

    String changePassword(String email, String oldPassword, String newPassword);

}
