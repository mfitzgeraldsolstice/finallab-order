package com.finallab.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finallab.order.controller.OrderLineItemsController;
import com.finallab.order.model.OrderLineItems;
import com.finallab.order.service.OrderLineItemsService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.results.ResultMatchers;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.*;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static com.sun.javaws.JnlpxArgs.verify;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = OrderLineItemsController.class)
@AutoConfigureMockMvc(secure = false)
public class OrderLineItemsControllerTests {

    @Resource
    WebApplicationContext webApplicationContext;
    @Autowired private MockMvc mockMvc;
    @MockBean private OrderLineItemsService orderLineItemsService;

    private ObjectMapper mapper;
    private OrderLineItems orderLineItems;
    private List<OrderLineItems> orderLineItemsList;

    @Before
    public void shipmentSetUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockitoAnnotations.initMocks(this);
        mapper = new ObjectMapper();
        orderLineItems = initializeTestOrderLineItem();
        orderLineItemsList = initializeTestOrderLineItemList();
    }

    @Test
    public void saveOrderLineItem() throws Exception {
        when(orderLineItemsService.save(any(OrderLineItems.class), any(long.class))).thenReturn(orderLineItems);

        String requestJson = mapper.writeValueAsString(orderLineItems);

        mockMvc.perform(post("/orders/{orderId}/lines", orderLineItems)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk()).andReturn();

    }

    @Test
    public void getOrderLineItemsById() throws Exception {
        when(orderLineItemsService.getOrderLineItemsById(1L)).thenReturn(orderLineItems);

        mockMvc.perform(get("/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].productId", Matchers.is(3)))
                .andExpect(jsonPath("$[0].quantity", Matchers.is(4)))
                .andExpect(jsonPath("$[0].price", Matchers.is(400.00)));

        Mockito.verify(orderLineItemsService, times(1)).getOrderLineItemsById(1L);
    }

    @Test
    public void getOrderLineItemsByProductId() throws Exception {
        when(orderLineItemsService.getOrderLineItemsByProductId(1L)).thenReturn(orderLineItemsList);

        mockMvc.perform(get("/orders/product/{productId}", 3L))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].productId", Matchers.is(3)))
                .andExpect(jsonPath("$[0].quantity", Matchers.is(4)))
                .andExpect(jsonPath("$[0].price", Matchers.is(400.00)));

        Mockito.verify(orderLineItemsService, times(1)).getOrderLineItemsByProductId(3L);
    }

    private OrderLineItems initializeTestOrderLineItem(){

        OrderLineItems orderLineItems = new OrderLineItems(1L, 3L,4L, 50.00,
                400.00, 2L, 2L);

        return orderLineItems;
    }

    private List<OrderLineItems> initializeTestOrderLineItemList(){
        OrderLineItems orderLineItems = new OrderLineItems(1L, 3L,4L, 50.00,
                400.00, 2L, 2L);

        OrderLineItems orderLineItems1 = new OrderLineItems(2L, 2L,4L, 10.00,
                400.00, 2L, 2L);

        List<OrderLineItems> orderLineItemsList = new ArrayList<>();
        orderLineItemsList.add(orderLineItems);
        orderLineItemsList.add(orderLineItems1);
        return orderLineItemsList;
    }

}
