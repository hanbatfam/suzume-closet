package com.suzume.closet.dto;

import com.suzume.closet.domain.Board;
import com.suzume.closet.vo.BoardVo;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
@Getter
@Builder
public class BoardListReadResponseDto {
    private final List<BoardVo> boardVoList;
}
