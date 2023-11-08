package com.marooo.ticketmanagement.controller;

import com.marooo.ticketmanagement.controller.dto.TicketResponseDto;
import com.marooo.ticketmanagement.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tickets")
@RequiredArgsConstructor
public class TicketController {
    private static final String BASE_URI = "/v1/tickets";

    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<TicketResponseDto.DetailDto>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketResponseDto.DetailDto> getTicketDetail(@PathVariable Long ticketId) {
        return ResponseEntity.ok(ticketService.getTicketDetail(ticketId));
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long ticketId) {
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.noContent().build();
    }
}
