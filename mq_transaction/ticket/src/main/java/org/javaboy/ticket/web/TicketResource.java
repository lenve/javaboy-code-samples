package org.javaboy.ticket.web;

import org.javaboy.service.dto.OrderDTO;
import org.javaboy.service.dto.TicketDTO;
import org.javaboy.service.service.TicketCompositeService;
import org.javaboy.ticket.dao.TicketRepository;
import org.javaboy.ticket.domain.Ticket;
import org.javaboy.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ticket")
public class TicketResource implements TicketCompositeService {

    @PostConstruct
    public void init() {
        if (ticketRepository.count() > 0) {
            return;
        }
        Ticket ticket = new Ticket();
        ticket.setName("No.1");
        ticket.setTicketNum(1L);
        ticketRepository.save(ticket);
    }

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TicketService ticketService;

    @PostMapping("")
    public OrderDTO create(@RequestBody OrderDTO dto) {
        return dto;
    }

    @GetMapping("/{customerId}")
    @Override
    public List<TicketDTO> getMyTickets(@PathVariable(name = "customerId") Long customerId) {
        List<Ticket> tickets = ticketRepository.findAllByOwner(customerId);
        return tickets.stream().map(ticket -> {
            TicketDTO dto = new TicketDTO();
            dto.setTicketNum(ticket.getTicketNum());
            dto.setId(ticket.getId());
            dto.setLockUser(ticket.getLockUser());
            dto.setName(ticket.getName());
            dto.setOwner(ticket.getOwner());
            return dto;
        }).collect(Collectors.toList());
    }

    @PostMapping("/lock")
    public Ticket lock(@RequestBody OrderDTO dto) {
        return ticketService.lockTicket(dto);
    }
    @PostMapping("/lock2")
    public int lock2(@RequestBody OrderDTO dto) {
        return ticketService.lockTicket2(dto);
    }

    @GetMapping("")
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

}
