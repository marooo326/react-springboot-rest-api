package com.marooo.ticketmanagement.controller.dto;

import com.marooo.ticketmanagement.domain.ticket.Ticket;
import lombok.*;

public class MemberTicketResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class DetailDto {
        private Long id;
        private Ticket ticket;
        private boolean isActive;
    }
}
