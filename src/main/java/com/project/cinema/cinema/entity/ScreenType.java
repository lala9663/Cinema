package com.project.cinema.cinema.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "screen_type")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ScreenType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screen_type_id)")
    private Long screenTypeId;

    @Column(name = "type_name", nullable = false)
    private String typeName;
    @Column(name = "description")
    private String description;
}
