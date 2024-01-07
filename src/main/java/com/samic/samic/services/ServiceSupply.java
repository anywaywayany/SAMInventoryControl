package com.samic.samic.services;

import com.samic.samic.data.entity.Storage;
import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.data.entity.Supply;
import com.samic.samic.data.repositories.RepositorySupply;
import com.samic.samic.exceptions.SamicException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
@Log4j2
public class ServiceSupply{

    @Autowired
    private RepositorySupply repositorySupply;

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
                            throw new SamicException("Supply with id1: '%s' and id2: '%s' does not match. Some error occoured while fetch!!".formatted(objectById.getId(), supply.getId()));
                        }
                    }else{
                        throw new SamicException("Supply with id: '%s' does not exist in DB".formatted(supply.getId()));
                    }
                }else{
                    throw new SamicException("Supply with id: '%s' does not exist in DB but does have a id: ".formatted(supply.getId()));
                }
            }else{
                Supply saved = repositorySupply.save(supply);
                return saved;
            }
        }else{
            throw new SamicException("Supply is null!");
        }
    }

    public Supply findSupplyByID(Long id){
        if(id != null){
            if(repositorySupply.findById(id).isPresent()){
                return repositorySupply.findById(id).get();
            }else{
                throw new SamicException("Could not find Supply with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new SamicException("Given id is null!");
        }

    }

    public Optional<Supply> findSupplyByIDOptional(Long id){
        if(id != null){
            if(repositorySupply.findById(id).isPresent()){
                return repositorySupply.findById(id);
            }else{
                throw new SamicException("Could not find Supply with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public void deleteSupplyById(Long id){
        if(id != null){
            if(!repositorySupply.findAll().isEmpty()){
                repositorySupply.deleteById(id);
            }else{
                throw new SamicException("Supply DB is empty!");
            }
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public void deleteByObject(Supply supply){
        if(supply != null){
            if(!repositorySupply.findAll().isEmpty()){
                repositorySupply.delete(supply);
            }else{
                throw new SamicException("Supply DB is empty!");
            }
        }else{
            throw new SamicException("Given Supply is null!");
        }
    }

    public boolean doesObjectExistById(Long id){
        if(id != null){
            return repositorySupply.existsById(id);
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public Optional<Supply> findSupplyByNameOptional(Integer amount){
        if(amount != null){
            if(repositorySupply.findSupplyByAmount(amount).isPresent()){
                return repositorySupply.findSupplyByAmount(amount);
            }else{
                throw new SamicException("Could not find Supply with name: '%s' in DB".formatted(amount));
            }
        }else{
            throw new SamicException("Given name is null!");
        }
    }

    public Stream<Supply> findAll(){
        if(repositorySupply.findAll().isEmpty()){
            throw new SamicException("Supply list is empty!");
        }else{
            return repositorySupply.findAll().stream();
        }
    }

}
