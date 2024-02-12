package com.samic.samic.services;

import com.samic.samic.data.entity.Status;
import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.data.entity.StorageObjectHistory;
import com.samic.samic.data.repositories.RepositoryStorageObjectHistory;
import com.samic.samic.exceptions.SamicException;
import com.samic.samic.exceptions.StorageObjectHistoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class ServiceStorageObjectHistory{

    @Autowired
    private final RepositoryStorageObjectHistory repositoryStorageObjectHistory;


    @Transactional
    public StorageObjectHistory saveStorageObjectHistoryByObject(StorageObjectHistory storageObjectHistory){
        if(storageObjectHistory != null){
            if(storageObjectHistory.getId() != null){
                if(doesObjectExistById(storageObjectHistory.getId())){
                    StorageObjectHistory objectById = findStorageObjectHistoryByID(storageObjectHistory.getId());
                    if(objectById != null){
                        if(objectById.getId().equals(storageObjectHistory.getId())){
                            objectById = storageObjectHistory;
                            return repositoryStorageObjectHistory.save(objectById);
                        }else{
                            throw new StorageObjectHistoryException("StorageObjectHistory with id1: '%s' and id2: '%s' does not match. Some error occoured while fetch!!".formatted(objectById.getId(), storageObjectHistory.getId()));
                        }
                    }else{
                        throw new StorageObjectHistoryException("StorageObjectHistory with id: '%s' does not exist in DB".formatted(storageObjectHistory.getId()));
                    }
                }else{
                    throw new StorageObjectHistoryException("StorageObjectHistory with id: '%s' does not exist in DB but does have a id: ".formatted(storageObjectHistory.getId()));
                }
            }else{
                StorageObjectHistory saved = repositoryStorageObjectHistory.save(storageObjectHistory);
                return saved;
            }
        }else{
            throw new StorageObjectHistoryException("StorageObjectHistory is null!");
        }
    }


    //t
    @Transactional
    public StorageObjectHistory findStorageObjectHistoryByID(Long id){
        if(id != null){
            if(repositoryStorageObjectHistory.findById(id).isPresent()){
                return repositoryStorageObjectHistory.findById(id).get();
            }else{
                throw new StorageObjectHistoryException("Could not find StorageObjectHistory with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new StorageObjectHistoryException("Given id is null!");
        }
    }

    //t
    @Transactional
    public Optional<StorageObjectHistory> findStorageObjectHistoryByIDOptional(Long id){
        if(id != null){
            if(repositoryStorageObjectHistory.findById(id).isPresent()){
                return repositoryStorageObjectHistory.findById(id);
            }else{
                throw new StorageObjectHistoryException("Could not find StorageObjectHistory with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new StorageObjectHistoryException("Given id is null!");
        }
    }

    //t
    public void deleteStorageObjectHistoryById(Long id){
        if(id != null){
            if(!repositoryStorageObjectHistory.findAll().isEmpty()){
                repositoryStorageObjectHistory.deleteById(id);
            }else{
                throw new StorageObjectHistoryException("StorageObjectHistory DB is empty!");
            }
        }else{
            throw new StorageObjectHistoryException("Given id is null!");
        }
    }

    //t
    public void deleteByObject(StorageObjectHistory storageObjectHistory){
        if(storageObjectHistory != null){
            if(!repositoryStorageObjectHistory.findAll().isEmpty()){
                repositoryStorageObjectHistory.delete(storageObjectHistory);
            }else{
                throw new StorageObjectHistoryException("StorageObjectHistory DB is empty!");
            }
        }else{
            throw new StorageObjectHistoryException("Given StorageObjectHistory is null!");
        }
    }

    //t
    public boolean doesObjectExistById(Long id){
        if(id != null){
            return repositoryStorageObjectHistory.existsById(id);
        }else{
            throw new StorageObjectHistoryException("Given id is null!");
        }
    }


    //t
    @Transactional
    public Optional<StorageObjectHistory> findStorageObjectHistoryByStatusOptional(Status status){
        if(status != null){
            if(repositoryStorageObjectHistory.findStorageObjectHistoryByStatus(status).isPresent()){
                return repositoryStorageObjectHistory.findStorageObjectHistoryByStatus(status);
            }else{
                throw new StorageObjectHistoryException("Could not find StorageObjectHistory with status: '%s' in DB".formatted(status));
            }
        }else{
            throw new StorageObjectHistoryException("Given status is null!");
        }
    }

    @Transactional
    public Stream<StorageObjectHistory> findALLStorageObjectHistoryByStatusOptional(Status status){
        if(status != null){
            if(repositoryStorageObjectHistory.findStorageObjectHistoriesByStatus(status).findAny().isPresent()){
                return repositoryStorageObjectHistory.findStorageObjectHistoriesByStatus(status);
            }else{
                throw new StorageObjectHistoryException("Could not find StorageObjectHistories with status: '%s' in DB".formatted(status));
            }
        }else{
            throw new StorageObjectHistoryException("Given status is null!");
        }
    }

    @Transactional
    public Optional<StorageObjectHistory> findStorageObjectHistoryByStorageObjectOptional(StorageObject storageObject){
        if(storageObject != null){
            if(repositoryStorageObjectHistory.findStorageObjectHistoryByStorageObject(storageObject).isPresent()){
                return repositoryStorageObjectHistory.findStorageObjectHistoryByStorageObject(storageObject);
            }else{
                throw new StorageObjectHistoryException("Could not find StorageObjectHistories with storageObject: '%s' in DB".formatted(storageObject));
            }
        }else{
            throw new StorageObjectHistoryException("Given storageObject is null!");
        }
    }

    @Transactional
    public Stream<StorageObjectHistory> findALLStorageObjectHistoriesByLocalDateTimeOptional(LocalDateTime localDateTime){
        if(localDateTime != null){
            if(repositoryStorageObjectHistory.findStorageObjectHistoriesByInsertDateTime(localDateTime).findAny().isPresent()){
                return repositoryStorageObjectHistory.findStorageObjectHistoriesByInsertDateTime(localDateTime);
            }else{
                throw new StorageObjectHistoryException("Could not find StorageObjectHistories with Date: '%s' in DB".formatted(localDateTime));
            }
        }else{
            throw new StorageObjectHistoryException("Given Date is null!");
        }
    }

    @Transactional
    public Stream<StorageObjectHistory> findALLStorageObjectsHistoriesByLocalDateTimeAFTEROptional(LocalDateTime localDateTime){
        if(localDateTime != null){
            if(repositoryStorageObjectHistory.findStorageObjectHistoryByInsertDateTimeAfter(localDateTime).findAny().isPresent()){
                return repositoryStorageObjectHistory.findStorageObjectHistoryByInsertDateTimeAfter(localDateTime);
            }else{
                throw new StorageObjectHistoryException("Could not find StorageObjectHistories with Date: '%s' in DB".formatted(localDateTime));
            }
        }else{
            throw new StorageObjectHistoryException("Given Date is null!");
        }
    }



    @Transactional
    public Stream<StorageObjectHistory> findAll(){
            return repositoryStorageObjectHistory.findAll().stream();
    }
}


