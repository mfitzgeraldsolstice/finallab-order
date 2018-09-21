package com.finallab.order.controller;

import com.finallab.order.service.OrderLineItemsService;
import com.finallab.order.summary.ShipmentResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.aspectj.weaver.ast.Or;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.finallab.order.model.OrderLineItems;
import com.finallab.order.repository.OrderLineItemsRepository;
import com.finallab.order.repository.OrdersRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.ws.rs.Path;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class OrderLineItemsController {

    Logger logger = LoggerFactory.getLogger("OrderLineItemsController");

    private OrdersRepository ordersRepository;
    private OrderLineItemsRepository orderLineItemsRepository;
    private OrderLineItemsService orderLineItemsService;

    public OrderLineItemsController(OrdersRepository ordersRepository, OrderLineItemsRepository orderLineItemsRepository,
                                    OrderLineItemsService orderLineItemsService){
        this.orderLineItemsRepository = orderLineItemsRepository;
        this.ordersRepository = ordersRepository;
        this.orderLineItemsService = orderLineItemsService;
    }

    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "getOrderLineItemsByIdFallback")
    public OrderLineItems getOrderLineItemsById(@PathVariable("id") Long id){
        OrderLineItems orderLineItems = orderLineItemsService.getOrderLineItemsById(id);
        return orderLineItems;
    }

    @PutMapping("/update")
    @HystrixCommand(fallbackMethod = "updateOrderLineItemFallback")
    public OrderLineItems updateOrderLineItem(@RequestBody OrderLineItems orderLineItems){
        OrderLineItems updatedOrderLineItem = orderLineItemsService.updateOrderLineItems(orderLineItems);
        return updatedOrderLineItem;
    }

    @DeleteMapping("/delete/{id}")
    @HystrixCommand(fallbackMethod = "deleteOrderLineItemFallback")
    public void delete(@PathVariable("id") Long orderLineItemId){
        orderLineItemsService.delete(orderLineItemId);
    }

    @PostMapping("/orders/{orderId}/lines")
    @HystrixCommand(fallbackMethod = "saveOrderLineItemFallback")
    public OrderLineItems saveOrderLineItem(@RequestBody OrderLineItems line, @PathVariable("orderId") Long orderId){
        OrderLineItems savedLine = orderLineItemsService.save(line, orderId);

        logger.info("Order Line Item Saved :" + savedLine.toString());

        return savedLine;
    }

    @GetMapping("/orders/{orderId}/lines")
    @HystrixCommand(fallbackMethod = "getLineByOrderIdFallback")
    public List<OrderLineItems> getLineByOrderId(@PathVariable("orderId") Long orderId){
        List<OrderLineItems> result = orderLineItemsService.getOrderLineItemByOrderId(orderId);
        return result;

    }

    @GetMapping("/orders/product/{productId}")
    @HystrixCommand(fallbackMethod = "getLinesByProductIdFallback")
    public List<OrderLineItems> getLinesByProductId(@PathVariable("productId") Long productId){
        List<OrderLineItems> results = orderLineItemsService.getOrderLineItemsByProductId(productId);
        return results;
    }

    public OrderLineItems getOrderLineItemsByIdFallback(Long id){
        logger.error("Error getting order line items: " + id);
        return new OrderLineItems();
    }

    public OrderLineItems updateOrderLineItemFallback(OrderLineItems orderLineItems){
        logger.error("Error updating order line items: " + orderLineItems);
        return new OrderLineItems();
    }

    public void deleteOrderLineItemFallback(Long orderLineItemId){
        logger.error("Error deleting order line item: " + orderLineItemId);
    }

    public OrderLineItems saveOrderLineItemFallback(OrderLineItems orderLineItems, Long orderId){
        logger.error("Error saving order line items: " + orderLineItems + " " + orderId);
        return new OrderLineItems();
    }

    public List<OrderLineItems> getLineByOrderIdFallback(Long orderId){
        logger.error("Error getting line items by order id: " + orderId);
        return new ArrayList<>();
    }

    public List<OrderLineItems> getLinesByProductIdFallback(Long productId){
        logger.error("Error getting line items by product id: " + productId);
        return new ArrayList<>();
    }
}
