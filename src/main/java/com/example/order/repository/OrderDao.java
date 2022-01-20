package com.example.order.repository;

import com.example.order.models.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDao extends CrudRepository<Order, Long> {
    List<Order> findAllByCustomer(Long id);

    Optional<Order> findByCustomer(Long id);
}
