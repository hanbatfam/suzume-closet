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
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/api/board")
    public ResponseEntity<Void> createBoard(@RequestBody BoardCreationRequestDto requestDto) {
        boardService.createBoard(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/api/board")
    public ResponseEntity<BoardListReadResponseDto> readBoardList() {
        BoardListReadResponseDto boardListReadResponseDto = boardService.readBoardList();
        return ResponseEntity.ok().body(boardListReadResponseDto);
    }

    @GetMapping("/api/board/{boardId}")
    public ResponseEntity<BoardReadResponseDto> readBoard(@PathVariable long boardId) {
        BoardReadResponseDto boardReadResponseDto = boardService.readBoard(boardId);
        return ResponseEntity.ok().body(boardReadResponseDto);
    }

    @PutMapping("/api/board")
    public ResponseEntity<Void> updateBoard(@RequestBody BoardUpdateRequestDto requestDto) {
        boardService.updateBoard(requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/board/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable long boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
