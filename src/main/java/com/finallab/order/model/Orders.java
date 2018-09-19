package com.finallab.order.model;

import com.finallab.order.summary.AccountResult;
import com.finallab.order.summary.AddressResult;
import com.finallab.order.summary.OrderDetails;
import com.finallab.order.summary.OrderSummary;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "OrderDetails",
                classes = @ConstructorResult(
                        targetClass = OrderDetails.class,
                        columns = {
                                @ColumnResult(name = "account_id", type = Long.class),
                                @ColumnResult(name = "order_number", type = Long.class),
                                @ColumnResult(name = "product_name", type = String.class),
                                @ColumnResult(name = "product_description", type = String.class),
                                @ColumnResult(name = "product_price", type = Double.class),
                                @ColumnResult(name = "product_quantity", type = Double.class),
                                @ColumnResult(name = "order_total_price", type = Double.class),
                                @ColumnResult(name = "apt_number", type = Long.class),
                                @ColumnResult(name = "city", type = String.class),
                                @ColumnResult(name = "country", type = String.class),
                                @ColumnResult(name = "state", type = String.class),
                                @ColumnResult(name = "street", type = String.class),
                                @ColumnResult(name = "zipcode", type = Long.class)

                        }
                )),
                @SqlResultSetMapping(
                        name = "OrderSummary",
                        classes = @ConstructorResult(
                                targetClass = OrderSummary.class,
                                columns = {
                                        @ColumnResult(name = "order_date", type = Date.class),
                                        @ColumnResult(name = "order_number", type = Long.class),
                                        @ColumnResult(name = "account_id", type = Long.class),
                                        @ColumnResult(name = "address_id", type = Long.class)
                                }
                        )
                )
})


@NamedNativeQueries(value = {
        @NamedNativeQuery(
                resultSetMapping = "OrderDetails",
                name = "Orders.getOrderDetails",
                query = "Select distinct * from (select orders.account_id, orders.order_number, " +
                        "product.name as product_name, product.description as product_description, " +
                        "product.price as product_price, order_line_items.quantity as product_quantity, " +
                        "order_line_items.quantity*order_line_items.price as order_total_price from orders, " +
                        "product, order_line_items where orders.account_id = :id and orders.id = order_line_items.order_id " +
                        "and order_line_items.product_id = product.id) t1 inner join " +
                        "(select distinct account_address.account_id, address.apt_number, address.city, address.country, " +
                        "address.state, address.street, address.zipcode from address, shipment, account_address " +
                        "where address.address_id = shipment.address_id and account_address.account_id = :id and " +
                        "account_address.address_address_id = shipment.address_id) t2 on t1.account_id = t2.account_id;"
        ),
        @NamedNativeQuery(
                resultSetMapping = "OrderSummary",
                name = "Orders.getOrdersForAccountSummary",
                query = "select DATE(order_date) as order_date, orders.order_number, orders.account_id, orders.address_id from orders where account_id = :id group by order_number, account_id, address_id, order_date  order by order_date;"
        )
})
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    //@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Long accountId;

    private Long orderNumber;
    private Date orderDate;

    //dont need this mapping
    //@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Long addressId;

    //@JsonManagedReference(value="orderLineItems")
    @JoinColumn(name = "orderId")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItems;

    public Orders() {
    }

    public Orders(Long accountId, Long orderNumber, Date orderDate, Long addressId, List<OrderLineItems> orderLineItems) {
        this.accountId = accountId;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.addressId = addressId;
        this.orderLineItems = orderLineItems;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccount(Long accountId) {
        this.accountId = accountId;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddress(Long addressId) {
        this.addressId = addressId;
    }

    public List<OrderLineItems> getOrderLineItems() {
        return orderLineItems;
    }

    public void setOrderLineItems(List<OrderLineItems> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }
}
