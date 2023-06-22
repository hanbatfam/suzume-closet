package com.suzume.closet.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne  // default EAGER
    @JoinColumn(name = "follower_id")
    private User followeruser;

    @ManyToOne  // default EAGER
    @JoinColumn(name = "following_id")
    private User followinguser;
}
