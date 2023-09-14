package com.project.movie.ticket.entity;

import com.project.movie.cinema.entity.MovieTime;
import com.project.movie.cinema.entity.Seat;
import com.project.movie.member.entity.Member;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long ticketId;

    @ManyToOne
    @JoinColumn(name = "time_id", referencedColumnName = "time_id", nullable = false)
    private MovieTime movieTime;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "seat_id", nullable = false)
    private Seat seat;

    @Column(name = "ticket_price", nullable = false)
    private int ticketPrice;

    // 생성자, 게터, 세터 등 필요한 메서드 추가
}
