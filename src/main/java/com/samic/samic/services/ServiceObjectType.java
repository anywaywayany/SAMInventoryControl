package com.samic.samic.services;

import com.samic.samic.data.entity.ObjectType;
import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.data.repositories.RepositoryObjectType;
import com.samic.samic.exceptions.ObjectTypeException;
import com.samic.samic.exceptions.SamicException;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class ServiceObjectType{

    @Autowired
    private RepositoryObjectType repositoryObjectType;

    @Transactional
    public ObjectType saveObjectTypeByObject(ObjectType objectType){
        if(objectType != null){
            if(objectType.getId() != null){
                if(doesObjectExistById(objectType.getId())){
                    ObjectType objectById = findObjectTypeByID(objectType.getId());
                    if(objectById != null){
                        if(objectById.getId().equals(objectType.getId())){
                            objectById = objectType;
                            return repositoryObjectType.save(objectById);
                        }else{
                            throw new ObjectTypeException("ObjectType with id1: '%s' and id2: '%s' does not match. Some error occoured while fetch!!".formatted(objectById.getId(), objectType.getId()));
                        }
                    }else{
                        throw new ObjectTypeException("ObjectType with id: '%s' does not exist in DB".formatted(objectType.getId()));
                    }
                }else{
                    throw new ObjectTypeException("ObjectType with id: '%s' does not exist in DB but does have a id: ".formatted(objectType.getId()));
                }
            }else{
                ObjectType saved = repositoryObjectType.save(objectType);
                return saved;
            }
        }else{
            throw new ObjectTypeException("ObjectType is null!");
        }
    }


    @Transactional
    public ObjectType findObjectTypeByID(Long id){
        if(id != null){
            if(repositoryObjectType.findById(id).isPresent()){
                return repositoryObjectType.findById(id).get();
            }else{
                throw new ObjectTypeException("Could not find ObjectType with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new ObjectTypeException("Given id is null!");
        }
    }

    @Transactional
    public Optional<ObjectType> findObjectTypeByIDOptional(Long id){
        if(id != null){
            if(repositoryObjectType.findById(id).isPresent()){
                return repositoryObjectType.findById(id);
            }else{
                throw new ObjectTypeException("Could not find ObjectType with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new ObjectTypeException("Given id is null!");
        }
    }

    @Transactional
    public void deleteObjectTypeById(Long id){
        if(id != null){
            repositoryObjectType.deleteById(id);
        }else{
            throw new ObjectTypeException("Given id is null!");
        }

    }


    public void deleteByObject(ObjectType objectType){
        if(objectType != null){
            repositoryObjectType.delete(objectType);
        }else{
            throw new ObjectTypeException("Given objectType is null!");
        }
    }


    public boolean doesObjectExistById(Long id){
        if(id != null){
            return repositoryObjectType.existsById(id);
        }else{
            throw new ObjectTypeException("Given id is null!");
        }

    }

    @Transactional
    public Optional<ObjectType> findObjectTypeByNameOptional(String name){
        if(name != null){
            if(repositoryObjectType.findByName(name).isPresent()){
                return repositoryObjectType.findByName(name);
            }else{
                throw new ObjectTypeException("Could not find ObjectType with name: '%s' in DB".formatted(name));
            }
        }else{
            throw new ObjectTypeException("Given name is null!");
        }
    }

    @Transactional
    public Stream<ObjectType> findAll(){
        return repositoryObjectType.findAll().stream();
    }

    @Transactional
    public Page<ObjectType> findAll(Pageable pageable){
        return repositoryObjectType.findAll(pageable);
    }
}
