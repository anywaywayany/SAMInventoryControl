package com.samic.samic.data.repositories;

import com.samic.samic.data.entity.SFP;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface RepositorySFP extends JpaRepository<SFP, Long>{
    Optional<SFP> findSFPBySerialnumber(String name);

    Page<SFP> findAllByProducerId(Long id, Pageable pageable);

    Stream<SFP> findAllByProducerId(Long id);
}
