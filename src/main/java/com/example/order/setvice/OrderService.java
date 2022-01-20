package com.example.order.setvice;

import com.example.order.exception.OrderNotFoundException;
import com.example.order.exception.OrderOfferNotFoundException;
import com.example.order.exception.OrdersCustomerNotFoundException;
import com.example.order.models.Order;
import com.example.order.repository.OrderDao;
import com.example.order.repository.StatusDao;
import com.example.order.transfer.OrderDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderDao orderDao;
    private final StatusDao statusDao;

    public Order findOrderById(Long id) throws OrderNotFoundException {
        Optional<Order> order = orderDao.findById(id);
        //            throw new OrderNotFoundException("Order Not Found");
        return order.orElse(null);
    }

    public List<OrderDTO> findOrdersByCustomer(Long idCustomer) throws OrderNotFoundException {
        List<OrderDTO> orders = new ArrayList();
        for (Order order : orderDao.findAllByCustomer(idCustomer)) {
            orders.add(OrderDTO.from(order));
        }
        return orders;
    }

    public void buy(Long customerId) throws OrderNotFoundException {
        Order order = orderDao.findAllByCustomer(customerId).stream()
                .filter(x -> !x.getPaid())
                .findFirst()
                .get();
        order.setDeliveryTime(LocalDate.now().plusDays(7));
        order.setPaid(true);
        order.setStatus(statusDao.findById(2L).get());
        orderDao.save(order);
    }

    public Iterable<Order> findAllOrders(){
        return  orderDao.findAll();
    }

    public Iterable<Order>  findAllOrdersByIdCustomer(Long id){

        return orderDao.findAllByCustomer(id);
    }

    public void deleteOrdersById(Long id) throws OrderNotFoundException {
        Order order = orderDao.findById(id).get();
        if (order == null) {
            throw new OrderNotFoundException("Order Not Found");
        }
        orderDao.deleteById(id);
    }

    public void saveOrders(Long customerId, Long idOffer) throws OrderNotFoundException, OrdersCustomerNotFoundException, OrderOfferNotFoundException {
//        if (order == null) {
//            throw new OrderNotFoundException("Order Not Found");
//        }
        List<Order> orders = orderDao.findAllByCustomer(customerId);
        Optional<Order> order = orders.stream().filter(x -> !x.getPaid()).findFirst();
        if (order.isPresent()) {
            order.get().addOffer(idOffer);
            orderDao.save(order.get());
        } else {
            List<Long> list = new ArrayList<>();
            list.add(idOffer);
            Order newOrder = Order.builder()
                    .customer(customerId)
                    .paid(false)
                    .offers(list)
                    .status(statusDao.findById(1L).get())
                    .build();
            orderDao.save(newOrder);
        }


    }
}
