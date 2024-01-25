package com.samic.samic.services;

import com.samic.samic.data.entity.Producer;
import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.data.repositories.RepositoryProducer;
import com.samic.samic.exceptions.SamicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Stream;
@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class ServiceProducer{
    @Autowired
    private RepositoryProducer repositoryProducer;

    @Transactional
    public Producer saveProducerByObject(Producer producer){
        if(producer != null){
            if(producer.getId() != null){
                if(doesObjectExistById(producer.getId())){
                    Producer objectById = findProducerById(producer.getId());
                    if(objectById != null){
                        if(objectById.getId().equals(producer.getId())){
                            objectById = producer;
                            return repositoryProducer.save(objectById);
                        }else{
                            throw new SamicException("Producer with id1: '%s' and id2: '%s' does not match. Some error occoured while fetch!!".formatted(objectById.getId(), producer.getId()));
                        }
                    }else{
                        throw new SamicException("Producer with id: '%s' does not exist in DB".formatted(producer.getId()));
                    }
                }else{
                    throw new SamicException("Producer with id: '%s' does not exist in DB but does have a id: ".formatted(producer.getId()));
                }
            }else{
                Producer saved = repositoryProducer.save(producer);
                return saved;
            }
        }else{
            throw new SamicException("Producer is null!");
        }
    }

    public Producer findProducerById(Long id){
        if(id != null){
            if(repositoryProducer.findById(id).isPresent()){
                return repositoryProducer.findById(id).get();
            }else{
                throw new SamicException("Could not find Producer with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public Optional<Producer> findProducerByIDOptional(Long id){
        if(id != null){
            if(repositoryProducer.findById(id).isPresent()){
                return repositoryProducer.findById(id);
            }else{
                throw new SamicException("Could not find Producer with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public void deleteProducerById(Long id){
        if(id != null){
            repositoryProducer.deleteById(id);
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public void deleteByObject(Producer producer){
        if(producer != null){
            repositoryProducer.delete(producer);
        }else{
            throw new SamicException("Given Producer is null!");
        }
    }

    public boolean doesObjectExistById(Long id){
        if(id != null){
            return repositoryProducer.existsById(id);
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public Optional<Producer> findProducerByNameOptional(String name){
        if(name != null){
            if(repositoryProducer.findStorageByName(name).isPresent()){
                return repositoryProducer.findStorageByName(name);
            }else{
                throw new SamicException("Could not find Producer with name: '%s' in DB".formatted(name));
            }
        }else{
            throw new SamicException("Given name is null!");
        }
    }

    public Stream<Producer> findAll(){
            return repositoryProducer.findAll().stream();
    }

}
