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

    @GetMapping("/add")
    public ResponseEntity addOrder(@RequestParam Long customerId, Long offerId) {
        try {
            orderService.saveOrders(customerId, offerId);
            return ResponseEntity.ok("Order save");
        } catch (OrderNotFoundException | OrdersCustomerNotFoundException | OrderOfferNotFoundException e) {
            return ResponseEntity.badRequest().body((e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity getOneOrder(@RequestParam Long customerId) {
        try {
            return ResponseEntity.ok(orderService.findOrdersByCustomer(customerId));
        } catch (OrderNotFoundException e) {
            return ResponseEntity.badRequest().body((e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get_id_offers")
    public ResponseEntity getIdOrders(@RequestParam Long customerId) {
        try {
            return ResponseEntity.ok(orderService.findIdOrdersByCustomer(customerId));
        } catch (OrderNotFoundException e) {
            return ResponseEntity.badRequest().body((e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/buy")
    public ResponseEntity buyOrder(@RequestParam Long customerId) {
        try {
            orderService.buy(customerId);
            return ResponseEntity.ok("Order paid for");
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
//
//    @GetMapping("/get-all-by-customer")
//    public ResponseEntity getAllOrderByIdCustomer(@RequestParam Long id) {
//        try {
//            return ResponseEntity.ok(orderService.findAllOrdersByIdCustomer(id));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

//    @PutMapping("/update")
//    public ResponseEntity updateOrder(@RequestBody Order order) {
//        try {
//            orderService.saveOrders(order);
//            return ResponseEntity.ok("Order update");
//        } catch (OrderNotFoundException e) {
//            return ResponseEntity.badRequest().body((e.getMessage()));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

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
