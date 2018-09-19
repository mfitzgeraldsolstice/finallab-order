package com.finallab.order.controller;

import com.finallab.order.service.OrderLineItemsService;
import com.finallab.order.summary.ShipmentResult;
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
    public OrderLineItems getOrderLineItemsById(@PathVariable("id") Long id){
        OrderLineItems orderLineItems = orderLineItemsService.getOrderLineItemsById(id);
        return orderLineItems;
    }

    @PutMapping("/update")
    public OrderLineItems updateOrderLineItem(@RequestBody OrderLineItems orderLineItems){
        OrderLineItems updatedOrderLineItem = orderLineItemsService.updateOrderLineItems(orderLineItems);
        return updatedOrderLineItem;
    }

    @DeleteMapping("/delete/lines")
    public void delete(@RequestBody OrderLineItems orderLineItems){
        orderLineItemsService.delete(orderLineItems);
    }

    @PostMapping("/orders/{orderId}/lines")
    public void saveOrderLineItem(@RequestBody OrderLineItems line, @PathVariable("orderId") Long orderId){
        OrderLineItems savedLine = orderLineItemsService.save(line, orderId);

        logger.info("Order Line Item Saved :" + savedLine.toString());

    }

    @GetMapping("/orders/{orderId}/lines")
    public List<OrderLineItems> getLineByOrderId(@PathVariable("orderId") Long orderId){
        List<OrderLineItems> result = orderLineItemsService.getOrderLineItemByOrderId(orderId);
        return result;

    }

    @GetMapping("/orders/product/{productId}")
    public List<OrderLineItems> getLinesByProductId(@PathVariable("productId") Long productId){
        List<OrderLineItems> results = orderLineItemsService.getOrderLineItemsByProductId(productId);
        return results;
    }

    private URI buildAddressUri(OrderLineItems line){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/" + line.getOrderId()).buildAndExpand().toUri();
    }
}
