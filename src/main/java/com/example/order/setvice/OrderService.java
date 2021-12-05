package com.example.order.setvice;

import com.example.order.clients.CustomerClients;
import com.example.order.clients.OfferClients;
import com.example.order.exception.OrderNotFoundException;
import com.example.order.exception.OrderOfferNotFoundException;
import com.example.order.exception.OrdersCustomerNotFoundException;
import com.example.order.models.Order;
import com.example.order.repository.OrderDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderDao orderDao;


    public Order findOrderById(Long id) throws OrderNotFoundException {
        Order order = orderDao.findById(id).get();
        if ( order == null) {
            throw new OrderNotFoundException("Order Not Found");
        }
        return order;
    }

    public Iterable<Order> findAllOrders(){
        return  orderDao.findAll();
    }

    public void deleteOrdersById(Long id) throws OrderNotFoundException {
        Order order = orderDao.findById(id).get();
        if (order == null) {
            throw new OrderNotFoundException("Order Not Found");
        }
        orderDao.deleteById(id);
    }

    public void saveOrders(Order order) throws OrderNotFoundException, OrdersCustomerNotFoundException, OrderOfferNotFoundException {
        if (order == null) {
            throw new OrderNotFoundException("Order Not Found");
        }

        if (!CustomerClients.checkCustomer(order.getCustomer())) {
            throw new OrdersCustomerNotFoundException("Customer id = " + order.getCustomer() + " not found");
        }

        if (!OfferClients.checkOffer(order.getOffer())) {
            throw new OrderOfferNotFoundException("Offer id = " + order.getOffer() + " not found");
        }

        orderDao.save(order);
    }
}
