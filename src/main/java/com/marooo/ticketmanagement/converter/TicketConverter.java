package com.marooo.ticketmanagement.converter;

import com.marooo.ticketmanagement.controller.dto.TicketRequestDto;
import com.marooo.ticketmanagement.controller.dto.TicketResponseDto;
import com.marooo.ticketmanagement.domain.store.Store;
import com.marooo.ticketmanagement.domain.ticket.MultiUseTicket;
import com.marooo.ticketmanagement.domain.ticket.SubscriptionTicket;
import com.marooo.ticketmanagement.domain.ticket.Ticket;
import com.marooo.ticketmanagement.domain.ticket.TicketType;
import com.marooo.ticketmanagement.exception.ErrorMessage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TicketConverter {

    public static Ticket toTicket(TicketRequestDto.TicketCreateDto ticketCreateDto, Store store) {
        if (ticketCreateDto.getTicketType() == TicketType.MULTI_USE) {
            return MultiUseTicket.builder()
                    .name(ticketCreateDto.getName())
                    .description(ticketCreateDto.getDescription())
                    .store(store)
                    .useLimit(ticketCreateDto.getUseLimit())
                    .build();
        } else if (ticketCreateDto.getTicketType() == TicketType.SUBSCRIPTION) {
            return SubscriptionTicket.builder()
                    .name(ticketCreateDto.getName())
                    .description(ticketCreateDto.getDescription())
                    .store(store)
                    .validDays(ticketCreateDto.getValidDays())
                    .build();
        } else {
            throw new IllegalArgumentException(ErrorMessage.TICKET_TYPE_NOT_SUPPORTED.getMessage());
        }
    }

    public static TicketResponseDto.TicketSummaryDto toSummaryDto(Ticket ticket) {
        return TicketResponseDto.TicketSummaryDto.builder()
                .id(ticket.getId())
                .name(ticket.getName())
                .description(ticket.getDescription())
                .ticketType(ticket.getTicketType())
                .build();
    }

    public static TicketResponseDto.TicketDetailDto toDetailDto(Ticket ticket) {
        if (ticket.getTicketType() == TicketType.MULTI_USE) {
            return toDetailDto((MultiUseTicket) ticket);
        } else if (ticket.getTicketType() == TicketType.SUBSCRIPTION) {
            return toDetailDto((SubscriptionTicket) ticket);
        } else {
            throw new IllegalArgumentException(ErrorMessage.TICKET_TYPE_NOT_SUPPORTED.getMessage());
        }
    }

    public static TicketResponseDto.TicketDetailDto toDetailDto(MultiUseTicket ticket) {
        return TicketResponseDto.TicketDetailDto.builder()
                .id(ticket.getId())
                .name(ticket.getName())
                .description(ticket.getDescription())
                .useLimit(ticket.getUseLimit())
                .ticketType(ticket.getTicketType())
                .storeId(ticket.getStore().getId())
                .createdAt(ticket.getCreatedAt())
                .updatedAt(ticket.getUpdatedAt())
                .build();
    }

    public static TicketResponseDto.TicketDetailDto toDetailDto(SubscriptionTicket ticket) {
        return TicketResponseDto.TicketDetailDto.builder()
                .id(ticket.getId())
                .name(ticket.getName())
                .description(ticket.getDescription())
                .validDays(ticket.getValidDays())
                .ticketType(ticket.getTicketType())
                .storeId(ticket.getStore().getId())
                .createdAt(ticket.getCreatedAt())
                .updatedAt(ticket.getUpdatedAt())
                .build();
    }
}
