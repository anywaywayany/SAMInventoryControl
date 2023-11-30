package com.samic.samic.data.persistence;

import com.samic.samic.data.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryStorage extends JpaRepository<Storage, Long>{

     Storage findStorageByName(String name);
}
