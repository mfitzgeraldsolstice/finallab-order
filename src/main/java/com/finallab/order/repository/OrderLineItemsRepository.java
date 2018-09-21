package com.finallab.order.repository;

import com.finallab.order.model.OrderLineItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface OrderLineItemsRepository extends JpaRepository<OrderLineItems, Long> {
    List<OrderLineItems> getOrderLineItemsByOrderId(Long orderId);

    ArrayList<OrderLineItems> getOrderLineItemsByShipmentId(Long shipmentId);

    OrderLineItems getOrderLineItemsById(Long id);

    List<OrderLineItems> getOrderLineItemsByProductId(Long productId);

    void deleteOrderLineItemsById(Long orderLineItemId);

}
