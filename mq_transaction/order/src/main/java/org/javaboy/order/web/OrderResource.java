package org.javaboy.order.web;

import org.javaboy.order.dao.OrderRepository;
import org.javaboy.order.domain.Order;
import org.javaboy.service.dto.OrderDTO;
import org.javaboy.service.service.OrderCompositeService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by mavlarn on 2018/1/20.
 */
@RestController
@RequestMapping("/api/order")
public class OrderResource implements OrderCompositeService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Transactional
    @PostMapping("")
    public void create(@RequestBody OrderDTO dto) {
        dto.setUuid(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("order:new", dto);
    }

    @GetMapping("/{customerId}")
    @Override
    public List<OrderDTO> getMyOrder(@PathVariable Long customerId) {
        List<Order> orders = orderRepository.findAllByCustomerId(customerId);
        return orders.stream().map(order -> {
            OrderDTO dto = new OrderDTO();
            dto.setId(order.getId());
            dto.setStatus(order.getStatus());
            dto.setTicketNum(order.getTicketNum());
            dto.setAmount(order.getAmount());
            dto.setCustomerId(order.getCustomerId());
            dto.setTitle(order.getTitle());
            dto.setUuid(order.getUuid());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("")
    public List<Order> getAll() {
        return orderRepository.findAll();
    }


}
