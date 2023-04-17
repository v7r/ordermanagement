package com.visonet.vamsidhar.ordermanagement.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "Orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String orderNumber;

    @Column(nullable = false)
    private Date createdDate = new Date();

    @Column(nullable = false)
    private Date modifiedDate  = new Date();

    private boolean active = true;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<LineItem> lineItems = new ArrayList<>();

    public void addLineItem(LineItem lineItem) {
        lineItem.setOrder(this);
        this.lineItems.add(lineItem);
    }

    public com.visonet.vamsidhar.ordermanagement.model.dto.Order toDto() {
        com.visonet.vamsidhar.ordermanagement.model.dto.Order o =
                new com.visonet.vamsidhar.ordermanagement.model.dto.Order();
        o.setId(this.getId());
        o.setOrderNumber(this.getOrderNumber());
        o.setCreatedDate(this.getCreatedDate());
        o.setModifiedDate(this.getModifiedDate());
        List<com.visonet.vamsidhar.ordermanagement.model.dto.LineItem> lineItemList =
                this.getLineItems().stream().map(lineItem -> {
                    com.visonet.vamsidhar.ordermanagement.model.dto.LineItem i =
                            new com.visonet.vamsidhar.ordermanagement.model.dto.LineItem();
                    BeanUtils.copyProperties(lineItem, i, new String[]{"active"});
                    return i;
                }).toList();
        o.setLineItems(lineItemList);
        return o;
    }
}
