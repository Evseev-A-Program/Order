package com.example.order.clients;

import org.springframework.web.client.RestTemplate;

public class OfferClients {

    public static Boolean checkOffer(Long id) {
        final String uri = "http://localhost:8081/offer/get?id=" + id;

        RestTemplate restTemplate = new RestTemplate();
        try {
            if (restTemplate.getForObject(uri, Object.class) != null) return true;

        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
