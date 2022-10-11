package com.shopping.Mini.Project.controller;

import com.shopping.Mini.Project.entity.Cart;
import com.shopping.Mini.Project.entity.Customer;
import com.shopping.Mini.Project.entity.Product;
import com.shopping.Mini.Project.jwtUtil.JwtUtil;
import com.shopping.Mini.Project.service.CartService;
import com.shopping.Mini.Project.service.JwtSessionService;
import com.shopping.Mini.Project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private JwtSessionService jwtSessionService;

    @PostMapping("/addToCart")
    private ResponseEntity<?> addProductInTOCart(HttpServletRequest request, @RequestBody Cart cart) {
        String token = request.getHeader("AuthToken");
        String email = jwtUtil.getSubject(token);
        String validate = jwtSessionService.checkToken(email, token);
        if (validate != null) {
            return ResponseEntity.ok(cartService.addToCart(cart));
        } else {
            return ResponseEntity.ok("Token is Not Valid");
        }
    }

    @DeleteMapping("/delete/cart/{id}")
    private ResponseEntity<?> deleteCart(HttpServletRequest request , @PathVariable ("id") Long id)
    {
        String token = request.getHeader("authToken");
        String email = jwtUtil.getSubject(token);
        String validate = jwtSessionService.checkToken(email,token);
        if (validate!=null){
            return ResponseEntity.ok(cartService.remove(id));
        }
        else {
            return ResponseEntity.ok("There is no Cart at this user Id");
        }
    }


//    @RequestMapping(value = "/cartList",method = RequestMethod.GET)
//    public ResponseEntity<?> listOfCart(@RequestBody Cart cart,HttpServletRequest request)
//    {
//        String token =request.getHeader("AuthToken");
//        String email=jwtUtil.getSubject(token);
//        String validate =jwtSessionService.checkToken(email,token);
//        if(validate !=null)
//        {
//            return ResponseEntity.ok(cartService.listOfCart(cart));
//        }else {
//return ResponseEntity.ok("token is not valid");
//    }
//    @GetMapping("/listOfCart")
//    public ResponseEntity<?> getCartItems(HttpServletRequest request) {
//        String token = request.getHeader("AuthToken");
//        String email = jwtUtil.getSubject(token);
//        String validate = jwtSessionService.checkToken(email, token);
//        if (validate != null) {
//            return ResponseEntity.ok(cartService.listCartItems(new Customer()));
//        } else {
//            return ResponseEntity.ok("Token is Not Valid");
//        }
    }


