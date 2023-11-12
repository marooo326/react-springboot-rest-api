package com.marooo.ticketmanagement.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class StoreResponseDto {

    @Schema(description = "가게 세부 정보 DTO")
    @Builder
    @Getter
    public static class StoreDetailDto {
        private Long id;
        private String name;
        private String phoneNumber;
        private String description;
        private LocalDateTime createdAt;
    }
}
