package com.suzume.closet.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Top {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String brand;

    private String category;

    private String color;

    private String season;

    private String textile;

    private String item;

    private long price;

    private String img;

    private String url;

    @OneToMany(mappedBy = "top")
    private List<Coordi> coordiList;

    @OneToMany(mappedBy = "top")
    private List<UserTop> userTopList;
}
