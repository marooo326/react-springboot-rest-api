package com.marooo.ticketmanagement.repository;

import com.marooo.ticketmanagement.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
}
