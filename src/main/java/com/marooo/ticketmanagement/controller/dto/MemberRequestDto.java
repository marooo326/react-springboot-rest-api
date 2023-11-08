package com.marooo.ticketmanagement.controller.dto;

import lombok.Builder;
import lombok.Getter;

public class MemberRequestDto {

    @Builder
    @Getter
    public static class CreateDto {
        private String name;
        private String phoneNumber;
    }
}
