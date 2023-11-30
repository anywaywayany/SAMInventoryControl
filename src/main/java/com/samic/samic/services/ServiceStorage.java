package com.samic.samic.services;

import com.samic.samic.data.entity.CPE;
import com.samic.samic.data.entity.Storage;
import com.samic.samic.data.persistence.RepositoryStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceStorage{

    @Autowired
    private RepositoryStorage repositoryStorage;


    public Storage saveStorageByObject(Storage storage){
        return repositoryStorage.save(storage);
    }

    public Storage findStorageyID(Long id){
        return repositoryStorage.findById(id).get();
    }


    public void deleteStoragetById(Long id){
        repositoryStorage.deleteById(id);
    }

    public void deleteByObject(Storage storage){
        repositoryStorage.delete(storage);
    }

    public boolean doesObjectExistById(Long id){
        return repositoryStorage.existsById(id);
    }

    public Storage findStorageByName(String name){
       return repositoryStorage.findStorageByName(name);
    }
}
