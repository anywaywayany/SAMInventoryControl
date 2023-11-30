package com.samic.samic.services;

import com.samic.samic.data.entity.Supply;
import com.samic.samic.data.persistence.RepositorySupply;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class ServiceSupply{

    @Autowired
    private RepositorySupply repositorySupply;

    public Supply saveSupplyByObject(Supply supply){
        return repositorySupply.save(supply);
    }

    public Supply findSupplyByID(Long id){
        return repositorySupply.findById(id).get();
    }


    public void deleteSupplyById(Long id){
        repositorySupply.deleteById(id);
    }

    public void deleteByObject(Supply supply){
        repositorySupply.delete(supply);
    }

    public boolean doesObjectExistById(Long id){
        return repositorySupply.existsById(id);
    }
}
