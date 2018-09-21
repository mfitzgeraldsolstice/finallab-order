package com.finallab.order.controller;

import com.finallab.order.model.Orders;
import com.finallab.order.repository.OrdersRepository;
import com.finallab.order.service.OrderService;
import com.finallab.order.service.ShipmentService;
import com.finallab.order.summary.*;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("")
public class OrderController {

    Logger logger = LoggerFactory.getLogger("OrderController");

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
    @HystrixCommand(fallbackMethod = "saveOrdersFallback")
    public Orders save(@RequestBody Orders order){
        Orders savedOrder = orderService.save(order);
        return savedOrder;
    }

    @GetMapping("/orders/{accountId}/summary")
    @HystrixCommand(fallbackMethod = "getOrdersForAccountSummaryFallback")
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
    @HystrixCommand(fallbackMethod = "getOrdersForAccountFallback")
    public List<Orders> getOrdersForAccount(@PathVariable("accountId") Long accountId){
        List<Orders> results = orderService.getOrdersByAccountId(accountId);
        return results;
    }

    @GetMapping("/orders/{accountId}/details")
    //@HystrixCommand(fallbackMethod = "getOrderDetailsForAccountFallback")
    public List<OrderDetails> getOrderDetailsForAccount(@PathVariable("accountId") Long accountId) throws URISyntaxException {
        List<OrderDetails> results = orderService.getOrderDetailsForAccount(accountId);
        return results;
    }

    public Orders saveOrdersFallback(Orders orders){
        logger.error("Error saving order: " + orders);
        return new Orders();
    }

    public OrdersForAccountSummary getOrdersForAccountSummaryFallback(Long accountId){
        logger.error("Error getting orders for account summary: " + accountId);
        return new OrdersForAccountSummary();
    }

    public List<Orders> getOrdersForAccountFallback(Long accountId){
        logger.error("Error getting orders for account: " + accountId);
        return new ArrayList<>();
    }

//    public List<OrderDetails> getOrderDetailsForAccountFallback(Long accountId){
//        logger.error("Error getting order details for account: " + accountId);
//        return new ArrayList<>();
//    }

}
