package com.praveen.mcp_server_stdio.tool;

import com.praveen.mcp_server_stdio.entity.HelpDeskTicket;
import com.praveen.mcp_server_stdio.model.TicketRequest;
import com.praveen.mcp_server_stdio.service.HelpDeskTicketService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class HelpDeskTools {

    private final HelpDeskTicketService helpDeskTicketService;

    @Tool(
            name="createTicket",
            description = "Create a support ticket"
    )
    public String createTicket(
            @ToolParam(description = "Details to create a support ticket")
            TicketRequest ticketRequest
    ) {
        log.info("Creating a support ticket");
        HelpDeskTicket ticket = helpDeskTicketService.createTicket(
                ticketRequest
        );
        return "Ticket #" + ticket.getId() + " created successfully for user "+ ticket.getUsername();
    }

    @Tool(
            name="getTicketStatus",
            description = "Get all tickets for a user based on a given username"
            //returnDirect = true
    )
    public List<HelpDeskTicket> getAllTickets(
            ToolContext toolContext
    ) {
        String username = (String) toolContext.getContext().get("username");
        log.info("Getting all tickets for user {}",username);
        //throw new RuntimeException("Not yet implemented");
        return helpDeskTicketService.getAllTicketsByUsername(username);
    }
}
