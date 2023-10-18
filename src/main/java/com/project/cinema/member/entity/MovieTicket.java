package com.project.cinema.member.entity;

import com.project.cinema.cinema.entity.MovieTime;
import com.project.cinema.cinema.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "movie_ticket")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MovieTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_ticket_id")
    private Long movieTicketId;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "time_id")
    private MovieTime movieTime;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}










