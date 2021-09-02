package org.javaboy.user.feign;

import org.javaboy.service.dto.OrderDTO;
import org.javaboy.service.service.OrderCompositeService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "order", path = "/api/order")
public interface OrderClient extends OrderCompositeService {

    @GetMapping("/{customerId}")
    @Override
    List<OrderDTO> getMyOrder(@PathVariable(name = "customerId") Long id);
}
