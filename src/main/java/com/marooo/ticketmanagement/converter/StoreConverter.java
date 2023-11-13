package com.marooo.ticketmanagement.converter;

import com.marooo.ticketmanagement.controller.dto.StoreRequestDto;
import com.marooo.ticketmanagement.controller.dto.StoreResponseDto;
import com.marooo.ticketmanagement.domain.store.Store;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreConverter {

    public static Store toStore(StoreRequestDto.StoreCreateDto storeCreateDto) {
        return Store.builder()
                .name(storeCreateDto.getName())
                .phoneNumber(storeCreateDto.getPhoneNumber())
                .description(storeCreateDto.getDescription())
                .build();
    }

    public static StoreResponseDto.StoreDetailDto toDetailDto(Store store) {
        return StoreResponseDto.StoreDetailDto.builder()
                .id(store.getId())
                .name(store.getName())
                .phoneNumber(store.getPhoneNumber())
                .description(store.getDescription())
                .createdAt(store.getCreatedAt())
                .updatedAt(store.getUpdatedAt())
                .build();
    }
}
