package com.suzume.closet.dto;

import com.suzume.closet.vo.BoardVo;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
@Getter
@Builder
public class BoardReadResponseDto {
    private final BoardVo boardVo;
}
