package com.samic.samic.services;

import com.samic.samic.data.entity.SFP;
import com.samic.samic.data.entity.Storage;
import com.samic.samic.data.repositories.RepositorySFP;
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
public class ServiceSFP{

    @Autowired
    private RepositorySFP repositorySFP;

    public SFP saveSFPByObject(SFP sfp){
        if(sfp != null){
            if(sfp.getId() != null){
                if(repositorySFP.existsById(sfp.getId())){
                    log.debug("SFP with id: '%s', serialnumber: '%s' already exists in DB".formatted(sfp.getId(), sfp.getSerialnumber()));
                    throw new SamicException("SFP with id: '%s' already exists in DB".formatted(sfp.getId()));
                }else{
                    return repositorySFP.save(sfp);
                }
            }else{
                return repositorySFP.save(sfp);
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

    public Optional<SFP> findSFPByNameOptional(String serialnumber){
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

}
