package org.javaboy.user.service;

import org.javaboy.service.dto.OrderDTO;
import org.javaboy.user.dao.CustomerRepository;
import org.javaboy.user.dao.PayInfoRepository;
import org.javaboy.user.domain.Customer;
import org.javaboy.user.domain.PayInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mavlarn on 2018/4/1.
 */
@Service
public class CustomerService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PayInfoRepository payInfoRepository;

    @Transactional
    @RabbitListener(queues = "order:pay")
    public void handle(OrderDTO msg) {
        LOG.info("Get new order to pay:{}", msg);
        // 先检查payInfo判断重复消息。
        PayInfo pay = payInfoRepository.findOneByOrderId(msg.getId());
        if (pay != null) {
            LOG.warn("Order already paid, duplicated message.");
            return;
        }

        Customer customer = customerRepository.getById(msg.getCustomerId());
        if (customer.getDeposit() < msg.getAmount()) {
            LOG.info("No enough deposit, need amount:{}", msg.getAmount());
            msg.setStatus("NOT_ENOUGH_DEPOSIT");
            rabbitTemplate.convertAndSend("order:ticket_error", msg);
            return;
        }

        pay = new PayInfo();
        pay.setOrderId(msg.getId());
        pay.setAmount(msg.getAmount());
        pay.setStatus("PAID");
        payInfoRepository.save(pay);
        customerRepository.charge(msg.getCustomerId(), msg.getAmount());

        msg.setStatus("PAID");
        rabbitTemplate.convertAndSend("order:ticket_move", msg);
    }
}
