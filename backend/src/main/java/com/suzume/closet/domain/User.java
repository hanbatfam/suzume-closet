package com.suzume.closet.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String password;

    private String name;

    private String gender;

    private Long age;

    private String address;

    private String personalColor;

    @OneToMany(mappedBy = "user")
    private List<UserTop> userTopList;

    @OneToMany(mappedBy = "user")
    private List<UserPants> userPantsList;

    @OneToMany(mappedBy = "user")
    private List<UserShoes> userShoesList;

    @OneToMany(mappedBy = "user")
    private List<UserOuter> userOuterList;

    @OneToMany(mappedBy = "user")
    private List<UserCoordi> userCoordiList;

    @OneToMany(mappedBy = "user")
    private List<Board> boardList;

    @OneToMany(mappedBy = "user")
    private List<UserBoardLikes> userBoardLikesList;

    @OneToMany(mappedBy = "followeruser")
    private List<Follow> followeruserList;

    @OneToMany(mappedBy = "followinguser")
    private List<Follow> followinguserList;
}
