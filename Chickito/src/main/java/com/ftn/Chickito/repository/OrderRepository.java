package com.ftn.Chickito.repository;

import com.ftn.Chickito.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
