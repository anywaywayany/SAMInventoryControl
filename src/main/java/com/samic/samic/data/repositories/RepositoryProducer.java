package com.samic.samic.data.repositories;

import com.samic.samic.data.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryProducer extends JpaRepository<Producer, Long>{
    Optional<Producer> findStorageByName(String name);

    Optional<Producer> findProducerBy(String name);
}
