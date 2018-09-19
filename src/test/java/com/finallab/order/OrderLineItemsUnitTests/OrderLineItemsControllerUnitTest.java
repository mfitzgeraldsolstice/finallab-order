package com.finallab.order.OrderLineItemsUnitTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finallab.order.controller.OrderLineItemsController;
import com.finallab.order.model.OrderLineItems;
import com.finallab.order.service.OrderLineItemsService;
import org.hibernate.criterion.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderLineItemsController.class)
public class OrderLineItemsControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderLineItemsService orderLineItemsService;

    @InjectMocks
    private OrderLineItemsController orderLineItemsController;

    private ObjectMapper mapper;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mapper = new ObjectMapper();
    }

    @Test
    public void addOrderLineItem() throws Exception {

        OrderLineItems testLineItem = new OrderLineItems();
        testLineItem.setProduct(1L);
        testLineItem.setQuantity(2L);
        testLineItem.setPrice(300.00);
        testLineItem.setOrderId(1L);

        when(orderLineItemsService.save(any(OrderLineItems.class))).thenReturn(testLineItem);

        /*OrderLineItems aLine = new OrderLineItems();
        testLineItem.setProduct(1L);
        testLineItem.setQuantity(2L);
        testLineItem.setPrice(300.00);
        testLineItem.setOrderId(1L);*/

        String requestJson = mapper.writeValueAsString(testLineItem);

        mockMvc.perform(post("/orders/{orderId}/lines", testLineItem.getOrderId())
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk()).andReturn();
    }

}
