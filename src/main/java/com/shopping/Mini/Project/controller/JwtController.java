package com.shopping.Mini.Project.controller;
import com.shopping.Mini.Project.entity.Customer;
import com.shopping.Mini.Project.entity.JwtChangePassword;
import com.shopping.Mini.Project.entity.JwtRequest;
import com.shopping.Mini.Project.entity.JwtResponse;
import com.shopping.Mini.Project.jwtUtil.JwtUtil;
import com.shopping.Mini.Project.service.JwtSessionService;
import com.shopping.Mini.Project.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class JwtController {

    @Autowired
    private JwtUtil jwtUtil;
    // Registration Controller....//
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private JwtSessionService jwtSessionService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody Customer customer) throws Exception {
        if (userDetailsService.existEmail(customer.getEmail())) {
            return ResponseEntity.ok("Email already exist");
        } else if (userDetailsService.existPhoneNo(customer.getPhoneNo())) {
            return ResponseEntity.ok("phoneNo already exist");
        } else {
            return ResponseEntity.ok(userDetailsService.save(customer));
        }
    }
    //Login Controller.....//
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest, HttpServletResponse response) throws Exception {
        final String user= userDetailsService.loginCheck(jwtRequest.getEmail(),jwtRequest.getPassword());
        if(user !=null)
        {
            final String token=jwtUtil.generateJwtToken(user);
            jwtSessionService.tokenSave(jwtRequest.getEmail(),token);
            response.setHeader("AuthToken" ,token);
            return ResponseEntity.ok(userDetailsService.profile(jwtRequest.getEmail()));
        }
            return ResponseEntity.ok("Email and Password Not Match");
    }
    //Customer Update Controller.....//
    @RequestMapping(value = "/user/profile", method = RequestMethod.GET)
    public Object getUser(HttpServletRequest request) {
        String token = request.getHeader("AuthToken");
        String email = jwtUtil.getSubject(token);
        String validate = jwtSessionService.checkToken(email, token);
        if (validate != null){
            return userDetailsService.profile(email);
        }else {
            return "Invalid Token";
        }
    }
    //Password Change Controller....//
    @RequestMapping(value = "/user/changePassword", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody JwtChangePassword jwtChangePassword, HttpServletRequest request,HttpServletResponse response) throws Exception {
        String token = request.getHeader("AuthToken");
        String email = jwtUtil.getSubject(token);
        String validate = jwtSessionService.checkToken(email, token);
        if (validate != null) {
            String verifyEmail = userDetailsService.changePassword(email, jwtChangePassword.getNewPassword(), jwtChangePassword.getOldPassword());

            if (verifyEmail != null) {
                String newToken = jwtUtil.generateJwtToken(email);
                jwtSessionService.changePasswordMethod(email,newToken);
                response.setHeader("AuthToken" ,newToken);
                return ResponseEntity.ok("Password Change successful");
            }
            return ResponseEntity.ok("old password not match");
        }
        return ResponseEntity.ok("Invalid Token");
    }
    @PutMapping("/update")
    public Customer updateEmp(HttpServletRequest request,@RequestBody Customer customer)
    {
        String token=request.getHeader("authToken");
        String email=jwtUtil.getSubject(token);
        return userDetailsService.updateCs(customer);
    }
}