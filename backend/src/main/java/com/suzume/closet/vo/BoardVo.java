package com.suzume.closet.vo;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
@Getter
@Builder
public class BoardVo {
    private final String username;

    private final String title;

    private final String content;

    private final Long viewcount;
}
