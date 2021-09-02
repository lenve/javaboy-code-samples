package org.javaboy.user.feign;

import org.javaboy.service.dto.TicketDTO;
import org.javaboy.service.service.TicketCompositeService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "ticket", path = "/api/ticket")
public interface TicketClient extends TicketCompositeService {

    @GetMapping("/{customerId}")
    @Override
    List<TicketDTO> getMyTickets(@PathVariable(name = "customerId") Long customerId);

}
