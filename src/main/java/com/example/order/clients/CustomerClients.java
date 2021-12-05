package com.example.order.clients;

import org.springframework.web.client.RestTemplate;

public class CustomerClients {

    public static Boolean checkCustomer(Long id) {
        final String uri = "http://localhost:8080/customers/get?id=" + id;

        RestTemplate restTemplate = new RestTemplate();
        try {
            if (restTemplate.getForObject(uri, Object.class) != null) return true;

        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
