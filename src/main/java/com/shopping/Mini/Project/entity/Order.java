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
@Table(name="orders")
public class Order {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//
//    @Column(name = "created_date")
//    private Date createdDate;
//
//    @Column(name = "total_price")
//    private Double totalPrice;
//
//
//    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
//    private List<OrderItem> orderItems;
//
//    @ManyToOne()
//    @JsonIgnore
//    @JoinColumn(name = "Customer_id", referencedColumnName = "id")
//    private Customer customer;
//
//    public void setSessionId(String sessionId) {

 //   }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_date")
    LocalDate localDate=LocalDate.now();

    @Column(name = "total_price")
    private Double totalPrice;


    @OneToMany( fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    private Long customer_id;
}
