package com.suzume.closet.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Coordi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "top_id")
    private Top top;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pants_id")
    private Pants pants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoes_id")
    private Shoes shoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "outer_id")
    private Outer outer;

    private String topCategory;

    private String pantsCategory;

    private String shoesCateogry;

    private String outerCateogry;
}
