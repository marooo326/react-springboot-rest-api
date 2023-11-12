package com.marooo.ticketmanagement.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class StoreRequestDto {

    @Schema(description = "가게 생성 DTO")
    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class StoreCreateDto {
        private String name;
        private String phoneNumber;
        private String description;
    }
}
