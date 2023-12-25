package com.samic.samic.data.repositories;

import com.samic.samic.data.entity.ObjectType;
import com.samic.samic.data.entity.Reservation;
import com.samic.samic.data.entity.StorageObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Qualifier("storageObject")
@Repository
public interface RepositoryStorageObject extends JpaRepository<StorageObject, Long>{


    Optional<StorageObject> findStorageObjectByObjectTypeName(ObjectType objectType);
//    Stream<StorageObject> findStorageObjectByReserved(Reservation.);
}
