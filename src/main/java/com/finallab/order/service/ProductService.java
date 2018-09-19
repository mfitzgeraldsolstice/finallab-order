package com.finallab.order.service;

import com.finallab.order.summary.AccountResult;
import com.finallab.order.summary.AddressResult;
import com.finallab.order.summary.ProductResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.Path;

@Component
@FeignClient(name="product-service")
public interface ProductService {
    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    ProductResult getProduct(@PathVariable("id") Long id);
}
