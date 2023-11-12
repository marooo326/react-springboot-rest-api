package com.marooo.ticketmanagement.controller;

import com.marooo.ticketmanagement.controller.dto.TicketResponseDto;
import com.marooo.ticketmanagement.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/tickets")
@RequiredArgsConstructor
public class TicketController {
    private static final String BASE_URI = "/v1/tickets";

    private final TicketService ticketService;

    @Operation(summary = "모든 이용권 조회", description = "모든 이용권을 조회합니다.", tags = {"Ticket Controller"})
    @GetMapping
    public ResponseEntity<List<TicketResponseDto.DetailDto>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @Operation(summary = "특정 이용권 조회", description = "ID를 이용해 특정 이용권을 조회합니다.", tags = {"Ticket Controller"})
    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketResponseDto.DetailDto> getTicketDetail(@PathVariable Long ticketId) {
        return ResponseEntity.ok(ticketService.getTicketDetail(ticketId));
    }
}
