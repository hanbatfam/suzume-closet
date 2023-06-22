package com.suzume.closet.repository.repository;

import com.suzume.closet.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//@RequiredArgsConstructor
public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findById(Long id);
}
