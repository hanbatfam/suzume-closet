package com.suzume.crawling.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Coordi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String img;

    private String category;

    private Long price;

    @OneToMany(mappedBy = "coordiId")
    private List<Outer> outerList;

    @OneToMany(mappedBy = "coordiId")
    private List<Top> topList;

    @OneToMany(mappedBy = "coordiId")
    private List<Pants> pantsList;

    @OneToMany(mappedBy = "coordiId")
    private List<Shoes> shoesList;
}
