package com.marooo.ticketmanagement.domain.mapping.memberTicket;

import com.marooo.ticketmanagement.exception.ErrorMessage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberSubscriptionTicket extends MemberTicket {
    @Column
    private LocalDate startDate = LocalDate.MIN;

    @Column
    private LocalDate expireDate = LocalDate.MAX;

    @Override
    public void updateTicketState() {
        if (expireDate.isBefore(LocalDate.now())) {
            this.state = MemberTicketState.EXPIRED;
        }
    }

    @Override
    public void useTicket() {
        updateTicketState();
        if (state == MemberTicketState.EXPIRED)
            throw new IllegalStateException(ErrorMessage.MEMBER_TICKET_EXPIRED.getMessage());
    }
}
