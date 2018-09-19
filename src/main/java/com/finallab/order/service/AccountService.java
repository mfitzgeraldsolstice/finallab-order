package com.finallab.order.service;

import com.finallab.order.summary.AccountResult;
import com.finallab.order.summary.AddressResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.Path;

@Component
@FeignClient(name="account-service")
public interface AccountService {
    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    public AccountResult getAccount(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value="/accounts/{id}/address")
    public AddressResult getAddress(@PathVariable("id") Long id);
}
