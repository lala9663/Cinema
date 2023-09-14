package com.project.movie.member.entity;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mId;
    @Column
    private String memberId;
    @Column
    private String memberPw;
    @Column
    private String memberName;
    @Column
    private String memberAge;
    @Column
    private String memberPhone;
    @Column
    private String addr;

}
