package com.marooo.ticketmanagement.controller.dto;

import com.marooo.ticketmanagement.domain.ticket.TicketType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class TicketRequestDto {

    @Schema(description = "이용권 생성 DTO")
    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class TicketCreateDto {
        private String name;
        private String description;
        private Integer useLimit;
        private Integer validDays;
        private TicketType ticketType;
    }
}
