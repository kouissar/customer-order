package com.honey.api.order.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.honey.api.order.common.Payment;
import com.honey.api.order.common.TransactionRequest;
import com.honey.api.order.common.TransactionResponse;
import com.honey.api.order.entity.Order;
import com.honey.api.order.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private RestTemplate restTemplate;
    private Logger log = LoggerFactory.getLogger(OrderService.class);

    public TransactionResponse saveOrder(TransactionRequest request) throws JsonProcessingException {
        String message = "";
        Order order = request.getOrder();
        repository.save(order);
        System.out.println(order.getOrderId());
        System.out.println(order.getCustomerName());
        System.out.println(order.getAmount());
        Payment payment = request.getPayment();

        //System.out.println(payment.getPaymentStatus());
        payment.setOrderId(order.getOrderId());
        payment.setAmount(order.getAmount());
        log.info("OrderService request: {}",new ObjectMapper().writeValueAsString(request));
        //rest api call
        Payment paymentResponse = restTemplate.postForObject("http://Payment-Service/payment/doPayment", payment, Payment.class);

        System.out.println("-----------");
        System.out.println(paymentResponse.getPaymentId());
        System.out.println(paymentResponse.getPaymentStatus());
        System.out.println(paymentResponse.getOrderId());
        System.out.println(paymentResponse.getAmount());
        message=paymentResponse.getPaymentStatus().equals("Success")?"Payment Successful":"payment failed";
        return new TransactionResponse(order, paymentResponse.getPaymentId(), paymentResponse.getAmount(), paymentResponse.getPaymentStatus(),message);

    }

    public List<Order> getOrders(){
        return repository.findAll();
    }
    public Order getOrderById(int orderId){
        return repository.findById(orderId).get();
    }

}
