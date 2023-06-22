package com.suzume.closet.service;

import com.suzume.closet.domain.Board;
import com.suzume.closet.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long join(Board board) {
        boardRepository.save(board);
        return board.getId();
    }

    public List<Board> findBoards() {
        return boardRepository.findAll();
    }

    public Board findById(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("Not Existing Board")
        );
    }

    @Transactional
    public void delete(Board board) {
        boardRepository.delete(board);
    }

    @Transactional
    public void update(Board board) {
        Board findboard = boardRepository.findById(board.getId()).orElseThrow(
                () -> new IllegalArgumentException("Not Existing Board")
        );
        findboard.updateBoard(findboard.getTitle(), findboard.getContent());
    }
}
