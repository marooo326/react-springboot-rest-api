package com.marooo.ticketmanagement.controller;

import com.marooo.ticketmanagement.controller.dto.StoreRequestDto;
import com.marooo.ticketmanagement.controller.dto.StoreResponseDto;
import com.marooo.ticketmanagement.controller.dto.TicketRequestDto;
import com.marooo.ticketmanagement.controller.dto.TicketResponseDto;
import com.marooo.ticketmanagement.service.StoreService;
import com.marooo.ticketmanagement.service.TicketService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/stores")
@RequiredArgsConstructor
public class StoreController {
    private static final String BASE_URI = "/v1/stores";

    private final StoreService storeService;
    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<StoreResponseDto.DetailDto>> getAllStores() {
        return ResponseEntity.ok(storeService.getAllStores());
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<StoreResponseDto.DetailDto> getStore(@PathVariable Long storeId) {
        return ResponseEntity.ok(storeService.getStoreById(storeId));
    }

    @GetMapping("/{storeId}/tickets")
    public ResponseEntity<List<TicketResponseDto.SummaryDto>> getTicketsFromStore(@PathVariable Long storeId) {
        return ResponseEntity.ok(storeService.getTicketsByStoreId(storeId));
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

    @PostMapping("/{storeId}/tickets")
    public ResponseEntity<TicketResponseDto.DetailDto> createTicket(@PathVariable Long storeId,
                                                                    @RequestBody @NonNull TicketRequestDto.CreateDto createDto,
                                                                    UriComponentsBuilder uriComponentsBuilder) {
        final TicketResponseDto.DetailDto ticketDetail = storeService.createTicket(storeId, createDto);
        URI location = uriComponentsBuilder.path("/tickets/{id}")
                .buildAndExpand(ticketDetail.getId())
                .toUri();
        return ResponseEntity.created(location).body(ticketDetail);
    }

    @DeleteMapping("/{storeId}")
    public ResponseEntity<Void> deleteStore(@PathVariable @NonNull Long storeId) {
        storeService.deleteStore(storeId);
        return ResponseEntity.noContent().build();
    }
}
