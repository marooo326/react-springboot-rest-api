package com.marooo.ticketmanagement.converter;

import com.marooo.ticketmanagement.controller.dto.StoreRequestDto;
import com.marooo.ticketmanagement.controller.dto.StoreResponseDto;
import com.marooo.ticketmanagement.domain.store.Store;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class StoreConverter {

    public static Store toStore(StoreRequestDto.StoreCreateDto storeCreateDto) {
        return Store.builder()
                .name(storeCreateDto.getName())
                .phoneNumber(storeCreateDto.getPhoneNumber())
                .description(storeCreateDto.getDescription())
                .createdAt(LocalDateTime.now())
                .tickets(new ArrayList<>())
                .build();
    }

    public static StoreResponseDto.StoreDetailDto toDetailDto(Store store) {
        return StoreResponseDto.StoreDetailDto.builder()
                .id(store.getId())
                .name(store.getName())
                .phoneNumber(store.getPhoneNumber())
                .description(store.getDescription())
                .createdAt(store.getCreatedAt())
                .build();
    }
}
