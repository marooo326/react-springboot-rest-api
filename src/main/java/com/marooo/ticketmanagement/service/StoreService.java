package com.marooo.ticketmanagement.service;

import com.marooo.ticketmanagement.controller.dto.StoreRequestDto;
import com.marooo.ticketmanagement.controller.dto.StoreResponseDto;
import com.marooo.ticketmanagement.controller.dto.TicketRequestDto;
import com.marooo.ticketmanagement.controller.dto.TicketResponseDto;
import com.marooo.ticketmanagement.converter.StoreConverter;
import com.marooo.ticketmanagement.domain.store.Store;
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

    public List<StoreResponseDto.DetailDto> getAllStores() {
        return storeRepository.findAll().stream()
                .map(StoreConverter::toDetailDto)
                .toList();
    }

    public StoreResponseDto.DetailDto getStoreById(Long storeId) {
        final Store store = storeRepository.findById(storeId).orElseThrow(() -> new NoSuchElementException("Store not found"));
        return StoreConverter.toDetailDto(store);
    }

    @Transactional(readOnly = false)
    public StoreResponseDto.DetailDto createStore(StoreRequestDto.CreateDto createDto) {
        if (storeRepository.existsByPhoneNumber(createDto.getPhoneNumber()))
            throw new IllegalArgumentException("Store already exists");
        final Store store = storeRepository.save(StoreConverter.toStore(createDto));
        return StoreConverter.toDetailDto(store);
    }

    @Transactional(readOnly = false)
    public void deleteStore(Long storeId) {
        if (!storeRepository.existsById(storeId))
            throw new NoSuchElementException("Store not found");
        storeRepository.deleteById(storeId);
    }

    // Related to tickets below
    public List<TicketResponseDto.SummaryDto> getTicketsByStoreId(Long storeId) {
        if (!storeRepository.existsById(storeId))
            throw new NoSuchElementException("Store not found");
        return ticketService.getTicketsByStoreId(storeId);
    }

    @Transactional(readOnly = false)
    public TicketResponseDto.DetailDto createTicket(Long storeId, TicketRequestDto.CreateDto createDto) {
        final Store store = storeRepository.findById(storeId).orElseThrow(() -> new NoSuchElementException("Store not found"));
        return ticketService.createTicket(createDto, store);
    }
}
