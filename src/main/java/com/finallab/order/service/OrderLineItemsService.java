package com.finallab.order.service;

import com.finallab.order.model.OrderLineItems;
import com.finallab.order.model.Orders;
import com.finallab.order.repository.OrderLineItemsRepository;
import com.finallab.order.summary.ShipmentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineItemsService {

    OrderLineItemsRepository orderLineItemsRepository;
    ShipmentService shipmentService;

    public OrderLineItemsService(OrderLineItemsRepository orderLineItemsRepository, ShipmentService shipmentService){
        super();
        this.orderLineItemsRepository = orderLineItemsRepository;
        this.shipmentService = shipmentService;
    }

    public OrderLineItems getOrderLineItemsById(Long id){
        OrderLineItems orderLineItems = orderLineItemsRepository.getOrderLineItemsById(id);
        return orderLineItems;
    }

    public OrderLineItems save(OrderLineItems orderLineItems, Long orderId){
        orderLineItems.setOrderId(orderId);
        OrderLineItems savedOrderLineItem = orderLineItemsRepository.save(orderLineItems);
        return savedOrderLineItem;
    }

    public void delete(OrderLineItems orderLineItems){
        ShipmentResult shipmentToUpdate = shipmentService.getShipment(orderLineItems.getShipmentId());
        shipmentToUpdate.setOrderLineItem(null);
        shipmentService.update(shipmentToUpdate);
        orderLineItemsRepository.delete(orderLineItems);
    }

    public OrderLineItems updateOrderLineItems(OrderLineItems orderLineItems){
        OrderLineItems updatedOrderLineItem = orderLineItemsRepository.save(orderLineItems);
        return updatedOrderLineItem;
    }

    public List<OrderLineItems> getOrderLineItemByOrderId(Long orderId){
        List<OrderLineItems> result = orderLineItemsRepository.getOrderLineItemsByOrderId(orderId);
        return result;
    }

    public List<OrderLineItems> getOrderLineItemsByProductId(Long productId){
        List<OrderLineItems> results = orderLineItemsRepository.getOrderLineItemsByProductId(productId);
        return results;
    }

}
