package com.suzume.crawling.repository;

import com.suzume.crawling.domain.Coordi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordiRepository extends JpaRepository<Coordi, Long> {
}
