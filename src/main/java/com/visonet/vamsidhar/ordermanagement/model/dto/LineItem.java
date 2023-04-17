package com.visonet.vamsidhar.ordermanagement.model.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class LineItem implements Serializable {
    private Long id;

    private String productName;
    private int productQuantity;
    private double costPerUnit;
    private Date createdDate;
    private Date modifiedDate;
}
