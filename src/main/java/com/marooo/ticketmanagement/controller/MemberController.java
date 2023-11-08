package com.marooo.ticketmanagement.controller;

import com.marooo.ticketmanagement.controller.dto.MemberRequestDto;
import com.marooo.ticketmanagement.controller.dto.MemberResponseDto;
import com.marooo.ticketmanagement.service.MemberService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController("/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private static final String BASE_URI = "/v1/members";

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<MemberResponseDto.DetailDto>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto.DetailDto> getMember(@PathVariable @NonNull Long memberId) {
        return ResponseEntity.ok(memberService.getMemberById(memberId));
    }

    @PostMapping
    public ResponseEntity<MemberResponseDto.DetailDto> createMember(@RequestBody @NonNull MemberRequestDto.CreateDto member, UriComponentsBuilder uriComponentsBuilder) {
        MemberResponseDto.DetailDto memberDetail = memberService.createMember(member);
        URI location = uriComponentsBuilder.path(BASE_URI + "/{id}")
                .buildAndExpand(memberDetail.getId())
                .toUri();
        return ResponseEntity.created(location).body(memberDetail);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto.DetailDto> deleteMember(@PathVariable @NonNull Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }
}
