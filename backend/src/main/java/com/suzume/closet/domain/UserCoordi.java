package com.suzume.closet.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class UserCoordi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_top_id")
    private UserTop userTop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_pants_id")
    private UserPants userPants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_shoes_id")
    private UserShoes userShoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_outer_id")
    private UserOuter userOuter;

    private Long lastDate;
}
