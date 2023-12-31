package com.suzume.closet.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Getter
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String content;

    private Long viewcount;

    @OneToMany(mappedBy = "board")
    private List<UserBoardLikes> userBoardLikesList;

    @OneToMany(mappedBy = "board")
    private List<Review> reviewList;

    public void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
