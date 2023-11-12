package com.marooo.ticketmanagement.controller;

import com.marooo.ticketmanagement.controller.dto.MemberRequestDto;
import com.marooo.ticketmanagement.controller.dto.MemberResponseDto;
import com.marooo.ticketmanagement.controller.dto.MemberTicketResponseDto;
import com.marooo.ticketmanagement.service.MemberService;
import com.marooo.ticketmanagement.service.MemberTicketService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "모든 회원 조회", description = "모든 회원 정보를 가져옵니다.", tags = {"Member Controller"})
    @GetMapping
    public ResponseEntity<List<MemberResponseDto.DetailDto>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @Operation(summary = "특정 회원 조회", description = "특정 회원 정보를 가져옵니다.", tags = {"Member Controller"})
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto.DetailDto> getMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getMemberById(memberId));
    }

    @Operation(summary = "회원의 이용권 조회", description = "해당 회원의 이용권을 가져옵니다.", tags = {"Member Controller"})
    @GetMapping("/{memberId}/tickets")
    public ResponseEntity<List<MemberTicketResponseDto.DetailDto>> getMemberTicketsFromMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberTicketService.getMemberTicketsByMemberId(memberId));
    }

    @Operation(summary = "새로운 회원 등록", description = "새로운 회원을 등록합니다.", tags = {"Member Controller"})
    @PostMapping
    public ResponseEntity<MemberResponseDto.DetailDto> createMember(@RequestBody @NonNull MemberRequestDto.CreateDto createDto,
                                                                    UriComponentsBuilder uriComponentsBuilder) {
        MemberResponseDto.DetailDto memberDetail = memberService.createMember(createDto);
        URI location = uriComponentsBuilder.path(BASE_URI + "/{id}")
                .buildAndExpand(memberDetail.getId())
                .toUri();
        return ResponseEntity.created(location).body(memberDetail);
    }

    @Operation(summary = "회원에게 이용권 지급", description = "회원에게 이용권을 지급합니다.", tags = {"Member Controller"})
    @PutMapping("/{memberId}/tickets/{ticketId}")
    public ResponseEntity<MemberTicketResponseDto.SummaryDto> assignTicketToMember(@PathVariable Long memberId, @PathVariable Long ticketId,
                                                                                   UriComponentsBuilder uriComponentsBuilder) {
        MemberTicketResponseDto.SummaryDto summaryDto = memberTicketService.assignTicketToMember(memberId, ticketId);
        URI location = uriComponentsBuilder.path(BASE_URI + "/{id}/tickets/{ticketId}")
                .buildAndExpand(summaryDto.getMemberId(), summaryDto.getTicketId())
                .toUri();
        return ResponseEntity.created(location).body(summaryDto);
    }

    @Operation(summary = "회원 삭제", description = "특정 회원을 삭제합니다.", tags = {"Member Controller"})
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "이용권 삭제", description = "회원의 이용권을 삭제합니다.", tags = {"Member Controller"})
    @DeleteMapping("/{memberId}/tickets/{ticketId}")
    public ResponseEntity<MemberResponseDto.DetailDto> deleteTicketFromMember(@PathVariable Long memberId, @PathVariable Long ticketId) {
        memberTicketService.deleteMemberTicket(memberId, ticketId);
        return ResponseEntity.noContent().build();
    }
}
