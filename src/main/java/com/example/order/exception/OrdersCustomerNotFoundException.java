package com.example.order.exception;

public class OrdersCustomerNotFoundException extends Exception{
    public OrdersCustomerNotFoundException(String message) {
        super(message);
    }
}
