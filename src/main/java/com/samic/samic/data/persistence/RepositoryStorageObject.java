package com.samic.samic.data.persistence;

import com.samic.samic.data.entity.StorageObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryStorageObject extends JpaRepository<StorageObject, Long>{
}
