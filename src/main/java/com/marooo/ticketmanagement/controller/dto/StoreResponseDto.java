package com.marooo.ticketmanagement.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class StoreResponseDto {

    @Builder
    @Getter
    public static class DetailDto {
        private Long id;
        private String name;
        private String phoneNumber;
        private String description;
        private LocalDateTime createdAt;
    }
}
