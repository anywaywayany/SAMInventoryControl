package com.samic.samic.services;

import com.samic.samic.data.entity.Customer;
import com.samic.samic.data.entity.ObjectType;
import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.data.entity.User;
import com.samic.samic.data.repositories.RepositoryStorageObject;
import com.samic.samic.exceptions.StorageObjectException;
import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
@ComponentScan(basePackages = "com.samic.samic.data.services")
public class ServiceStorageObject{

    @Autowired
    private final RepositoryStorageObject repositoryStorageObject;
    @Autowired
    private final EntityManagerFactory    emf;
//    private final Logger                  log = LoggerFactory.getLogger(this.getClass());
    //    public ServiceStorageObject(@Qualifier("storageObject") RepositoryStorageObject repositoryStorageObject){
    //        this.repositoryStorageObject = repositoryStorageObject;
    //    }

    @Transactional
    public StorageObject saveStorageObject(StorageObject storageObject){

        if(storageObject != null){
//            System.out.println("------------- 1 " + storageObject.getReservation().getReservedFrom());
            log.warn("saveStorageObject() | ######################## USER1 ########################, {}: ", storageObject.getStoredAtUser());

            log.debug("saveStorageObject() | StorageObject is not null, {}: ------ , {}", storageObject, storageObject.getStoredAtUser());
            if(storageObject.getId() != null){
//                System.out.println("------------- 2 " + storageObject.getReservation().getReservedFrom());
                log.warn("saveStorageObject() | ######################## USER2 ########################, {}: ", storageObject.getStoredAtUser());
                log.debug("saveStorageObject() | StorageObject id is not null, StorageObject ID: {}, \nstorageObject: {}", storageObject.getId(), storageObject);
                if(doesObjectExistById(storageObject.getId())){
//                    System.out.println("------------- 3 " + storageObject.getReservation().getReservedFrom());
                    log.warn("saveStorageObject() | ######################## USER3 ########################, {}: ", storageObject.getStoredAtUser());
                    log.debug("saveStorageObject() | StorageObject exists, StorageObject ID: {},\nstorageObject: {}", storageObject.getId(), storageObject);
                    StorageObject fetchedStorageObject = findStorageObjectById(storageObject.getId());
//                    System.out.println("------------- 4 " + fetchedStorageObject.getReservation().getReservedFrom());
                    log.warn("saveStorageObject() | ######################## USER4 ########################, {}: ", storageObject.getStoredAtUser());
                    log.debug("saveStorageObject() | StorageObject found with ID: {},\nFetched StorageObject{},", storageObject.getId(), fetchedStorageObject);
                    if(fetchedStorageObject != null){
//                        System.out.println("------------- 5 " + fetchedStorageObject.getReservation().getReservedFrom());
                        log.warn("saveStorageObject() | ######################## USER5 ########################i, {}: ", storageObject.getStoredAtUser());
                        log.debug("saveStorageObject() | in if statement, fetchedStorageObject: {},\nStorageObject: {}", fetchedStorageObject.getId(), fetchedStorageObject);
                        if(fetchedStorageObject.getId().equals(storageObject.getId())){
//                            System.out.println("------------- 6 " + fetchedStorageObject.getReservation().getReservedFrom());
                            log.warn("saveStorageObject() | ######################## USER6 ########################, {}: ", storageObject.getStoredAtUser());
                            log.debug("saveStorageObject() | fetched StorageObject equals given StorageObject, fetchedStorageObject: {},\nStorageObject: {}", fetchedStorageObject.getId(), storageObject);
                            fetchedStorageObject = storageObject;
                            log.debug("saveStorageObejct() | given StorageObject is set to fetched StorageObject, fetchedStorageObject: {},\n storageObject: {}", fetchedStorageObject, storageObject);
                            return repositoryStorageObject.save(fetchedStorageObject);
                        }else{
                            log.debug("saveStorageObject() | fetched StorageObject does not match with given StorageObject, fetchedStorageObject: {},\nStorageObject: {}", fetchedStorageObject, storageObject);
                            throw new StorageObjectException("StorageObject with id1: '%s' and id2: '%s' does not match. Some error occoured while fetch!!".formatted(fetchedStorageObject.getId(), storageObject.getId()));
                        }
                    }else{
                        log.debug("saveStorageObject() | fetched StorageObject is null, fetchedStorageObject: {}", fetchedStorageObject);
                        throw new StorageObjectException("StorageObject with id: '%s' does not exist in DB".formatted(storageObject.getId()));
                    }
                }else{
                    log.debug("saveStorageObject() | StorageObject does not exist in DB, ID: {},\nStorageObject: {}", storageObject.getId());
                    return repositoryStorageObject.save(storageObject);

                }
            }else{
                log.debug("saveStorageObject() | StorageObject id is null, Saving StorageObject: {}", storageObject); //TODO T3600
                StorageObject saved = repositoryStorageObject.save(storageObject);
                return saved;
            }
        }else{
            log.debug("saveStorageObject() | StorageObject is null");
            throw new StorageObjectException("StorageObject is null!");
        }
    }

    @Transactional
    public StorageObject findStorageObjectById(Long id){
        if(id != null){
            log.debug("findStorageObjectById() | id is not null, id: {}", id);
            if(repositoryStorageObject.findById(id).isPresent()){
                return repositoryStorageObject.findById(id).get();
            }else{
                throw new StorageObjectException("Could not find StorageObject with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new StorageObjectException("Given id is null!");
        }
    }

//    public StorageObject findStorageObjectByID(Long id){
//        StorageObject sto = new StorageObject();
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//
//        EntityGraph<?>            entityGraph = em.createEntityGraph("graph.StorageObjectStorage");
//        TypedQuery<StorageObject> query       = em.createQuery("SELECT a FROM StorageObject a", StorageObject.class).setHint("javax.persistence.fetchgraph", entityGraph);
//        List<StorageObject> resultList = query.getResultList();
//
//        em.getTransaction().commit();
//        em.close();
//
//        for(StorageObject so : resultList){
//            if(so.getId().equals(id)){
//                sto = so;
//            }
//        }
//
//        if(sto != null){
//            return sto;
//        }else{
//            throw new StorageObjectException("Could not find StorageObject with id: '%s' in DB".formatted(id));
//        }
//    }

    @Transactional
    public Optional<StorageObject> findStorageObjectByIDOptional(Long id){
        if(id != null){
            if(repositoryStorageObject.findById(id).isPresent()){
                return repositoryStorageObject.findById(id);
            }else{
                throw new StorageObjectException("Could not find StorageObject with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new StorageObjectException("Given id is null!");
        }
    }

    public void deleteStorageObjectById(Long id){
        if(id != null){
            if(!repositoryStorageObject.findAll().isEmpty()){
                repositoryStorageObject.deleteById(id);
            }else{
                throw new StorageObjectException("StorageObject DB is empty!");
            }
        }else{
            throw new StorageObjectException("StorageObject id is null!");
        }
    }

    public void deleteByObject(StorageObject storageObject){
        if(storageObject != null){
            if(!repositoryStorageObject.findAll().isEmpty()){
                repositoryStorageObject.delete(storageObject);
            }else{
                throw new StorageObjectException("StorageObject DB is empty!");
            }
        }else{
            throw new StorageObjectException("StorageObject is null!");
        }
    }

    public boolean doesObjectExistById(Long id){
        if(id != null){
            return repositoryStorageObject.existsById(id);
        }else{
            throw new StorageObjectException("StorageObject id is null!");
        }
    }

    @Transactional
    public Optional<StorageObject> findStorageByNameOptional(ObjectType objectType){
        if(objectType != null){
            if(repositoryStorageObject.findStorageObjectByObjectTypeName(objectType).isPresent()){
                return repositoryStorageObject.findStorageObjectByObjectTypeName(objectType);
            }else{
                throw new StorageObjectException("Could not find StorageObject with objectType: '%s' in DB".formatted(objectType.getName()));
            }
        }else{
            throw new StorageObjectException("Given name is null!");
        }
    }

    @Transactional
    public Stream<StorageObject> findAll(){
        if(repositoryStorageObject.findAll().isEmpty()){
            throw new StorageObjectException("StorageObject list is empty!");
        }else{
            return repositoryStorageObject.findAll().stream();
        }
    }

    @Transactional
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
            throw new StorageObjectException("StorageObject list is empty!");
        }
        if(freeStorageObjects.isEmpty()){
            throw new StorageObjectException("There are no Free StorageObjects in DB!");
        }
        return freeStorageObjects;
    }

    @Transactional
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
            throw new StorageObjectException("StorageObject list is empty!");
        }
        if(reservedStorageObjects.isEmpty()){
            throw new StorageObjectException("There are no Free StorageObjects in DB!");
        }
        return reservedStorageObjects;
    }


    @Transactional
    public void deleteAll(){
        if(repositoryStorageObject.count()>0){
            repositoryStorageObject.deleteAll();
        }else{
            throw new StorageObjectException("StorageObject DB is empty!");
        }
    }


    public Stream<StorageObject> findAllStorageObjectByUserId(Long id, PageRequest request){
        if(id != null){
            return repositoryStorageObject.findAllByStoredAtUser_Id(id, request).stream();
        }else{
            throw new StorageObjectException("Given id is null!");
        }
    }

    public Stream<StorageObject> findAllStorageObjectByUserIdStream(Long id){
        if(id != null){
            return repositoryStorageObject.findStorageObjectsByStoredAtUser_Id(id);
        }else{
            throw new StorageObjectException("Given id is null!");
        }
    }


    public StorageObject findStorageObjectByReservationID(Long id){
        if(id != null){
            return repositoryStorageObject.findStorageObjectByReservation_Id(id);
        }else{
            throw new StorageObjectException("Given id is null!");
        }
    }

    ////////////////////
    public Stream<StorageObject> findAllStoageObjectBySFPID(Long id, PageRequest request){
        if(id != null){
            return repositoryStorageObject.findStorageObjectsBySfp_Id(id, request).stream();
        }else{
            throw new StorageObjectException("Given id is null!");
        }
    }

    public Stream<StorageObject> findAllStoageObjectBySFPIDStream(Long id){
        if(id != null){
            return repositoryStorageObject.findStorageObjectsBySfp_Id(id);
        }else{
            throw new StorageObjectException("Given id is null!");
        }
    }

    public Stream<StorageObject> findAllStorageObjectByCPEID(Long id, PageRequest request){
        if(id != null){
            return repositoryStorageObject.findStorageObjectByCpe_Id(id, request).stream();
        }else{
            throw new StorageObjectException("Given id is null!");
        }
    }

    public Stream<StorageObject> findAllStorageObjectByCPEIDStream(Long id){
        if(id != null){
            return repositoryStorageObject.findStorageObjectByCpe_Id(id);
        }else{
            throw new StorageObjectException("Given id is null!");
        }
    }

    public Stream<StorageObject> findStorageObjectsByStorageId(Long id, PageRequest request){
        if(id != null){
            return repositoryStorageObject.findStorageObjectsByStorage_Id(id, request).stream();
        }else{
            throw new StorageObjectException("Given id is null!");
        }
    }

    public Stream<StorageObject> findStorageObjectsByStorageIdStream(Long id){
        if(id != null){
            return repositoryStorageObject.findStorageObjectsByStorage_Id(id);
        }else{
            throw new StorageObjectException("Given id is null!");
        }
    }

    public Stream<StorageObject> findStorageObjectsBySupplyId(Long id, PageRequest request){
        if(id != null){
            return repositoryStorageObject.findStorageObjectsBySupply_Id(id, request).stream();
        }else{
            throw new StorageObjectException("Given id is null!");
        }
    }

    public Stream<StorageObject> findStorageObjectsBySupplyIdStream(Long id){
        if(id != null){
            return repositoryStorageObject.findStorageObjectsBySupply_Id(id);
        }else{
            throw new StorageObjectException("Given id is null!");
        }
    }

    public Stream<StorageObject> findFreeStorageObjects(){
        Stream<StorageObject> freeStorageObjects = repositoryStorageObject.findAll().stream();
        return freeStorageObjects.filter(storageObject -> storageObject.getReservation() == null).filter(storageObject -> storageObject.getStoredAtUser() == null).filter(storageObject -> storageObject.getStoredAtCustomer() == null);

    }

    public Stream<StorageObject> findReservedStorageObjectsAsStream(){
        Stream<StorageObject> reservedStorageObjects = repositoryStorageObject.findAll().stream();
        return reservedStorageObjects.filter(storageObject -> storageObject.getReservation() != null).filter(storageObject -> storageObject.getStoredAtUser() == null);
    }

    public Stream<StorageObject> findStorageObjectByGivenUser(User user){
        Stream<StorageObject> storageObjectOnUser = repositoryStorageObject.findAll().stream();
        return storageObjectOnUser.filter(stor -> stor.getStoredAtUser() != null).filter(storageObject -> storageObject.getStoredAtUser().getId().equals(user.getId()));
    }

    public Optional<StorageObject> findStorageObjectByCustomer(Customer customer){
        return repositoryStorageObject.findStorageObjectByStoredAtCustomer(customer);
    }
}
