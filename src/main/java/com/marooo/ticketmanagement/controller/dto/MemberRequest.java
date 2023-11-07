package com.marooo.ticketmanagement.controller.dto;

import lombok.Builder;
import lombok.Getter;

public class MemberRequest {

    @Builder
    @Getter
    public static class Create {
        private String name;
        private String phoneNumber;
    }
}
