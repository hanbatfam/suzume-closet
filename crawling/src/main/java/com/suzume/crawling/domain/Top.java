package com.suzume.crawling.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Top {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private String category;

    private String season;

    private String item;

    private Long price;

    private String img;

    private String url;

    @OneToMany(mappedBy = "top")
    private List<Coordi> coordiList;
}
