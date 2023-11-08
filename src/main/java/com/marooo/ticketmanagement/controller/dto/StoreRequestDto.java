package com.marooo.ticketmanagement.controller.dto;

import lombok.*;

public class StoreRequestDto {

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CreateDto {
        private String name;
        private String phoneNumber;
        private String description;
    }
}
