package com.samic.samic.data.repositories;

import com.samic.samic.data.entity.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorySupply extends JpaRepository<Supply, Long>{

    Optional<Supply> findSupplyByAmount (Integer amount);

}
