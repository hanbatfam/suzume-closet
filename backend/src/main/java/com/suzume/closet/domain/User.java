package com.suzume.closet.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
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
}
