package com.samic.samic.services;

import com.samic.samic.data.entity.ObjectType;
import com.samic.samic.data.entity.Reservation;
import com.samic.samic.data.entity.Storage;
import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.data.repositories.RepositoryStorageObject;
import com.samic.samic.exceptions.SamicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Log4j2
public class ServiceStorageObject{

    @Autowired
    private RepositoryStorageObject repositoryStorageObject;

    public ServiceStorageObject(@Qualifier("storageObject") RepositoryStorageObject repositoryStorageObject){
        this.repositoryStorageObject = repositoryStorageObject;
    }

    public StorageObject saveStorageObject(StorageObject storageObject){
        if(storageObject != null){
            if(storageObject.getId() != null){
                if(repositoryStorageObject.existsById(storageObject.getId())){
                    log.debug("StorageObject with id: '%s', name: '%s' already exists in DB".formatted(storageObject.getId(), storageObject.getObjectTypeName().getName()));
                    throw new SamicException("StorageObject with id: '%s' already exists in DB".formatted(storageObject.getId()));
                }else{
                    return repositoryStorageObject.save(storageObject);
                }
            }else{
                return repositoryStorageObject.save(storageObject);
            }
        }else{
            throw new SamicException("StorageObject is null!");
        }
    }

    public StorageObject findStorageObjectById(Long id){
        if(id != null){
            if(repositoryStorageObject.findById(id).isPresent()){
                return repositoryStorageObject.findById(id).get();
            }else{
                throw new SamicException("Could not find StorageObject with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public Optional<StorageObject> findStorageObjectByIDOptional(Long id){
        if(id != null){
            if(repositoryStorageObject.findById(id).isPresent()){
                return repositoryStorageObject.findById(id);
            }else{
                throw new SamicException("Could not find StorageObject with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public void deleteStorageObjectById(Long id){
        if(id != null){
            if(!repositoryStorageObject.findAll().isEmpty()){
                repositoryStorageObject.deleteById(id);
            }else{
                throw new SamicException("StorageObject DB is empty!");
            }
        }else{
            throw new SamicException("StorageObject id is null!");
        }
    }

    public void deleteByObject(StorageObject storageObject){
        if(storageObject != null){
            if(!repositoryStorageObject.findAll().isEmpty()){
                repositoryStorageObject.delete(storageObject);
            }else{
                throw new SamicException("StorageObject DB is empty!");
            }
        }else{
            throw new SamicException("StorageObject is null!");
        }
    }

    public boolean doesObjectExistById(Long id){
        if(id != null){
            return repositoryStorageObject.existsById(id);
        }else{
            throw new SamicException("StorageObject id is null!");
        }
    }

    public Optional<StorageObject> findStorageByNameOptional(ObjectType objectType){
        if(objectType != null){
            if(repositoryStorageObject.findStorageObjectByObjectTypeName(objectType).isPresent()){
                return repositoryStorageObject.findStorageObjectByObjectTypeName(objectType);
            }else{
                throw new SamicException("Could not find StorageObject with objectType: '%s' in DB".formatted(objectType.getName()));
            }
        }else{
            throw new SamicException("Given name is null!");
        }
    }

    public Stream<StorageObject> findAll(){
        if(repositoryStorageObject.findAll().isEmpty()){
            throw new SamicException("StorageObject list is empty!");
        }else{
            return repositoryStorageObject.findAll().stream();
        }
    }

    public List<StorageObject> findNotReservedStorageObjects(){
        List<StorageObject> freeStorageObjects = new ArrayList<>();
        if(!repositoryStorageObject.findAll().isEmpty()){
            List<StorageObject> storageObjectList = repositoryStorageObject.findAll();
            Iterator<StorageObject> iter = storageObjectList.iterator();
            while(iter.hasNext()){
                StorageObject TempstorageObject = iter.next();
                if(TempstorageObject.getReservation() == null){
                    freeStorageObjects.add(TempstorageObject);
                }
            }
        }else{
            throw new SamicException("StorageObject list is empty!");
        }
        if(freeStorageObjects.isEmpty()){
            throw new SamicException("There are no Free StorageObjects in DB!");
        }
            return freeStorageObjects;
    }

    public List<StorageObject> findReservedStorageObjects(){
        List<StorageObject> reservedStorageObjects = new ArrayList<>();
        if(!repositoryStorageObject.findAll().isEmpty()){
            List<StorageObject> storageObjectList = repositoryStorageObject.findAll();
            Iterator<StorageObject> iter = storageObjectList.iterator();
            while(iter.hasNext()){
                StorageObject TempstorageObject = iter.next();
                if(TempstorageObject.getReservation() != null){
                    reservedStorageObjects.add(TempstorageObject);
                }
            }
        }else{
            throw new SamicException("StorageObject list is empty!");
        }
        if(reservedStorageObjects.isEmpty()){
            throw new SamicException("There are no Free StorageObjects in DB!");
        }
            return reservedStorageObjects;
    }


    public void deleteAll(){
            if(!repositoryStorageObject.findAll().isEmpty()){
                  repositoryStorageObject.deleteAll();
            }else{
                  throw new SamicException("StorageObject DB is empty!");
            }
    }
}
