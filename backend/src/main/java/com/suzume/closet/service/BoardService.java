package com.suzume.closet.service;

import com.suzume.closet.domain.Board;
import com.suzume.closet.domain.User;
import com.suzume.closet.dto.BoardCreationRequestDto;
import com.suzume.closet.dto.BoardListReadResponseDto;
import com.suzume.closet.dto.BoardReadResponseDto;
import com.suzume.closet.dto.BoardUpdateRequestDto;
import com.suzume.closet.repository.BoardRepository;
import com.suzume.closet.repository.UserRepository;
import com.suzume.closet.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createBoard(BoardCreationRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("Not Existing User")
        );

        Board board = Board.builder()
                .user(user)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .build();

        boardRepository.save(board);
    }

    public BoardListReadResponseDto readBoardList() {
        List<Board> boardList = boardRepository.findAll();

        List<BoardVo> boardVoList = new ArrayList<>();

        for (Board board : boardList) {
            BoardVo boardVo = BoardVo.builder()
                    .username(board.getUser().getName())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .viewcount(board.getViewcount())
                    .build();

            boardVoList.add(boardVo);
        }

        return BoardListReadResponseDto.builder()
                .boardVoList(boardVoList)
                .build();
    }

    public BoardReadResponseDto readBoard(long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("Not Existing Board")
        );

        BoardVo boardVo = BoardVo.builder()
                .username(board.getUser().getName())
                .title(board.getTitle())
                .content(board.getContent())
                .viewcount(board.getViewcount())
                .build();

        return BoardReadResponseDto.builder()
                .boardVo(boardVo)
                .build();
    }

    @Transactional
    public void update(BoardUpdateRequestDto requestDto) {
        Board findboard = boardRepository.findById(requestDto.getId()).orElseThrow(
                () -> new IllegalArgumentException("Not Existing Board")
        );

        findboard.updateBoard(findboard.getTitle(), findboard.getContent());
    }

    @Transactional
    public void delete(long boardId) {
        boardRepository.deleteById(boardId);
    }
}
