package com.marooo.ticketmanagement.converter;

import com.marooo.ticketmanagement.controller.dto.StoreRequestDto;
import com.marooo.ticketmanagement.controller.dto.StoreResponseDto;
import com.marooo.ticketmanagement.domain.store.Store;
import org.springframework.stereotype.Component;

@Component
public class StoreConverter {
    
    public static Store toStore(StoreRequestDto.CreateDto createDto) {
        return Store.builder()
                .name(createDto.getName())
                .phoneNumber(createDto.getPhoneNumber())
                .description(createDto.getDescription())
                .build();
    }

    public static StoreResponseDto.DetailDto toDetailDto(Store store) {
        return StoreResponseDto.DetailDto.builder()
                .id(store.getId())
                .name(store.getName())
                .phoneNumber(store.getPhoneNumber())
                .description(store.getDescription())
                .createdAt(store.getCreatedAt())
                .build();
    }
}
