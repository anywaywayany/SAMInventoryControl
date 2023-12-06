package com.samic.samic.services;

import com.samic.samic.data.entity.Producer;
import com.samic.samic.data.service.RepositoryProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ServiceProducer{
    @Autowired
    private RepositoryProducer repositoryProducer;


    public Producer saveProducerByObject(Producer producer){
        return repositoryProducer.save(producer);
    }

    public Producer findProducerById(Long id){
        return repositoryProducer.findById(id).get();
    }

    public void deleteProducerById(Long id){
        repositoryProducer.deleteById(id);
    }

    public void deleteByObject(Producer producer){
        repositoryProducer.delete(producer);
    }

    public boolean doesObjectExistById(Long id){
        return repositoryProducer.existsById(id);
    }

    public Stream<Producer> findAll(){
        return repositoryProducer.findAll().stream();
    }

}
