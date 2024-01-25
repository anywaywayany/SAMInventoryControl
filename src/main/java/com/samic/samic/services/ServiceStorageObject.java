package com.samic.samic.services;

import com.samic.samic.data.entity.*;
import com.samic.samic.data.repositories.RepositoryStorageObject;
import com.samic.samic.exceptions.SamicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
@ComponentScan(basePackages = "com.samic.samic.data.services")
public class ServiceStorageObject{

    @Autowired
    private final RepositoryStorageObject repositoryStorageObject;

//    public ServiceStorageObject(@Qualifier("storageObject") RepositoryStorageObject repositoryStorageObject){
//        this.repositoryStorageObject = repositoryStorageObject;
//    }

    @Transactional
    public StorageObject saveStorageObject(StorageObject storageObject){
        if(storageObject != null){
            if(storageObject.getId() != null){
                if(doesObjectExistById(storageObject.getId())){
                    StorageObject objectById = findStorageObjectById(storageObject.getId());
                    if(objectById != null){
                        if(objectById.getId().equals(storageObject.getId())){
                            objectById = storageObject;
                            return repositoryStorageObject.save(objectById);
                        }else{
                            throw new SamicException("StorageObject with id1: '%s' and id2: '%s' does not match. Some error occoured while fetch!!".formatted(objectById.getId(), storageObject.getId()));
                        }
                    }else{
                        throw new SamicException("StorageObject with id: '%s' does not exist in DB".formatted(storageObject.getId()));
                    }
                }else{
                    throw new SamicException("StorageObject with id: '%s' does not exist in DB but does have a id: ".formatted(storageObject.getId()));
                }
            }else{
                StorageObject saved = repositoryStorageObject.save(storageObject);
                return saved;
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
            List<StorageObject>     storageObjectList = repositoryStorageObject.findAll();
            Iterator<StorageObject> iter              = storageObjectList.iterator();
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
        List<StorageObject> reservedStorageObjects = repositoryStorageObject.findAll();
        //        List<StorageObject>     storageObjectList = repositoryStorageObject.findAll();
        if(!reservedStorageObjects.isEmpty()){
            Iterator<StorageObject> iter = reservedStorageObjects.iterator();
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
        if(repositoryStorageObject.count()>0){
            repositoryStorageObject.deleteAll();
        }else{
            throw new SamicException("StorageObject DB is empty!");
        }
    }


    public Stream<StorageObject> findAllStorageObjectByUserId(Long id, PageRequest request){
        if(id != null){
            return repositoryStorageObject.findAllByStoredAtUserId(id, request).stream();
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public Stream<StorageObject> findAllStorageObjectByUserIdStream(Long id){
        if(id != null){
            return repositoryStorageObject.findStorageObjectsByStoredAtUserId(id);
        }else{
            throw new SamicException("Given id is null!");
        }
    }


    public StorageObject findStorageObjectByReservationID(Long id){
        if(id != null){
            return repositoryStorageObject.findStorageObjectByReservationId(id);
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    ////////////////////
    public Stream<StorageObject> findAllStoageObjectBySFPID(Long id, PageRequest request){
        if(id != null){
            return repositoryStorageObject.findStorageObjectsBySfpId(id, request).stream();
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public Stream<StorageObject> findAllStoageObjectBySFPIDStream(Long id){
        if(id != null){
            return repositoryStorageObject.findStorageObjectsBySfpId(id);
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public Stream<StorageObject> findAllStorageObjectByCPEID(Long id, PageRequest request){
        if(id != null){
            return repositoryStorageObject.findStorageObjectByCpeId(id, request).stream();
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public Stream<StorageObject> findAllStorageObjectByCPEIDStream(Long id){
        if(id != null){
            return repositoryStorageObject.findStorageObjectByCpeId(id);
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public Stream<StorageObject> findStorageObjectsByStorageId(Long id, PageRequest request){
        if(id != null){
            return repositoryStorageObject.findStorageObjectsByStorageId(id, request).stream();
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public Stream<StorageObject> findStorageObjectsByStorageIdStream(Long id){
        if(id != null){
            return repositoryStorageObject.findStorageObjectsByStorageId(id);
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public Stream<StorageObject> findStorageObjectsBySupplyId(Long id, PageRequest request){
        if(id != null){
            return repositoryStorageObject.findStorageObjectsBySupplyId(id, request).stream();
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public Stream<StorageObject> findStorageObjectsBySupplyIdStream(Long id){
        if(id != null){
            return repositoryStorageObject.findStorageObjectsBySupplyId(id);
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public Stream<StorageObject> findFreeStorageObjects(){
        Stream<StorageObject> freeStorageObjects = repositoryStorageObject.findAll().stream();
        return freeStorageObjects.filter(storageObject -> storageObject.getReservation() == null)
                .filter(storageObject -> storageObject.getStoredAtUser() == null)
                       .filter(storageObject -> storageObject.getStoredAtCustomer() == null);

    }

    public Stream<StorageObject> findReservedStorageObjectsAsStream(){
        Stream<StorageObject> reservedStorageObjects = repositoryStorageObject.findAll().stream();
        return reservedStorageObjects.filter(storageObject -> storageObject.getReservation() != null)
                .filter(storageObject -> storageObject.getStoredAtUser() == null);
    }

    public Stream<StorageObject> findStorageObjectByGivenUser(User user){
        Stream<StorageObject> storageObjectOnUser = repositoryStorageObject.findAll().stream();
       return storageObjectOnUser.filter(storageObject -> storageObject.getStoredAtUser().equals(user));
    }

    public Optional<StorageObject> findStorageObjectByCustomer(Customer customer){
        return repositoryStorageObject.findStorageObjectByStoredAtCustomer(customer);
    }
}
