package com.samic.samic.services;

import com.samic.samic.data.entity.CPE;
import com.samic.samic.data.entity.SFP;
import com.samic.samic.data.entity.Storage;
import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.data.repositories.RepositorySFP;
import com.samic.samic.exceptions.SFPException;
import com.samic.samic.exceptions.SamicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class ServiceSFP{

    @Autowired
    private RepositorySFP repositorySFP;

    @Transactional
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
                            throw new SFPException("SFP with id1: '%s' and id2: '%s' does not match. Some error occoured while fetch!!".formatted(objectById.getId(), sfp.getId()));
                        }
                    }else{
                        throw new SFPException("SFP with id: '%s' does not exist in DB".formatted(sfp.getId()));
                    }
                }else{
                    throw new SFPException("SFP with id: '%s' does not exist in DB but does have a id: ".formatted(sfp.getId()));
                }
            }else{
                SFP saved = repositorySFP.save(sfp);
                return saved;
            }
        }else{
            throw new SFPException("SFP is null!");
        }
    }

    @Transactional
    public SFP findSFPById(Long id){
        if(id != null){
            if(repositorySFP.findById(id).isPresent()){
                return repositorySFP.findById(id).get();
            }else{
                throw new SFPException("Could not find SFP with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new SFPException("Given id is null!");
        }
    }

    @Transactional
    public Optional<SFP> findSFPByIDOptional(Long id){
        if(id != null){
            if(repositorySFP.findById(id).isPresent()){
                return repositorySFP.findById(id);
            }else{
                throw new SFPException("Could not find SFP with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new SFPException("Given id is null!");
        }
    }


    public void deleteSFPById(Long id){
        if(id != null){
            repositorySFP.deleteById(id);
        }else{
            throw new SFPException("Given id is null!");
        }

    }

    public void deleteByObject(SFP sfp){
        if(sfp != null){
            repositorySFP.delete(sfp);
        }else{
            throw new SFPException("Given sfp is null!");
        }

    }

    public boolean doesObjectExistById(Long id){
        if(id != null){
            return repositorySFP.existsById(id);
        }else{
            throw new SFPException("Given id is null!");
        }
    }

    @Transactional
    public Optional<SFP> findSFPBySerialNumberOptional(String serialnumber){
        if(serialnumber != null){
            if(repositorySFP.findSFPBySerialnumber(serialnumber).isPresent()){
                return repositorySFP.findSFPBySerialnumber(serialnumber);
            }else{
                throw new SFPException("Could not find SFP with serialnumber: '%s' in DB".formatted(serialnumber));
            }
        }else{
            throw new SFPException("Given name is null!");
        }
    }

    @Transactional
    public Stream<SFP> findAll(){
            return repositorySFP.findAll().stream();
    }

    @Transactional
    public Stream<SFP> findAllSfpByProducerId(Long id, PageRequest request){
        if(id != null){
            return repositorySFP.findAllByProducerId(id, request).stream();
        }else{
            throw new SFPException("Given id is null!");
        }
    }

    @Transactional
    public Stream<SFP> findAllSfpByProducerIdStream(Long id){
        if(id != null){
            return repositorySFP.findAllByProducerId(id);
        }else{
            throw new SFPException("Given id is null!");
        }
    }

    @Transactional
    public Stream<SFP> findAllSfpByProducerName(String name){
        if(name != null){
            Stream<SFP> sfpByProducerName = repositorySFP.findAll().stream();
            return sfpByProducerName.filter(sfp -> sfp.getProducer().getName().equals(name));
        }else{
            throw new SFPException("Given name is null!");
        }
    }

}
