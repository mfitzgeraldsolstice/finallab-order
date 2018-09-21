package com.finallab.order.service;

import com.finallab.order.summary.AccountResult;
import com.finallab.order.summary.AddressResult;
import com.finallab.order.summary.ShipmentResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@Component
@FeignClient(name="shipment-service")
public interface ShipmentService {

    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    ShipmentResult getShipment(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value="/shipments/{accountId}")
    List<ShipmentResult> getShipmentsByAccountId(@PathVariable("accountId") Long id);

    @PutMapping("/update")
    ShipmentResult update(@RequestBody ShipmentResult shipmentResult);

}