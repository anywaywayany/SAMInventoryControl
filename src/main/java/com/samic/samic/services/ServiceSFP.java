package com.samic.samic.services;

import com.samic.samic.data.entity.CPE;
import com.samic.samic.data.entity.SFP;
import com.samic.samic.data.entity.Storage;
import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.data.repositories.RepositorySFP;
import com.samic.samic.exceptions.SamicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Log4j2
public class ServiceSFP{

    @Autowired
    private RepositorySFP repositorySFP;

    public SFP saveSFPByObject(SFP sfp){
        if(sfp != null){
            if(sfp.getId() != null){
                if(doesObjectExistById(sfp.getId())){
                    SFP objectById = findSFPById(sfp.getId());
                    if(objectById != null){
                        if(objectById.getId().equals(sfp.getId())){
                            objectById = sfp;
                            return repositorySFP.save(objectById);
                        }else{
                            throw new SamicException("SFP with id1: '%s' and id2: '%s' does not match. Some error occoured while fetch!!".formatted(objectById.getId(), sfp.getId()));
                        }
                    }else{
                        throw new SamicException("SFP with id: '%s' does not exist in DB".formatted(sfp.getId()));
                    }
                }else{
                    throw new SamicException("SFP with id: '%s' does not exist in DB but does have a id: ".formatted(sfp.getId()));
                }
            }else{
                SFP saved = repositorySFP.save(sfp);
                return saved;
            }
        }else{
            throw new SamicException("SFP is null!");
        }
    }

    public SFP findSFPById(Long id){
        if(id != null){
            if(repositorySFP.findById(id).isPresent()){
                return repositorySFP.findById(id).get();
            }else{
                throw new SamicException("Could not find SFP with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public Optional<SFP> findSFPByIDOptional(Long id){
        if(id != null){
            if(repositorySFP.findById(id).isPresent()){
                return repositorySFP.findById(id);
            }else{
                throw new SamicException("Could not find SFP with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public void deleteSFPById(Long id){
        if(id != null){
            repositorySFP.deleteById(id);
        }else{
            throw new SamicException("Given id is null!");
        }

    }

    public void deleteByObject(SFP sfp){
        if(sfp != null){
            repositorySFP.delete(sfp);
        }else{
            throw new SamicException("Given sfp is null!");
        }

    }

    public boolean doesObjectExistById(Long id){
        if(id != null){
            return repositorySFP.existsById(id);
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public Optional<SFP> findSFPBySerialNumberOptional(String serialnumber){
        if(serialnumber != null){
            if(repositorySFP.findSFPBySerialnumber(serialnumber).isPresent()){
                return repositorySFP.findSFPBySerialnumber(serialnumber);
            }else{
                throw new SamicException("Could not find SFP with serialnumber: '%s' in DB".formatted(serialnumber));
            }
        }else{
            throw new SamicException("Given name is null!");
        }
    }

    public Stream<SFP> findAll(){
        if(repositorySFP.findAll().isEmpty()){
            throw new SamicException("SFP list is empty!");
        }else{
            return repositorySFP.findAll().stream();
        }
    }

    public Stream<SFP> findAllSfpByProducerId(Long id, PageRequest request){
        if(id != null){
            return repositorySFP.findAllByProducerId(id, request).stream();
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public Stream<SFP> findAllSfpByProducerIdStream(Long id){
        if(id != null){
            return repositorySFP.findAllByProducerId(id);
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public Stream<SFP> findAllSfpByProducerName(String name){
        if(name != null){
            Stream<SFP> sfpByProducerName = repositorySFP.findAll().stream();
            return sfpByProducerName.filter(sfp -> sfp.getProducer().getName().equals(name));
        }else{
            throw new SamicException("Given name is null!");
        }
    }

}
