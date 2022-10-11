package com.shopping.Mini.Project.controller;

import com.shopping.Mini.Project.entity.Product;
import com.shopping.Mini.Project.jwtUtil.JwtUtil;
import com.shopping.Mini.Project.service.JwtSessionService;
import com.shopping.Mini.Project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private JwtSessionService jwtSessionService;
    @Autowired
    ProductService productService;

    //***************************************Add Product Method*****************************************//
    @PutMapping("/addProduct")
    private ResponseEntity<?> addProduct(HttpServletRequest request, @RequestBody Product product) {
        String token = request.getHeader("AuthToken");
        String email = jwtUtil.getSubject(token);
        String validate = jwtSessionService.checkToken(email, token);
        if (validate != null) {
            return ResponseEntity.ok(productService.productSave(product));
        } else {
            return ResponseEntity.ok("Token is Not Valid");
        }
    }
    // ****************************************Products List*******************************************//
    @GetMapping("/products")
    public List<Product> listOfProduct() {
        return productService.allProductList();
    }
    //***************************************Products List By Id*************************************//
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Optional<Product> productById(HttpServletRequest request, @PathVariable("id") Long id) {
        String token=request.getHeader("AuthToken");
        String email = jwtUtil.getSubject(token);
        String validate = jwtSessionService.checkToken(email, token);
        return productService.findProductById(id);
    }
    // ************************Products Delete By Id ******************************************//
            @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
            public ResponseEntity<?> deleteById(HttpServletRequest request,@PathVariable("id") Long id){
        String token=request.getHeader("AuthToken");
        String email = jwtUtil.getSubject(token);
        String validate = jwtSessionService.checkToken(email, token);
        String x = productService.deleteProductById(id);
        if (x!=null){
            return  ResponseEntity.ok("Delete SuccessFully");
        }
        else {
            return ResponseEntity.ok("Id not found");
        }
    }
    //***************************************Products Update By ID****************************************//
    @PutMapping("/product/update")
    public Product updateProduct(HttpServletRequest request, @RequestBody Product product) {
        String token = request.getHeader("authToken");
        String email = jwtUtil.getSubject(token);
        return productService.updateProd(product);
    }
}

