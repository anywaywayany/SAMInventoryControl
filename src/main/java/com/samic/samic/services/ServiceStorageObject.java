package com.samic.samic.services;

import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.data.persistence.RepositoryStorageObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceStorageObject{

    @Autowired
    private RepositoryStorageObject repositoryStorageObject;

    public ServiceStorageObject(@Qualifier("storageObject") RepositoryStorageObject repositoryStorageObject){
        this.repositoryStorageObject = repositoryStorageObject;
    }

    public StorageObject saveStorageObject(StorageObject storageObject){
        return repositoryStorageObject.save(storageObject);
    }

    public StorageObject findStorageObjectById(Long id){
        return repositoryStorageObject.findById(id).get();
    }

    public void deleteStorageObjectById(Long id){
        repositoryStorageObject.deleteById(id);
    }//Todo delete ByObject

    public void deleteByObject(StorageObject storageObject){
         repositoryStorageObject.delete(storageObject);
    }

    public boolean doesObjectExistById(Long id){
       return repositoryStorageObject.existsById(id);
    }
}
