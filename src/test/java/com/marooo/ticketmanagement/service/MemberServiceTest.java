package com.marooo.ticketmanagement.service;

import com.marooo.ticketmanagement.controller.dto.MemberRequestDto;
import com.marooo.ticketmanagement.controller.dto.MemberResponseDto;
import com.marooo.ticketmanagement.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    MemberRequestDto.MemberCreateDto testDto = MemberRequestDto.MemberCreateDto.builder()
            .name("홍길동")
            .phoneNumber("010-1111-1111")
            .build();

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll();
        memberService.createMember(testDto);
    }

    @Test
    @DisplayName("이름과 전화번호로 멤버를 생성한다.")
    void createMember() {
        //given
        MemberRequestDto.MemberCreateDto memberCreateDto = MemberRequestDto.MemberCreateDto.builder()
                .name("김개똥")
                .phoneNumber("010-4444-4444")
                .build();

        //when
        final MemberResponseDto.MemberDetailDto member = memberService.createMember(memberCreateDto);

        //then
        assertThat(memberService.getAllMembers()).hasSize(2);
        assertThat(memberService.getMemberById(member.getId()).getPhoneNumber()).isEqualTo(member.getPhoneNumber());
    }

    @Test
    @DisplayName("중복된 전화번호로는 멤버를 생성할 수 없다.")
    void createMember_duplicated_phone_number() {
        assertThatThrownBy(() -> memberService.createMember(testDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Member already exists");
    }

    @Test
    @DisplayName("모든 멤버를 조회한다.")
    void getAllMembers() {
        assertThat(memberService.getAllMembers()).hasSize(1);
    }

    @Test
    @DisplayName("Id로 멤버를 조회한다.")
    void getMemberById() {
        //given
        MemberRequestDto.MemberCreateDto memberCreateDto = MemberRequestDto.MemberCreateDto.builder()
                .name("김개똥")
                .phoneNumber("010-4444-4444")
                .build();
        final MemberResponseDto.MemberDetailDto member = memberService.createMember(memberCreateDto);

        //when
        final MemberResponseDto.MemberDetailDto memberById = memberService.getMemberById(member.getId());

        //then
        assertThat(memberById.getId()).isEqualTo(member.getId());
    }

    @Test
    @DisplayName("없는 Id로 멤버를 조회할 수 없다.")
    void getMemberById_invalid_id() {
        assertThatThrownBy(() -> memberService.getMemberById(99L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Member not found");
    }


    @Test
    @DisplayName("Id로 멤버를 삭제한다.")
    void deleteMember() {
        //given
        MemberRequestDto.MemberCreateDto memberCreateDto = MemberRequestDto.MemberCreateDto.builder()
                .name("김개똥")
                .phoneNumber("010-4444-4444")
                .build();
        final MemberResponseDto.MemberDetailDto member = memberService.createMember(memberCreateDto);

        //when
        memberService.deleteMember(member.getId());

        //then
        assertThat(memberService.getAllMembers()).hasSize(1);
    }
}
