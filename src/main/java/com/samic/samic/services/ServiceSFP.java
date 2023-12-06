package com.samic.samic.services;

import com.samic.samic.data.entity.SFP;
import com.samic.samic.data.service.RepositorySFP;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ServiceSFP{

    @Autowired
    private RepositorySFP repositorySFP;

    public SFP saveSFPByObject(SFP sfp){
        return repositorySFP.save(sfp);
    }

    public SFP findSFPById(Long id){
        return repositorySFP.findById(id).get();
    }

    public void deleteSFPById(Long id){
        repositorySFP.deleteById(id);
    }

    public void deleteByObject(SFP sfp){
        repositorySFP.delete(sfp);
    }

    public boolean doesObjectExistById(Long id){
        return repositorySFP.existsById(id);
    }

    public Stream<SFP> findAll(){
        return repositorySFP.findAll().stream();
    }

}
