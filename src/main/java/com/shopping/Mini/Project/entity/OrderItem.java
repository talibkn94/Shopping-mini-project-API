package com.shopping.Mini.Project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "orderitems")
public class OrderItem {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;


//    @Column(name = "quantity")
//    private @NotNull Integer quantity;
//
//    @Column(name = "price")
//    private @NotNull Double price;
//
//
//    @Column(name = "created_date")
//    private Date createdDate;
//
//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name = "order_id", referencedColumnName = "id")
//    private Order order;
//
//    @OneToOne
//    @JoinColumn(name = "product_id", referencedColumnName = "id")
//    private Product product;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private @NotNull int quantity;

    @Column(name = "created_date")
    LocalDate localDate=LocalDate.now();

    private Long product_id;


}
