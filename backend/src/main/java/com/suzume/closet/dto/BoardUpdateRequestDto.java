package com.suzume.closet.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
@Getter
@Builder
public class BoardUpdateRequestDto {
    private final Long id;

    private final String title;

    private final String content;
}
