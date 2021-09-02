package org.javaboy.order.dao;


import org.javaboy.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by mavlarn on 2018/1/20.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByCustomerId(Long customerId);

    List<Order> findAllByStatusAndCreatedDateBefore(String status, ZonedDateTime checkTime);

    Order findOneByUuid(String uuid);
}
