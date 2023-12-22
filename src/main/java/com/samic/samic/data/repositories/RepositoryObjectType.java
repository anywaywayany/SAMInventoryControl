package com.samic.samic.data.service;

import com.samic.samic.data.entity.ObjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryObjectType extends JpaRepository<ObjectType, Long> {
}
