package com.marooo.ticketmanagement.service;

import com.marooo.ticketmanagement.controller.dto.TicketRequestDto;
import com.marooo.ticketmanagement.controller.dto.TicketResponseDto;
import com.marooo.ticketmanagement.converter.TicketConverter;
import com.marooo.ticketmanagement.domain.store.Store;
import com.marooo.ticketmanagement.domain.ticket.Ticket;
import com.marooo.ticketmanagement.exception.ErrorMessage;
import com.marooo.ticketmanagement.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TicketService {
    private final TicketRepository ticketRepository;

    public List<TicketResponseDto.DetailDto> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(TicketConverter::toDetailDto)
                .toList();
    }

    public TicketResponseDto.SummaryDto getTicketSummary(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .map(TicketConverter::toSummaryDto)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.TICKET_NOT_FOUND.getMessage()));
    }

    public TicketResponseDto.DetailDto getTicketDetail(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .map(TicketConverter::toDetailDto)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.TICKET_NOT_FOUND.getMessage()));
    }

    public List<TicketResponseDto.SummaryDto> getTicketsByStoreId(Long ticketId) {
        return ticketRepository.findByStoreId(ticketId).stream()
                .map(TicketConverter::toSummaryDto)
                .toList();
    }

    @Transactional(readOnly = false)
    public TicketResponseDto.DetailDto createTicket(TicketRequestDto.CreateDto createDto, Store store) {
        final Ticket ticket = ticketRepository.save(TicketConverter.toTicket(createDto, store));
        return TicketConverter.toDetailDto(ticket);
    }

    @Transactional(readOnly = false)
    public void deleteTicket(Long ticketId) {
        if (!ticketRepository.existsById(ticketId))
            throw new NoSuchElementException(ErrorMessage.TICKET_NOT_FOUND.getMessage());
        ticketRepository.deleteById(ticketId);
    }
}
