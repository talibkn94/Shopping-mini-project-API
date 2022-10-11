package com.shopping.Mini.Project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name="cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "created_date")
    LocalDate date=LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;


    @OneToOne(targetEntity = Customer.class, fetch = FetchType.EAGER)
    @JoinColumn( nullable = false,name = "customer_id",referencedColumnName = "id")
    private Customer customer;

//    @OneToMany( fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//    private List<CartItem> cartItems;

    private Integer quantity;

}
