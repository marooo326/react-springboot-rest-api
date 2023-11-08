package com.marooo.ticketmanagement.controller.dto;

import com.marooo.ticketmanagement.domain.ticket.TicketType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class TicketResponseDto {

    @Builder
    @Getter
    public static class SummaryDto {
        private Long id;
        private String name;
        private String description;
    }

    @Builder
    @Getter
    public static class DetailDto {
        private Long id;
        private String name;
        private String description;
        private Integer useLimit;
        private Integer validDays;
        private TicketType ticketType;
        private LocalDateTime createdAt;
        private Long storeId;
    }
}
