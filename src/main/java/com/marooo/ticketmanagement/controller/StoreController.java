package com.marooo.ticketmanagement.controller;

import com.marooo.ticketmanagement.controller.dto.StoreRequestDto;
import com.marooo.ticketmanagement.controller.dto.StoreResponseDto;
import com.marooo.ticketmanagement.controller.dto.TicketRequestDto;
import com.marooo.ticketmanagement.controller.dto.TicketResponseDto;
import com.marooo.ticketmanagement.service.StoreService;
import com.marooo.ticketmanagement.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "모든 가게 조회", description = "모든 가게를 조회합니다.", tags = {"Store Controller"})
    @GetMapping
    public ResponseEntity<List<StoreResponseDto.StoreDetailDto>> getAllStores() {
        return ResponseEntity.ok(storeService.getAllStores());
    }

    @Operation(summary = "특정 가게 조회", description = "특정한 가게를 조회합니다.", tags = {"Store Controller"})
    @GetMapping("/{storeId}")
    public ResponseEntity<StoreResponseDto.StoreDetailDto> getStore(@PathVariable Long storeId) {
        return ResponseEntity.ok(storeService.getStoreById(storeId));
    }

    @Operation(summary = "가게가 발행한 이용권 조회", description = "가게가 발행한 이용권을 조회합니다.", tags = {"Store Controller"})
    @GetMapping("/{storeId}/tickets")
    public ResponseEntity<List<TicketResponseDto.TicketSummaryDto>> getTicketsFromStore(@PathVariable Long storeId) {
        return ResponseEntity.ok(storeService.getTicketsByStoreId(storeId));
    }

    @Operation(summary = "새로운 가게 생성", description = "새로운 가게를 생성합니다.", tags = {"Store Controller"})
    @PostMapping
    public ResponseEntity<StoreResponseDto.StoreDetailDto> createStore(@RequestBody @NonNull StoreRequestDto.StoreCreateDto storeCreateDto,
                                                                       UriComponentsBuilder uriComponentsBuilder) {
        final StoreResponseDto.StoreDetailDto storeDetail = storeService.createStore(storeCreateDto);
        URI location = uriComponentsBuilder.path(BASE_URI + "/{id}")
                .buildAndExpand(storeDetail.getId())
                .toUri();
        return ResponseEntity.created(location).body(storeDetail);
    }

    @Operation(summary = "새로운 이용권 생성", description = "새로운 이용권을 생성합니다.", tags = {"Store Controller"})
    @PostMapping("/{storeId}/tickets")
    public ResponseEntity<TicketResponseDto.TicketDetailDto> createTicket(@PathVariable Long storeId,
                                                                          @RequestBody @NonNull TicketRequestDto.TicketCreateDto ticketCreateDto,
                                                                          UriComponentsBuilder uriComponentsBuilder) {
        final TicketResponseDto.TicketDetailDto ticketDetail = storeService.createTicket(storeId, ticketCreateDto);
        URI location = uriComponentsBuilder.path("/tickets/{id}")
                .buildAndExpand(ticketDetail.getId())
                .toUri();
        return ResponseEntity.created(location).body(ticketDetail);
    }

    @Operation(summary = "가게 삭제", description = "특정 가게를 삭제합니다.", tags = {"Store Controller"})
    @DeleteMapping("/{storeId}")
    public ResponseEntity<Void> deleteStore(@PathVariable @NonNull Long storeId) {
        storeService.deleteStore(storeId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "이용권 삭제", description = "ID를 이용해 특정 이용권을 삭제합니다.", tags = {"Store Controller"})
    @DeleteMapping("/{storeId}/tickets/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long storeId, @PathVariable Long ticketId) {
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.noContent().build();
    }
}
