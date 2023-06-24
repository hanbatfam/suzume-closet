package com.suzume.crawling.repository;

import com.suzume.crawling.domain.Pants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PantsRepository extends JpaRepository<Pants, Long> {
}
