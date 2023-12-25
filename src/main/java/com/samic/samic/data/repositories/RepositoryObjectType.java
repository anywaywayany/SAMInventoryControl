package com.samic.samic.data.repositories;

import com.samic.samic.data.entity.ObjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryObjectType extends JpaRepository<ObjectType, Long> {
    Optional<ObjectType> findStorageByName(String name);
}
