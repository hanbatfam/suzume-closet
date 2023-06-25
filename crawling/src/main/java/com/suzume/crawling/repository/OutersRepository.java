package com.suzume.crawling.repository;

import com.suzume.crawling.domain.Outers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutersRepository extends JpaRepository<Outers, Long> {
}
