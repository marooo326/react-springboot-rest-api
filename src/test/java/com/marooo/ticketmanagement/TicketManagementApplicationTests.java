package com.marooo.ticketmanagement;

import com.marooo.ticketmanagement.domain.member.Member;
import com.marooo.ticketmanagement.domain.member.MemberState;
import com.marooo.ticketmanagement.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
class TicketManagementApplicationTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void test() {

        Member member = Member.builder()
                .name("김철수")
                .phoneNumber("010-1234-5678")
                .status(MemberState.ACTIVE)
                .build();
        memberRepository.save(member);

        log.info(memberRepository.findById(1L).get().getCreatedAt().toString());
        log.info(memberRepository.findById(1L).get().getUpdatedAt().toString());
    }

}
