package com.suzume.closet.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
@Getter
@Builder
public class BoardCreationRequestDto {
    private final Long userId;

    private final String title;

    private final String content;
}
