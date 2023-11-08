package com.marooo.ticketmanagement.controller.dto;

import com.marooo.ticketmanagement.domain.ticket.TicketType;
import lombok.Builder;
import lombok.Getter;

public class TicketRequestDto {

    @Builder
    @Getter
    public static class CreateDto {
        private String name;
        private String description;
        private Integer useLimit;
        private Integer validDays;
        private TicketType ticketType;
        private Long storeId;
    }
}
