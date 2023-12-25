package com.samic.samic.services;

import com.samic.samic.data.entity.Status;
import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.data.entity.StorageObjectHistory;
import com.samic.samic.data.repositories.RepositoryStorageObjectHistory;
import com.samic.samic.exceptions.SamicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Log4j2
public class ServiceStorageObjectHistory{

    @Autowired
    private final RepositoryStorageObjectHistory repositoryStorageObjectHistory;


    //t
    public StorageObjectHistory saveStorageObjectHistoryByObject(StorageObjectHistory storageObjectHistory){
        if(storageObjectHistory != null){
            if(storageObjectHistory.getId() != null){
                if(repositoryStorageObjectHistory.existsById(storageObjectHistory.getId())){
                    log.debug("StorageObjectHistory with id: '%s', storageObjectHistory: '%s' already exists in DB".formatted(storageObjectHistory.getId(), storageObjectHistory.getStorageObject()));
                    throw new SamicException("StorageObjectHistory with id: '%s' already exists in DB".formatted(storageObjectHistory.getId()));
                }else{
                    return repositoryStorageObjectHistory.save(storageObjectHistory);
                }
            }else{
                return repositoryStorageObjectHistory.save(storageObjectHistory);
            }
        }else{
            throw new SamicException("StorageObjectHistory is null!");
        }
    }


    //t
    public StorageObjectHistory findStorageObjectHistoryByID(Long id){
        if(id != null){
            if(repositoryStorageObjectHistory.findById(id).isPresent()){
                return repositoryStorageObjectHistory.findById(id).get();
            }else{
                throw new SamicException("Could not find StorageObjectHistory with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    //t
    public Optional<StorageObjectHistory> findStorageObjectHistoryByIDOptional(Long id){
        if(id != null){
            if(repositoryStorageObjectHistory.findById(id).isPresent()){
                return repositoryStorageObjectHistory.findById(id);
            }else{
                throw new SamicException("Could not find StorageObjectHistory with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    //t
    public void deleteStorageObjectHistoryById(Long id){
        if(id != null){
            if(!repositoryStorageObjectHistory.findAll().isEmpty()){
                repositoryStorageObjectHistory.deleteById(id);
            }else{
                throw new SamicException("StorageObjectHistory DB is empty!");
            }
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    //t
    public void deleteByObject(StorageObjectHistory storageObjectHistory){
        if(storageObjectHistory != null){
            if(!repositoryStorageObjectHistory.findAll().isEmpty()){
                repositoryStorageObjectHistory.delete(storageObjectHistory);
            }else{
                throw new SamicException("StorageObjectHistory DB is empty!");
            }
        }else{
            throw new SamicException("Given StorageObjectHistory is null!");
        }
    }

    //t
    public boolean doesObjectExistById(Long id){
        if(id != null){
            return repositoryStorageObjectHistory.existsById(id);
        }else{
            throw new SamicException("Given id is null!");
        }
    }


    //t
    public Optional<StorageObjectHistory> findStorageObjectHistoryByStatusOptional(Status status){
        if(status != null){
            if(repositoryStorageObjectHistory.findStorageObjectHistoryByStatus(status).isPresent()){
                return repositoryStorageObjectHistory.findStorageObjectHistoryByStatus(status);
            }else{
                throw new SamicException("Could not find StorageObjectHistory with status: '%s' in DB".formatted(status));
            }
        }else{
            throw new SamicException("Given status is null!");
        }
    }

    public Stream<StorageObjectHistory> findALLStorageObjectHistoryByStatusOptional(Status status){
        if(status != null){
            if(repositoryStorageObjectHistory.findStorageObjectHistoriesByStatus(status).findAny().isPresent()){
                return repositoryStorageObjectHistory.findStorageObjectHistoriesByStatus(status);
            }else{
                throw new SamicException("Could not find StorageObjectHistories with status: '%s' in DB".formatted(status));
            }
        }else{
            throw new SamicException("Given status is null!");
        }
    }

    public Optional<StorageObjectHistory> findStorageObjectHistoryByStorageObjectOptional(StorageObject storageObject){
        if(storageObject != null){
            if(repositoryStorageObjectHistory.findStorageObjectHistoryByStorageObject(storageObject).isPresent()){
                return repositoryStorageObjectHistory.findStorageObjectHistoryByStorageObject(storageObject);
            }else{
                throw new SamicException("Could not find StorageObjectHistories with storageObject: '%s' in DB".formatted(storageObject));
            }
        }else{
            throw new SamicException("Given storageObject is null!");
        }
    }
    public Stream<StorageObjectHistory> findALLStorageObjectHistoriesByLocalDateTimeOptional(LocalDateTime localDateTime){
        if(localDateTime != null){
            if(repositoryStorageObjectHistory.findStorageObjectHistoriesByInsertDateTime(localDateTime).findAny().isPresent()){
                return repositoryStorageObjectHistory.findStorageObjectHistoriesByInsertDateTime(localDateTime);
            }else{
                throw new SamicException("Could not find StorageObjectHistories with Date: '%s' in DB".formatted(localDateTime));
            }
        }else{
            throw new SamicException("Given Date is null!");
        }
    }

    public Stream<StorageObjectHistory> findALLStorageObjectsHistoriesByLocalDateTimeAFTEROptional(LocalDateTime localDateTime){
        if(localDateTime != null){
            if(repositoryStorageObjectHistory.findStorageObjectHistoryByInsertDateTimeAfter(localDateTime).findAny().isPresent()){
                return repositoryStorageObjectHistory.findStorageObjectHistoryByInsertDateTimeAfter(localDateTime);
            }else{
                throw new SamicException("Could not find StorageObjectHistories with Date: '%s' in DB".formatted(localDateTime));
            }
        }else{
            throw new SamicException("Given Date is null!");
        }
    }



    public Stream<StorageObjectHistory> findAll(){
        if(repositoryStorageObjectHistory.findAll().isEmpty()){
            throw new SamicException("StorageObjectHistory list is empty!");
        }else{
            return repositoryStorageObjectHistory.findAll().stream();
        }
    }
}


