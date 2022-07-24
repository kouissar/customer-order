package com.honey.api.order.common;

import com.honey.api.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

    private Order order;
    private int paymentId;
    private double amount;
    private String paymentStatus;
    private String message;

}
