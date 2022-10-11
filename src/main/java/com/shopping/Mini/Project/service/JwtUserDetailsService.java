package com.shopping.Mini.Project.service;


import com.shopping.Mini.Project.dto.CustomerDto;
import com.shopping.Mini.Project.entity.Customer;
import com.shopping.Mini.Project.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements CustomerService  {
    @Autowired
    private CustomerRepository customerRepository;
    //User Come First Time Then User Registered your Self and Save Details in BD//
    public Customer save(Customer customer) {
        Customer newUser = new Customer();
        return customerRepository.save(customer);
    }
    public Customer find(String s){
        System.out.println(customerRepository.findByEmail(s));
        return customerRepository.findByEmail(s);
    }
    // Check Hear Login Customer is available in DB or not//
    // if Customer Provide wrong Id And Password Then throw Exception //
    public String loginCheck(String email, String password){
        try {
            Customer s = customerRepository.findByEmail(email);
            if (email.equals(s.getEmail()) && password.equals(s.getPassword())) {
                return s.getEmail();
            } else {
                return null;
            }
        }catch (Exception e)
        {
            return null;
        }
    }
    @Override
    public boolean existPhoneNo(String phoneNo) {
        return customerRepository.existsByPhoneNo(phoneNo);
    }

    @Override
    public boolean existEmail(String email) {
        return customerRepository.existsByEmail(email);
    }


    @Override
    public Customer getUserById(int id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer getUserByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    //Provide Authority to Customer Change Your personal Details //
    public Customer updateCs(Customer customer) {
        Customer customer1 = customerRepository.findById(customer.getId()).get();
        customer1.setFirstName(checkNull(customer1.getFirstName(), customer.getFirstName()));
        customer1.setLastName(checkNull(customer1.getLastName(),customer.getLastName()));
        customer1.setEmail(checkNull(customer1.getEmail(),customer.getEmail()));
        customer1.setPhoneNo(checkNull(customer1.getPhoneNo(),customer.getPhoneNo()));
        customer1.setPassword(checkNull(customer1.getPassword(),customer.getPassword()));
        customer1.setAddress(checkNull(customer1.getAddress(),customer.getAddress()));
        customer1.setPostCode(checkNull(customer1.getPostCode(),customer.getPostCode()));
        customer1.setCity(checkNull(customer1.getCity(),customer.getPostCode()));
        return customerRepository.save(customer1);
    }
    private String checkNull(String customer1,String customer)
    {
        if(customer==null)
            return customer1;
        return customer;
    }

    // If Customer Have Old Password Then Customer generate new password //
    @Override
    public String changePassword(String email, String newPassword, String oldPassword) {
        Customer customer =customerRepository.findByEmail(email);
        if(oldPassword.equals(customer.getPassword()))
        {
            customer.setPassword(newPassword);
            customerRepository.save(customer);
            return customer.getEmail();
        }
        return null;
    }


    //Customer Get details//
    public CustomerDto profile(String email){
        Customer c= customerRepository.findByEmail(email);
        CustomerDto customerDto=new CustomerDto();
        customerDto.setFirstName(c.getFirstName());
        customerDto.setLastName(c.getLastName());
        customerDto.setEmail(c.getEmail());
        customerDto.setAddress(c.getAddress());
        customerDto.setPhoneNo(c.getPhoneNo());
        customerDto.setPostCode(c.getPostCode());
        customerDto.setCity(c.getCity());
        return customerDto;

    }
}
