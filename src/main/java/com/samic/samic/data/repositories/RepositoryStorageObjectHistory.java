package com.samic.samic.data.repositories;

import com.samic.samic.data.entity.Status;
import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.data.entity.StorageObjectHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface RepositoryStorageObjectHistory extends JpaRepository<StorageObjectHistory, Long>{
    Optional<StorageObjectHistory> findStorageObjectHistoryByStatus(Status status);

    Stream<StorageObjectHistory> findStorageObjectHistoriesByStatus(Status status);

    Optional<StorageObjectHistory> findStorageObjectHistoryByStorageObject(StorageObject storageObject);

    Stream<StorageObjectHistory> findStorageObjectHistoriesByInsertDateTime(LocalDateTime localDateTime);

    Stream<StorageObjectHistory> findStorageObjectHistoryByInsertDateTimeAfter(LocalDateTime localDateTime);
}
