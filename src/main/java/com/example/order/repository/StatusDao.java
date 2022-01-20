package com.example.order.repository;

import com.example.order.models.Order;
import com.example.order.models.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface StatusDao extends CrudRepository<Status, Long> {
}
