package com.samic.samic.services;

import com.samic.samic.data.entity.ObjectType;
import com.samic.samic.data.service.RepositoryObjectType;
import com.samic.samic.exceptions.SamicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@Log4j2
@RequiredArgsConstructor
public class ServiceObjectType{

    @Autowired
    private RepositoryObjectType repositoryObjectType;

    public ObjectType saveObjectTypeByObject(ObjectType objectType){
        try{
            return repositoryObjectType.save(objectType);
        }catch(SamicException e){
            throw new SamicException("ObjectType "+ objectType + " not valid!");
        }
    }

    public ObjectType findObjectTypeByID(Long id){
        return repositoryObjectType.findById(id).get();
    }


    public void deleteObjectTypeById(Long id){
        repositoryObjectType.deleteById(id);
    }

    public void deleteByObject(ObjectType objectType){
        repositoryObjectType.delete(objectType);
    }

    public boolean doesObjectExistById(Long id){
        return repositoryObjectType.existsById(id);
    }

    public Stream<ObjectType> findAll(){
        return repositoryObjectType.findAll().stream();
    }
}
