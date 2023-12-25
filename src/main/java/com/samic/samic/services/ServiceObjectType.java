package com.samic.samic.services;

import com.samic.samic.data.entity.ObjectType;
import com.samic.samic.data.repositories.RepositoryObjectType;
import com.samic.samic.exceptions.SamicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@Log4j2
@RequiredArgsConstructor
public class ServiceObjectType{

    @Autowired
    private RepositoryObjectType repositoryObjectType;

    public ObjectType saveObjectTypeByObject(ObjectType objectType){
        if(objectType != null){
            if(objectType.getId() != null){
                if(repositoryObjectType.existsById(objectType.getId())){
                    log.debug("ObjectType with id: '%s', name: '%s' already exists in DB".formatted(objectType.getId(), objectType.getName()));
                    throw new SamicException("ObjectType with id: '%s' already exists in DB".formatted(objectType.getId()));
                }else{
                    return repositoryObjectType.save(objectType);
                }
            }else{
                return repositoryObjectType.save(objectType);
            }
        }else{
            throw new SamicException("ObjectType is null!");
        }
    }

    public ObjectType findObjectTypeByID(Long id){
        if(id != null){
            if(repositoryObjectType.findById(id).isPresent()){
                return repositoryObjectType.findById(id).get();
            }else{
                throw new SamicException("Could not find ObjectType with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public Optional<ObjectType> findObjectTypeByIDOptional(Long id){
        if(id != null){
            if(repositoryObjectType.findById(id).isPresent()){
                return repositoryObjectType.findById(id);
            }else{
                throw new SamicException("Could not find ObjectType with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public void deleteObjectTypeById(Long id){
        if(id != null){
            repositoryObjectType.deleteById(id);
        }else{
            throw new SamicException("Given id is null!");
        }

    }

    public void deleteByObject(ObjectType objectType){
        if(objectType != null){
            repositoryObjectType.delete(objectType);
        }else{
            throw new SamicException("Given objectType is null!");
        }
    }

    public boolean doesObjectExistById(Long id){
        if(id != null){
            return repositoryObjectType.existsById(id);
        }else{
            throw new SamicException("Given id is null!");
        }

    }

    public Optional<ObjectType> findObjectTypeByNameOptional(String name){
        if(name != null){
            if(repositoryObjectType.findStorageByName(name).isPresent()){
                return repositoryObjectType.findStorageByName(name);
            }else{
                throw new SamicException("Could not find ObjectType with name: '%s' in DB".formatted(name));
            }
        }else{
            throw new SamicException("Given name is null!");
        }
    }

    public Stream<ObjectType> findAll(){
        if(repositoryObjectType.findAll().isEmpty()){
            throw new SamicException("ObjectType list is empty!");
        }else{
            return repositoryObjectType.findAll().stream();
        }

    }
}
