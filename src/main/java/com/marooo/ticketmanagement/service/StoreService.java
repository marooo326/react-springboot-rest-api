package com.marooo.ticketmanagement.service;

import com.marooo.ticketmanagement.controller.dto.StoreRequestDto;
import com.marooo.ticketmanagement.controller.dto.StoreResponseDto;
import com.marooo.ticketmanagement.controller.dto.TicketRequestDto;
import com.marooo.ticketmanagement.controller.dto.TicketResponseDto;
import com.marooo.ticketmanagement.converter.StoreConverter;
import com.marooo.ticketmanagement.domain.store.Store;
import com.marooo.ticketmanagement.exception.ErrorMessage;
import com.marooo.ticketmanagement.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;
    private final TicketService ticketService;

    public List<StoreResponseDto.StoreDetailDto> getAllStores() {
        return storeRepository.findAll().stream()
                .map(StoreConverter::toDetailDto)
                .toList();
    }

    public StoreResponseDto.StoreDetailDto getStoreById(Long storeId) {
        final Store store = storeRepository.findById(storeId).orElseThrow(() -> new NoSuchElementException(ErrorMessage.STORE_NOT_FOUND.getMessage()));
        return StoreConverter.toDetailDto(store);
    }

    @Transactional(readOnly = false)
    public StoreResponseDto.StoreDetailDto createStore(StoreRequestDto.StoreCreateDto storeCreateDto) {
        if (storeRepository.existsByPhoneNumber(storeCreateDto.getPhoneNumber()))
            throw new IllegalArgumentException(ErrorMessage.STORE_ALREADY_EXIST.getMessage());
        final Store store = storeRepository.save(StoreConverter.toStore(storeCreateDto));
        return StoreConverter.toDetailDto(store);
    }

    @Transactional(readOnly = false)
    public void deleteStore(Long storeId) {
        if (!storeRepository.existsById(storeId))
            throw new NoSuchElementException(ErrorMessage.STORE_NOT_FOUND.getMessage());
        storeRepository.deleteById(storeId);
    }

    // Related to tickets below
    public List<TicketResponseDto.TicketSummaryDto> getTicketsByStoreId(Long storeId) {
        if (!storeRepository.existsById(storeId))
            throw new NoSuchElementException(ErrorMessage.STORE_NOT_FOUND.getMessage());
        return ticketService.getTicketsByStoreId(storeId);
    }

    @Transactional(readOnly = false)
    public TicketResponseDto.TicketDetailDto createTicket(Long storeId, TicketRequestDto.TicketCreateDto ticketCreateDto) {
        final Store store = storeRepository.findById(storeId).orElseThrow(() -> new NoSuchElementException(ErrorMessage.STORE_NOT_FOUND.getMessage()));
        return ticketService.createTicket(ticketCreateDto, store);
    }
}
