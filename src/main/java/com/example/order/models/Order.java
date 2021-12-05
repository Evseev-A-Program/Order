package com.example.order.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Entity
@ToString
@Table (name = "orders")
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long offer;

    private String name;

    private LocalDate deliveryTime;

    private Long customer;

    private Boolean paid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="status_id")
    private Status status;

    public Order() {

    }
}
