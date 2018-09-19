package com.finallab.order.service;

import com.finallab.order.model.OrderLineItems;
import com.finallab.order.model.Orders;
import com.finallab.order.repository.OrderLineItemsRepository;
import com.finallab.order.repository.OrdersRepository;
import com.finallab.order.summary.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Service
public class OrderService {

    Logger logger = LoggerFactory.getLogger("OrderService");

    private String accountUrl;
    private String addressUrl;
    private RestTemplate restTemplate = new RestTemplate();
    OrdersRepository ordersRepository;
    OrderLineItemsRepository orderLineItemsRepository;
    OrderLineItemsService orderLineItemsService;
    @Autowired AccountService accountService;
    @Autowired ShipmentService shipmentService;
    @Autowired ProductService productService;

    public OrderService(@Value("http://localhost:8080/account/") String accountUrl,
                        @Value("https://localhost:8080/accounts/{id}/address") String addressUrl,
                        OrdersRepository ordersRepository, OrderLineItemsRepository orderLineItemsRepository,
                        OrderLineItemsService orderLineItemsService){
        super();
        this.accountUrl = accountUrl;
        this.addressUrl = addressUrl;
        this.ordersRepository = ordersRepository;
        this.orderLineItemsRepository = orderLineItemsRepository;
        this.orderLineItemsService = orderLineItemsService;
    }

    private AccountResult get(long id){
        return accountService.getAccount(id);
    }

    public Orders save(Orders orders){
        Orders orderSaved = ordersRepository.save(orders);

        return orderSaved;
    }

    public HashMap<Long, Date> getOrdersByAccount(Long accountId){
        List<Orders> results = ordersRepository.getOrdersByAccountIdOrderByOrderDate(accountId);
        HashMap<Long, Date> orderResults = new HashMap<>();
        for (int i = 0; i < results.size(); i++){
            orderResults.put(results.get(i).getOrderNumber(), results.get(i).getOrderDate());
        }
        return orderResults;
    }

    public List<OrderDetails> getOrderDetailsForAccount(Long accountId) throws URISyntaxException {
        List<ShipmentResult> shipments = getShipments(accountId);
        AddressResult address = getAddressInfo(accountId);
        AccountResult account = getAccountInfo(accountId);
        List<Orders> orders = getOrdersByAccountId(accountId);

        HashMap<Long, List<OrderLineItems>> lineItemsByOrder = new HashMap<>();
        for(int i = 0; i < orders.size(); i++){
            lineItemsByOrder.put(orders.get(i).getId(), orders.get(i).getOrderLineItems());
        }

        List<OrderLineItems> lineItems = new ArrayList<>();

        for(int i = 0; i < shipments.size(); i++){
            OrderLineItems temp = orderLineItemsService.getOrderLineItemsById(shipments.get(i).getOrderLineItem());
            lineItems.add(temp);
        }
        List<ProductResult> productResults = new ArrayList<>();

        for(int i = 0; i < lineItems.size(); i++){
            Long tempId = lineItems.get(i).getProductId();
            ProductResult temp = productService.getProduct(lineItems.get(i).getProductId());
            productResults.add(temp);
        }

        List<OrderDetails> orderDetails = new ArrayList<>();

        for (int i = 0; i < orders.size(); i++){
            OrderDetails temp = new OrderDetails();
            temp.setAccount_id(accountId);
            temp.setStreet(address.getStreet());
            temp.setApt_number(address.getAptNumber());
            temp.setCity(address.getCity());
            temp.setState(address.getState());
            temp.setCountry(address.getCountry());
            temp.setZipcode(address.getZipcode());
            temp.setOrderNumber(orders.get(i).getOrderNumber());
            //temp.setLineItems(shipments.get());

            List<ShipmentResult> orderShipments = new ArrayList<>();
            /*for(int j = 0; j < shipments.size(); j++){
                for(int k = 0; k < j; k++){
                    if(shipments.get(i).getOrderLineItems().getOrderId() == orders.get(i).getId()){
                        orderShipments.add(shipments.get(i));
                    }
                }
            }*/
            temp.setShipments(shipments);
            temp.setProduct(productResults);

            orderDetails.add(temp);

        }

        return orderDetails;
    }



    public ArrayList<OrderLineItems> getLineItemsForShipment(Long shipmentId){
        ArrayList<OrderLineItems> lineItems = orderLineItemsRepository.getOrderLineItemsByShipmentId(shipmentId);
        return lineItems;
    }

    public List<ShipmentResult> getShipments(Long accountId){
        List<ShipmentResult> shipment = shipmentService.getShipmentsByAccountId(accountId);
        return shipment;
    }

    public List<Orders> getOrdersByAccountId(Long accountId){
        List<Orders> orders = ordersRepository.getOrdersByAccountId(accountId);
        return orders;
    }

    public AccountResult getAccountInfo(Long accountId) throws URISyntaxException {
        AccountResult account = accountService.getAccount(accountId);
        return account;
    }

    public AddressResult getAddressInfo(Long accountId) throws URISyntaxException {
        AddressResult address = accountService.getAddress(accountId);
        return address;
    }
}
