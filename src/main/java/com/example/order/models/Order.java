package com.example.order.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "order_id"))
    private List<Long> offers;

   // private String name;

    private LocalDate deliveryTime;

    private Long customer;

    private Boolean paid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="status_id")
    private Status status;

    public Order() {

    }

    public void addOffer(Long id) {
        offers.add(id);
    }

    public void deleteOffer(Long id) {
        offers.remove(id);
    }
}
