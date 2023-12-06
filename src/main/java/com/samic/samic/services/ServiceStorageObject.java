package com.samic.samic.services;

import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.data.foundation.Guard;
import com.samic.samic.data.service.RepositoryStorageObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

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
        Guard.ensureNotNull(repositoryStorageObject.findById(id).get());
        return repositoryStorageObject.findById(id).get();
    }

    public void deleteStorageObjectById(Long id){
        repositoryStorageObject.deleteById(id);
    }

    public void deleteByObject(StorageObject storageObject){
        repositoryStorageObject.delete(storageObject);
    }

    public boolean doesObjectExistById(Long id){
        return repositoryStorageObject.existsById(id);
    }

    public Stream<StorageObject> findAll(){
        return repositoryStorageObject.findAll().stream();
    }
}
