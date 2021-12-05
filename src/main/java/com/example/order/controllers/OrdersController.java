package com.example.order.controllers;

import com.example.order.exception.OrderNotFoundException;
import com.example.order.exception.OrderOfferNotFoundException;
import com.example.order.exception.OrdersCustomerNotFoundException;
import com.example.order.models.Order;
import com.example.order.setvice.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrdersController {

    private final OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity addOrder(@RequestBody Order order) {
        try {
            orderService.saveOrders(order);
            return ResponseEntity.ok("Order save");
        } catch (OrderNotFoundException | OrdersCustomerNotFoundException | OrderOfferNotFoundException e) {
            return ResponseEntity.badRequest().body((e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity getOneOrder(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(orderService.findOrderById(id));
        } catch (OrderNotFoundException e) {
            return ResponseEntity.badRequest().body((e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity getAllOrder() {
        try {
            return ResponseEntity.ok(orderService.findAllOrders());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateOrder(@RequestBody Order order) {
        try {
            orderService.saveOrders(order);
            return ResponseEntity.ok("Order update");
        } catch (OrderNotFoundException e) {
            return ResponseEntity.badRequest().body((e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrdersById(id);
            return ResponseEntity.ok("Order delete");
        } catch (OrderNotFoundException e) {
            return ResponseEntity.badRequest().body((e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
