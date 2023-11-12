package com.marooo.ticketmanagement.domain.store;

import com.marooo.ticketmanagement.domain.ticket.Ticket;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "store")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 2, max = 10, message = "이름은 2자 이상 10자 이하로 입력해주세요.")
    private String name;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "번호 형식이 유효하지 않습니다. (예: 010-1234-1234)")
    private String phoneNumber;

    @Column
    private String description;

    @Column
    private LocalDateTime createdAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store", cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
