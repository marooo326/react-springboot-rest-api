package com.marooo.ticketmanagement.converter;

import com.marooo.ticketmanagement.controller.dto.TicketRequestDto;
import com.marooo.ticketmanagement.controller.dto.TicketResponseDto;
import com.marooo.ticketmanagement.domain.store.Store;
import com.marooo.ticketmanagement.domain.ticket.MultiUseTicket;
import com.marooo.ticketmanagement.domain.ticket.SubscriptionTicket;
import com.marooo.ticketmanagement.domain.ticket.Ticket;
import com.marooo.ticketmanagement.domain.ticket.TicketType;
import com.marooo.ticketmanagement.exception.ErrorMessage;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TicketConverter {

    public static Ticket toTicket(TicketRequestDto.CreateDto createDto, Store store) {
        if (createDto.getTicketType() == TicketType.MULTI_USE) {
            return MultiUseTicket.builder()
                    .name(createDto.getName())
                    .description(createDto.getDescription())
                    .createdAt(LocalDateTime.now())
                    .store(store)
                    .useLimit(createDto.getUseLimit())
                    .build();
        } else if (createDto.getTicketType() == TicketType.SUBSCRIPTION) {
            return SubscriptionTicket.builder()
                    .name(createDto.getName())
                    .description(createDto.getDescription())
                    .createdAt(LocalDateTime.now())
                    .store(store)
                    .validDays(createDto.getValidDays())
                    .build();
        } else {
            throw new IllegalArgumentException(ErrorMessage.TICKET_TYPE_NOT_SUPPORTED.getMessage());
        }
    }

    public static TicketResponseDto.SummaryDto toSummaryDto(Ticket ticket) {
        return TicketResponseDto.SummaryDto.builder()
                .id(ticket.getId())
                .name(ticket.getName())
                .description(ticket.getDescription())
                .ticketType(ticket.getTicketType())
                .build();
    }

    public static TicketResponseDto.DetailDto toDetailDto(Ticket ticket) {
        if (ticket.getTicketType() == TicketType.MULTI_USE) {
            return toDetailDto((MultiUseTicket) ticket);
        } else if (ticket.getTicketType() == TicketType.SUBSCRIPTION) {
            return toDetailDto((SubscriptionTicket) ticket);
        } else {
            throw new IllegalArgumentException("ErrorMessage.TICKET_TYPE_NOT_SUPPORTED.getMessage()");
        }
    }

    public static TicketResponseDto.DetailDto toDetailDto(MultiUseTicket ticket) {
        return TicketResponseDto.DetailDto.builder()
                .id(ticket.getId())
                .name(ticket.getName())
                .description(ticket.getDescription())
                .useLimit(ticket.getUseLimit())
                .ticketType(ticket.getTicketType())
                .createdAt(ticket.getCreatedAt())
                .storeId(ticket.getStore().getId())
                .build();
    }

    public static TicketResponseDto.DetailDto toDetailDto(SubscriptionTicket ticket) {
        return TicketResponseDto.DetailDto.builder()
                .id(ticket.getId())
                .name(ticket.getName())
                .description(ticket.getDescription())
                .validDays(ticket.getValidDays())
                .ticketType(ticket.getTicketType())
                .createdAt(ticket.getCreatedAt())
                .storeId(ticket.getStore().getId())
                .build();
    }
}
