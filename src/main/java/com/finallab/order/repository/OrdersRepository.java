package com.finallab.order.repository;

import com.finallab.order.model.Orders;
import com.finallab.order.summary.OrderDetails;
import com.finallab.order.summary.OrderSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    Orders findById(long id);

    List<Orders> getOrdersByAccountId(long accountId);

    List<Orders> getOrdersByAccountIdOrderByOrderDate(long accountId);

    @Query(nativeQuery = true)
    List<OrderDetails> getOrderDetails(@Param("id") Long id);
}
