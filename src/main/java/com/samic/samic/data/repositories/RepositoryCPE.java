package com.samic.samic.data.repositories;

import com.samic.samic.data.entity.CPE;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryCPE extends JpaRepository<CPE, Long>{
    Optional<CPE> findCPEBySerialnumber(String name);

    Page<CPE> findCPESByProducerId(Long id, PageRequest request);
}
