package com.samic.samic.data.service;

import com.samic.samic.data.entity.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorySupply extends JpaRepository<Supply, Long>{
}
