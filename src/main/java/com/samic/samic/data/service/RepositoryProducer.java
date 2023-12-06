package com.samic.samic.data.service;

import com.samic.samic.data.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryProducer extends JpaRepository<Producer, Long>{
}
