package org.javaboy.service.service;

import org.javaboy.service.dto.OrderDTO;

import java.util.List;

/**
 * Created by mavlarn on 2018/2/14.
 */
public interface OrderCompositeService {

    List<OrderDTO> getMyOrder(Long id);
}
