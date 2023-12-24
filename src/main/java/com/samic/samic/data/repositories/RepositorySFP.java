package com.samic.samic.data.repositories;

import com.samic.samic.data.entity.SFP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositorySFP extends JpaRepository<SFP, Long>{
    Optional<SFP> findSFPBySerialnumber(String name);
}
