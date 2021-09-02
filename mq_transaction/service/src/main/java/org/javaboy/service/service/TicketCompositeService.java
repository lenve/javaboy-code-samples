package org.javaboy.service.service;

import org.javaboy.service.dto.TicketDTO;

import java.util.List;

/**
 * Created by mavlarn on 2018/4/5.
 */
public interface TicketCompositeService {

    List<TicketDTO> getMyTickets(Long customerId);

}
