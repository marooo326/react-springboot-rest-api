package com.marooo.ticketmanagement.controller;

import com.marooo.ticketmanagement.controller.dto.MemberRequest;
import com.marooo.ticketmanagement.controller.dto.MemberResponse;
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
    public ResponseEntity<List<MemberResponse.Info>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse.Info> getMember(@PathVariable @NonNull Long memberId) {
        return ResponseEntity.ok(memberService.getMemberById(memberId));
    }

    @PostMapping
    public ResponseEntity<MemberResponse.Info> createMember(@RequestBody @NonNull MemberRequest.Create member, UriComponentsBuilder uriComponentsBuilder) {
        MemberResponse.Info memberInfo = memberService.createMember(member);
        URI location = uriComponentsBuilder.path(BASE_URI + "/{id}")
                .buildAndExpand(memberInfo.getId())
                .toUri();
        return ResponseEntity.created(location).body(memberInfo);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<MemberResponse.Info> deleteMember(@PathVariable @NonNull Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }
}
