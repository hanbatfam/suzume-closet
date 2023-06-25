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

    @OneToMany(mappedBy = "coordi")
    private List<Outers> outersList;

    @OneToMany(mappedBy = "coordi")
    private List<Top> topList;

    @OneToMany(mappedBy = "coordi")
    private List<Pants> pantsList;

    @OneToMany(mappedBy = "coordi")
    private List<Shoes> shoesList;

    public void updatePrice(Long price) {
        this.price = price;
    }
}
