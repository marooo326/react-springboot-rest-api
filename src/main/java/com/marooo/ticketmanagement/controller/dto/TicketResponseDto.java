package com.marooo.ticketmanagement.controller.dto;

import com.marooo.ticketmanagement.domain.ticket.TicketType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class TicketResponseDto {

    @Schema(description = "이용권 요약 정보 DTO")
    @Builder
    @Getter
    public static class TicketSummaryDto {
        private Long id;
        private String name;
        private String description;
        private TicketType ticketType;
    }

    @Schema(description = "이용권 세부 정보 DTO")
    @Builder
    @Getter
    public static class TicketDetailDto {
        private Long id;
        private String name;
        private String description;
        private Integer useLimit;
        private Integer validDays;
        private TicketType ticketType;
        private Long storeId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
