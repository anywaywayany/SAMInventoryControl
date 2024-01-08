package com.samic.samic.data.repositories;

import com.samic.samic.data.entity.Customer;
import com.samic.samic.data.entity.ObjectType;
import com.samic.samic.data.entity.Reservation;
import com.samic.samic.data.entity.StorageObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

@Qualifier("storageObject")
@Repository
public interface RepositoryStorageObject extends JpaRepository<StorageObject, Long>{


    Optional<StorageObject> findStorageObjectByObjectTypeName(ObjectType objectType);

    Page<StorageObject> findAllByStoredAtUserId(Long id, Pageable pageable);

    Stream<StorageObject> findStorageObjectsByStoredAtUserId(Long id);

    StorageObject findStorageObjectByReservationId(Long id);
    Page<StorageObject> findStorageObjectsBySfpId (Long id, PageRequest request);
    Stream<StorageObject> findStorageObjectsBySfpId (Long id);


    //    Stream<StorageObject> findStorageObjectByReserved(Reservation.);

    Page<StorageObject> findStorageObjectsByStorageId(Long id, PageRequest request);

    Stream<StorageObject> findStorageObjectsByStorageId(Long id);

    Page<StorageObject> findStorageObjectsBySupplyId(Long id, PageRequest request);

    Stream<StorageObject> findStorageObjectsBySupplyId(Long id);

    Page<StorageObject> findStorageObjectByCpeId(Long id, PageRequest request);

    Stream<StorageObject> findStorageObjectByCpeId(Long id);

    Optional<StorageObject> findStorageObjectByStoredAtCustomer(Customer customer);
}
