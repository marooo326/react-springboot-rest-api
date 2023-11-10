package com.marooo.ticketmanagement.controller;

import com.marooo.ticketmanagement.controller.dto.MemberRequestDto;
import com.marooo.ticketmanagement.controller.dto.MemberResponseDto;
import com.marooo.ticketmanagement.controller.dto.MemberTicketResponseDto;
import com.marooo.ticketmanagement.service.MemberService;
import com.marooo.ticketmanagement.service.MemberTicketService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private static final String BASE_URI = "/v1/members";

    private final MemberService memberService;
    private final MemberTicketService memberTicketService;

    @GetMapping
    public ResponseEntity<List<MemberResponseDto.DetailDto>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto.DetailDto> getMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getMemberById(memberId));
    }

    @GetMapping("/{memberId}/tickets")
    public ResponseEntity<List<MemberTicketResponseDto.DetailDto>> getMemberTicketsFromMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberTicketService.getMemberTicketsByMemberId(memberId));
    }

    @PostMapping
    public ResponseEntity<MemberResponseDto.DetailDto> createMember(@RequestBody @NonNull MemberRequestDto.CreateDto createDto,
                                                                    UriComponentsBuilder uriComponentsBuilder) {
        MemberResponseDto.DetailDto memberDetail = memberService.createMember(createDto);
        URI location = uriComponentsBuilder.path(BASE_URI + "/{id}")
                .buildAndExpand(memberDetail.getId())
                .toUri();
        return ResponseEntity.created(location).body(memberDetail);
    }

    @PutMapping("/{memberId}/tickets/{ticketId}")
    public ResponseEntity<MemberTicketResponseDto.SummaryDto> assignTicketToMember(@PathVariable Long memberId, @PathVariable Long ticketId,
                                                                                   UriComponentsBuilder uriComponentsBuilder) {
        MemberTicketResponseDto.SummaryDto summaryDto = memberTicketService.assignTicketToMember(memberId, ticketId);
        URI location = uriComponentsBuilder.path(BASE_URI + "/{id}/tickets/{ticketId}")
                .buildAndExpand(summaryDto.getMemberId(), summaryDto.getTicketId())
                .toUri();
        return ResponseEntity.created(location).body(summaryDto);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{memberId}/tickets/{ticketId}")
    public ResponseEntity<MemberResponseDto.DetailDto> deleteTicketFromMember(@PathVariable Long memberId, @PathVariable Long ticketId) {
        memberTicketService.deleteMemberTicket(memberId, ticketId);
        return ResponseEntity.noContent().build();
    }
}
