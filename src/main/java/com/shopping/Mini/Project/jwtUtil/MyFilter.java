package com.shopping.Mini.Project.jwtUtil;

import com.auth0.jwt.JWT;
import com.shopping.Mini.Project.entity.Customer;
import com.shopping.Mini.Project.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyFilter implements Filter {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JwtUtil jwtUtil;
    public static List<String> allowedUrls;
    public static List<String>allowedUrlsForUser;
    public static List<String>allowedUrlsForAdmin;
    static{
        allowedUrls = new ArrayList<>();
        allowedUrls.add("/login");
        allowedUrls.add("/register");
        //allowedUrls.add("/products");
        }
    private boolean isAllowed(String requestURI) {
        return allowedUrls.stream().anyMatch(requestURI::startsWith);
    }
    static{
        allowedUrlsForAdmin = new ArrayList<>();
        allowedUrlsForAdmin.add("/user/profile");
        allowedUrlsForAdmin.add("/update");
        allowedUrlsForAdmin.add("/user/changePassword");
        allowedUrlsForAdmin.add("/addProduct");
       allowedUrlsForAdmin.add("/products");
       allowedUrlsForAdmin.add("/product/update");
    }
    private boolean isAllowedAdmin(String requestURI) {
        return allowedUrlsForAdmin.stream().anyMatch(requestURI::startsWith);
    }
    static{
     allowedUrlsForUser=new ArrayList<>();
     allowedUrlsForUser.add("/user/profile");
     allowedUrlsForUser.add("/update");
     allowedUrlsForUser.add("/user/changePassword");
     allowedUrlsForUser.add("/products");
     allowedUrlsForUser.add("/addToCart");
     allowedUrlsForUser.add("/listOfCart");
     allowedUrlsForUser.add("/order");
     allowedUrlsForUser.add("/cartLis");
     allowedUrlsForUser.add("/delete/cart/{id}");
    }
    private boolean isAllowedUser(String requestURI) {
        return allowedUrlsForUser.stream().anyMatch(requestURI::startsWith);
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest req = (HttpServletRequest) request;
        d*********************************************//
        if(isAllowed(req.getRequestURI())){
            chain.doFilter(request, response);
        }
        else {
            String token = req.getHeader("authToken");
            //***********************************check if token is verified and valid***********************//
            if (jwtUtil.isVerified(token)) {
                String email = jwtUtil.getSubject(token);
                request.setAttribute("email", email);
                Customer customer = customerRepository.findByEmail(email);
                String roleName = customer.getRole().getRollName();
                if (roleName.equals("Admin")) {
                    if (isAllowedAdmin(req.getRequestURI())) {
                        chain.doFilter(request, response);
                    }
                } else {
                    if (isAllowedUser(req.getRequestURI())) {
                        chain.doFilter(request, response);
                    }
                }
            } else {
                throw new ServletException("Exception is authorization");
            }
        }

    }
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
