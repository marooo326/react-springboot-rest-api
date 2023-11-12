package com.marooo.ticketmanagement.controller.dto;

import com.marooo.ticketmanagement.domain.ticket.TicketType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Size;

public class TicketRequestDto {

    @Schema(description = "이용권 생성 DTO")
    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class TicketCreateDto {
        @Size(min = 2, max = 10, message = "이름은 2자 이상 10자 이하로 입력해주세요.")
        private String name;
        private String description;
        private Integer useLimit;
        private Integer validDays;
        private TicketType ticketType;
    }
}
