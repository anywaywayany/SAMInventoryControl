package com.samic.samic.services;

import com.samic.samic.data.entity.Storage;
import com.samic.samic.data.foundation.Guard;
import com.samic.samic.data.repositories.RepositoryStorage;
import com.samic.samic.exceptions.SamicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.spi.ToolProvider.findFirst;
@Log4j2
@Service
@RequiredArgsConstructor
public class ServiceStorage{

    @Autowired
    private final RepositoryStorage repositoryStorage;


    //t
    public Storage saveStorageByObject(Storage storage){
        if(storage != null){
            if(storage.getId() != null){
                if(repositoryStorage.existsById(storage.getId())){
                    log.debug("Storage with id: '%s', name: '%s' already exists in DB".formatted(storage.getId(), storage.getName()));
                    throw new SamicException("Storage with id: '%s' already exists in DB".formatted(storage.getId()));
                }else{
                    return repositoryStorage.save(storage);
                }
            }else{
                return repositoryStorage.save(storage);
            }
        }else{
            throw new SamicException("Storage is null!");
        }



        /*else{
//            return repositoryStorage.save(storage);
            List<Storage> storagesList = repositoryStorage.findAll();
            boolean updated = false;
            Iterator<Storage> iter = storagesList.iterator();
            while(iter.hasNext()){
                Storage stg = iter.next();
                if(stg.getName().equals(storage.getName())
                        && stg.getAddress().getCity().equals(storage.getAddress().getCity())
                        && stg.getAddress().getStreet().equals(storage.getAddress().getStreet())
                        && stg.getAddress().getZipCode().equals(storage.getAddress().getZipCode())){
                    Long temId = stg.getId();

                    log.debug("Storage update");
                    storagesList.add(Math.toIntExact(temId), storage);
                    updated = true;
                    repositoryStorage.saveAll(storagesList);
                    log.debug("Storage updated");
                    if(updated){
                        return storage;

                    }
                }
            }
          }
        return repositoryStorage.save(storage);*/
    }


    //t
    public Storage findStorageByID(Long id){
        if(id != null){
            if(repositoryStorage.findById(id).isPresent()){
                return repositoryStorage.findById(id).get();
            }else{
                throw new SamicException("Could not find Storage with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    //t
    public Optional<Storage> findStorageByIDOptional(Long id){
        if(id != null){
            if(repositoryStorage.findById(id).isPresent()){
                return repositoryStorage.findById(id);
            }else{
                throw new SamicException("Could not find Storage with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    //t
    public void deleteStoragetById(Long id){
        if(id != null){
            if(!repositoryStorage.findAll().isEmpty()){
                repositoryStorage.deleteById(id);
            }else{
                throw new SamicException("Storage DB is empty!");
            }
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    //t
    public void deleteByObject(Storage storage){
        if(storage != null){
            if(!repositoryStorage.findAll().isEmpty()){
                repositoryStorage.delete(storage);
            }else{
                throw new SamicException("Storage DB is empty!");
            }
        }else{
            throw new SamicException("Given Storage is null!");
        }
    }

    //t
    public boolean doesObjectExistById(Long id){
        if(id != null){
            return repositoryStorage.existsById(id);
        }else{
            throw new SamicException("Given id is null!");
        }
    }

//    public Storage findStorageByName(String name){
//        if(!Guard.isNullBlankOrEmpty(name)){
//            return repositoryStorage.findStorageByName(name);
//        }else{
//            throw new SamicException("Given name is null!");
//        }
//    }

    //t
    public Optional<Storage> findStorageByNameOptional(String name){
        if(name != null){
            if(repositoryStorage.findStorageByName(name).isPresent()){
                return repositoryStorage.findStorageByName(name);
            }else{
                throw new SamicException("Could not find Storage with name: '%s' in DB".formatted(name));
            }
        }else{
            throw new SamicException("Given name is null!");
        }
    }

    public Stream<Storage> findAll(){
        if(repositoryStorage.findAll().isEmpty()){
            throw new SamicException("Storage list is empty!");
        }else{
            return repositoryStorage.findAll().stream();
        }
    }
}
