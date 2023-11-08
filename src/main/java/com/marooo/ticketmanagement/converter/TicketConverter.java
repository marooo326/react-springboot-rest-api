package com.marooo.ticketmanagement.converter;

import com.marooo.ticketmanagement.controller.dto.TicketRequestDto;
import com.marooo.ticketmanagement.controller.dto.TicketResponseDto;
import com.marooo.ticketmanagement.domain.store.Store;
import com.marooo.ticketmanagement.domain.ticket.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketConverter {

    public static Ticket toTicket(TicketRequestDto.CreateDto createDto, Store store) {
        return Ticket.builder()
                .name(createDto.getName())
                .description(createDto.getDescription())
                .useLimit(createDto.getUseLimit())
                .validDays(createDto.getValidDays())
                .ticketType(createDto.getTicketType())
                .store(store)
                .build();
    }

    public static TicketResponseDto.SummaryDto toSummaryDto(Ticket ticket) {
        return TicketResponseDto.SummaryDto.builder()
                .id(ticket.getId())
                .name(ticket.getName())
                .description(ticket.getDescription())
                .build();
    }

    public static TicketResponseDto.DetailDto toDetailDto(Ticket ticket) {
        return TicketResponseDto.DetailDto.builder()
                .id(ticket.getId())
                .name(ticket.getName())
                .description(ticket.getDescription())
                .useLimit(ticket.getUseLimit())
                .validDays(ticket.getValidDays())
                .ticketType(ticket.getTicketType())
                .createdAt(ticket.getCreatedAt())
                .storeId(ticket.getStore().getId())
                .build();
    }
}
