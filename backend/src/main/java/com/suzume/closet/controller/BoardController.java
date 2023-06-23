package com.suzume.closet.controller;

import com.suzume.closet.dto.BoardCreationRequestDto;
import com.suzume.closet.dto.BoardListReadResponseDto;
import com.suzume.closet.dto.BoardReadResponseDto;
import com.suzume.closet.dto.BoardUpdateRequestDto;
import com.suzume.closet.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("")
    public ResponseEntity<Void> createBoard(@RequestBody BoardCreationRequestDto requestDto) {
        boardService.createBoard(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("")
    public ResponseEntity<BoardListReadResponseDto> readBoardList() {
        BoardListReadResponseDto boardListReadResponseDto = boardService.readBoardList();

        return ResponseEntity.ok().body(boardListReadResponseDto);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardReadResponseDto> readBoard(@PathVariable long boardId) {
        BoardReadResponseDto boardReadResponseDto = boardService.readBoard(boardId);

        return ResponseEntity.ok().body(boardReadResponseDto);
    }

    @PutMapping("")
    public ResponseEntity<Void> updateBoard(@RequestBody BoardUpdateRequestDto requestDto) {
        boardService.updateBoard(requestDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable long boardId) {
        boardService.deleteBoard(boardId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
