package com.praveen.mcp_server_stdio.service;

import com.praveen.mcp_server_stdio.entity.HelpDeskTicket;
import com.praveen.mcp_server_stdio.model.TicketRequest;
import com.praveen.mcp_server_stdio.repository.HelpDeskTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HelpDeskTicketService {

    private final HelpDeskTicketRepository helpDeskTicketRepository;


    public HelpDeskTicket createTicket(
            TicketRequest ticketRequest
    ){
        HelpDeskTicket helpDeskTicket= HelpDeskTicket
                .builder()
                .issue(ticketRequest.issue())
                .username(ticketRequest.username())
                .status("OPEN")
                .createdAt(LocalDateTime.now())
                .eta(LocalDateTime.now().plusDays(7))
                .build();
        return helpDeskTicketRepository.save(helpDeskTicket);
    }

    public List<HelpDeskTicket> getAllTicketsByUsername(
            String username
    ){
        return helpDeskTicketRepository.findByUsername(username);
    }
}
