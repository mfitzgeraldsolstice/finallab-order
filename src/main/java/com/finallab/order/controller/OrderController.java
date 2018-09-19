package com.finallab.order.controller;

import com.finallab.order.model.Orders;
import com.finallab.order.repository.OrdersRepository;
import com.finallab.order.service.OrderService;
import com.finallab.order.service.ShipmentService;
import com.finallab.order.summary.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.criteria.Order;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("")
public class OrderController {

    private String accountUrl;
    private OrderService orderService;
    private ShipmentService shipmentService;
    private RestTemplate restTemplate = new RestTemplate();

    OrderController(@Value("https://localhost:8080/accounts") String accountUrl, OrderService orderService,
                    ShipmentService shipmentService){
        this.accountUrl = accountUrl;
        this.orderService = orderService;
        this.shipmentService = shipmentService;
    }

    @PostMapping("/orders")
    public Orders save(@RequestBody Orders order){
        Orders savedOrder = orderService.save(order);
        return savedOrder;
    }

    @GetMapping("/orders/{accountId}/summary")
    public OrdersForAccountSummary getOrdersForAccountSummary(@PathVariable("accountId") Long accountId) throws URISyntaxException {
        System.out.println("in endpoint");
        HashMap<Long, Date> results = orderService.getOrdersByAccount(accountId);
        AccountResult account = orderService.getAccountInfo(accountId);
        AddressResult address = orderService.getAddressInfo(accountId);
        OrdersForAccountSummary ordersForAccountSummary = new OrdersForAccountSummary(account.getFirstName(),
                account.getLastName(), account.getEmail(), address.getStreet(), address.getAptNumber(),
                address.getCity(), address.getState(), address.getCountry(), address.getZipcode(), results);
        return ordersForAccountSummary;
    }

    @GetMapping("/orders/{accountId}")
    public List<Orders> getOrdersForAccount(@PathVariable("accountId") Long accountId){
        List<Orders> results = orderService.getOrdersByAccountId(accountId);
        return results;
    }

    @GetMapping("/orders/{accountId}/details")
    public List<OrderDetails> getOrderDetailsForAccount(@PathVariable("accountId") Long accountId) throws URISyntaxException {
        List<OrderDetails> results = orderService.getOrderDetailsForAccount(accountId);
        return results;

    }

    /*@GetMapping("/orders{accountId}")
    public OrderDetails getOrderDetails(@RequestBody Orders order) throws URISyntaxException {



        ParameterizedTypeReference<Resource<AccountResult>> emp = new ParameterizedTypeReference<Resource<AccountResult>>() {};
        ResponseEntity<Resource<AccountResult>> accountResponse = restTemplate.exchange(
                RequestEntity.get(new URI(url + order.getAccountId()))
                        .build(),
                emp
        );



        assert response != null;
        Long symbolID;
        if(response.getStatusCode() == HttpStatus.OK){
            SymbolResult symbol = response.getBody().getContent();
            System.out.println(symbol.getSymbolID() + symbol.getName());
            assert symbol != null;
            symbolID = symbol.getSymbolID();
        } else
            symbolID = null;

        Long stockId = symbolID;

        AggregateQuote result = quoteRepository.findMaxPrice(date, stockId);
    }*/

}
