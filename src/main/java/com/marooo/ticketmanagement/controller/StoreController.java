package com.marooo.ticketmanagement.controller;

import com.marooo.ticketmanagement.controller.dto.StoreRequestDto;
import com.marooo.ticketmanagement.controller.dto.StoreResponseDto;
import com.marooo.ticketmanagement.service.StoreService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController("/v1/stores")
@RequiredArgsConstructor
public class StoreController {
    private static final String BASE_URI = "/v1/stores";

    private final StoreService storeService;

    @GetMapping
    public ResponseEntity<List<StoreResponseDto.DetailDto>> getAllStores() {
        return ResponseEntity.ok(storeService.getAllStores());
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<StoreResponseDto.DetailDto> getStore(@PathVariable @NonNull Long storeId) {
        return ResponseEntity.ok(storeService.getStoreById(storeId));
    }

    @PostMapping
    public ResponseEntity<StoreResponseDto.DetailDto> createStore(@RequestBody @NonNull StoreRequestDto.CreateDto createDto,
                                                                  UriComponentsBuilder uriComponentsBuilder) {
        final StoreResponseDto.DetailDto storeDetail = storeService.createStore(createDto);
        URI location = uriComponentsBuilder.path(BASE_URI + "/{id}")
                .buildAndExpand(storeDetail.getId())
                .toUri();
        return ResponseEntity.created(location).body(storeDetail);
    }

    @DeleteMapping("/{storeId}")
    public ResponseEntity<Void> deleteMember(@PathVariable @NonNull Long storeId) {
        storeService.deleteStore(storeId);
        return ResponseEntity.noContent().build();
    }
}
