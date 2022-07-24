package com.honey.api.order.repository;

import com.honey.api.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
//    List<Order> findByOrderId(int);

}
