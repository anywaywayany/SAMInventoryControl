package com.samic.samic.services;

import com.samic.samic.data.entity.Storage;
import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.data.entity.Supply;
import com.samic.samic.data.repositories.RepositorySupply;
import com.samic.samic.exceptions.SamicException;
import com.samic.samic.exceptions.SupplyException;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class ServiceSupply{

    @Autowired
    private RepositorySupply repositorySupply;

    @Transactional
    public Supply saveSupplyByObject(Supply supply){
        if(supply != null){
            if(supply.getId() != null){
                if(doesObjectExistById(supply.getId())){
                    Supply objectById = findSupplyByID(supply.getId());
                    if(objectById != null){
                        if(objectById.getId().equals(supply.getId())){
                            objectById = supply;
                            return repositorySupply.save(objectById);
                        }else{
                            throw new SupplyException("Supply with id1: '%s' and id2: '%s' does not match. Some error occoured while fetch!!".formatted(objectById.getId(), supply.getId()));
                        }
                    }else{
                        throw new SupplyException("Supply with id: '%s' does not exist in DB".formatted(supply.getId()));
                    }
                }else{
                    throw new SupplyException("Supply with id: '%s' does not exist in DB but does have a id: ".formatted(supply.getId()));
                }
            }else{
                Supply saved = repositorySupply.save(supply);
                return saved;
            }
        }else{
            throw new SupplyException("Supply is null!");
        }
    }

    @Transactional
    public Supply findSupplyByID(Long id){
        if(id != null){
            if(repositorySupply.findById(id).isPresent()){
                return repositorySupply.findById(id).get();
            }else{
                throw new SupplyException("Could not find Supply with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new SupplyException("Given id is null!");
        }

    }

    @Transactional
    public Optional<Supply> findSupplyByIDOptional(Long id){
        if(id != null){
            if(repositorySupply.findById(id).isPresent()){
                return repositorySupply.findById(id);
            }else{
                throw new SupplyException("Could not find Supply with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new SupplyException("Given id is null!");
        }
    }

    public void deleteSupplyById(Long id){
        if(id != null){
            if(!repositorySupply.findAll().isEmpty()){
                repositorySupply.deleteById(id);
            }else{
                throw new SupplyException("Supply DB is empty!");
            }
        }else{
            throw new SupplyException("Given id is null!");
        }
    }

    public void deleteByObject(Supply supply){
        if(supply != null){
            if(!repositorySupply.findAll().isEmpty()){
                repositorySupply.delete(supply);
            }else{
                throw new SupplyException("Supply DB is empty!");
            }
        }else{
            throw new SupplyException("Given Supply is null!");
        }
    }

    public boolean doesObjectExistById(Long id){
        if(id != null){
            return repositorySupply.existsById(id);
        }else{
            throw new SupplyException("Given id is null!");
        }
    }

    @Transactional
    public Optional<Supply> findSupplyByAmountOptional(Integer amount){
        if(amount != null){
            if(repositorySupply.findSupplyByAmount(amount).isPresent()){
                return repositorySupply.findSupplyByAmount(amount);
            }else{
                throw new SupplyException("Could not find Supply with name: '%s' in DB".formatted(amount));
            }
        }else{
            throw new SupplyException("Given name is null!");
        }
    }

    @Transactional
    public Stream<Supply> findAll(){
            return repositorySupply.findAll().stream();
    }

    public Long count(){
        return repositorySupply.count();
    }


}
