package com.visonet.vamsidhar.ordermanagement.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@Table(name = "Line_Items")
public class LineItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private int productQuantity = 0;

    @Column(nullable = false)
    private double costPerUnit = 1;

    @Column(nullable = false)
    private Date createdDate = new Date();

    @Column(nullable = false)
    private Date modifiedDate = new Date();

    private boolean active = true;
}
