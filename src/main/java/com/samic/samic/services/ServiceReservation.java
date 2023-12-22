package com.samic.samic.services.serviceControl;

import com.samic.samic.data.entity.Producer;
import com.samic.samic.data.entity.Reservation;
import com.samic.samic.data.repositories.RepositoryProducer;
import com.samic.samic.data.repositories.RepositoryReservation;
import com.samic.samic.exceptions.SamicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Log4j2
public class ServiceReservation{

    @Autowired
    private RepositoryReservation repositoryReservation;


    public Reservation saveProducerByObject(Producer producer){
        if(producer != null){
            if(producer.getId() != null){
                if(repositoryProducer.existsById(producer.getId())){
                    log.debug("Producer with id: '%s', name: '%s' already exists in DB".formatted(producer.getId(), producer.getName()));
                    throw new SamicException("Producer with id: '%s' already exists in DB".formatted(producer.getId()));
                }else{
                    return repositoryProducer.save(producer);
                }
            }else{
                return repositoryProducer.save(producer);
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
        if(repositoryProducer.findAll().isEmpty()){
            throw new SamicException("Producer list is empty!");
        }else{
            return repositoryProducer.findAll().stream();
        }
    }

}
