package com.suzume.crawling.repository;

import com.suzume.crawling.domain.Outer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OuterRepository extends JpaRepository<Outer, Long> {
}
