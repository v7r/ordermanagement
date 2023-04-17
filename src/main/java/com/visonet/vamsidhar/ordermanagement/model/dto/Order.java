package com.visonet.vamsidhar.ordermanagement.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class Order implements Serializable {
    private Long id;
    private String orderNumber;
    private Date createdDate;
    private Date modifiedDate;

    private List<LineItem> lineItems = new ArrayList<>();
}
