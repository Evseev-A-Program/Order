package com.example.order.transfer;

import com.example.order.models.Order;
import com.example.order.models.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
public class OrderDTO {

    private Long id;

    private List<Long> offers;

    private LocalDate deliveryTime;

    private Long customer;

    private String paid;

    private String status;

    public static OrderDTO from(Order order) {
       return OrderDTO.builder()
                .id(order.getId())
                .offers(order.getOffers())
                .deliveryTime(order.getDeliveryTime())
                .customer(order.getCustomer())
                .paid(order.getPaid().toString())
                .status(order.getStatus().getName())
                .build();
    }
}
