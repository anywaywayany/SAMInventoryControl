package com.samic.samic.data.persistence;

import com.samic.samic.data.entity.CPE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryCPE extends JpaRepository<CPE, Long>{
}
