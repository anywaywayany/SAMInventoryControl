package com.samic.samic.services;

import com.samic.samic.data.entity.CPE;
import com.samic.samic.data.repositories.RepositoryCPE;
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
public class ServiceCPE{

    @Autowired
    private final RepositoryCPE repositoryCPE;


    public CPE saveCPEByObject(CPE cpe){
//        if(serviceValidation.checkObject(cpe)){
//            return repositoryCPE.save(cpe);
//        }else{
//            throw new ValidationException("CPE checking in service fail!");
//        }

        if(cpe != null){
            if(cpe.getId() != null){
                if(repositoryCPE.existsById(cpe.getId())){
                    log.debug("CPE with MAC: '%s', name: '%s' already exists in DB".formatted(cpe.getId(), cpe.getMacAddress()));
                    throw new SamicException("CPE with id: '%s' already exists in DB".formatted(cpe.getId()));
                }else{
                    return repositoryCPE.save(cpe);
                }
            }else{
                return repositoryCPE.save(cpe);
            }
        }else{
            throw new SamicException("CPE is null!");
        }
    }

    public CPE findCPEByID(Long id){
        if(id != null){
            if(repositoryCPE.findById(id).isPresent()){
                return repositoryCPE.findById(id).get();
            }else{
                throw new SamicException("Could not find CPE with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public Optional<CPE> findCPEByIDOptional(Long id){
        if(id != null){
            if(repositoryCPE.findById(id).isPresent()){
                return repositoryCPE.findById(id);
            }else{
                throw new SamicException("Could not find CPE with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new SamicException("Given id is null!");
        }
    }


    public void deleteCPEtById(Long id){
        if(id != null){
            repositoryCPE.deleteById(id);
        }else{
            throw new SamicException("Given id is null!");
        }

    }

    public void deleteByObject(CPE cpe){
        if(cpe != null){
            repositoryCPE.delete(cpe);
        }else{
            throw new SamicException("Given CPE is null!");
        }

    }

    public boolean doesObjectExistById(Long id){
        if(id != null){
            return repositoryCPE.existsById(id);
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public Optional<CPE> findCPEBySerialbumberOptional(String serialnumber){
        if(serialnumber != null){
            if(repositoryCPE.findCPEBySerialnumber(serialnumber).isPresent()){
                return repositoryCPE.findCPEBySerialnumber(serialnumber);
            }else{
                throw new SamicException("Could not find CPE with serialnumber: '%s' in DB".formatted(serialnumber));
            }
        }else{
            throw new SamicException("Given name is null!");
        }
    }

    public Stream<CPE> findAll(){
        if(repositoryCPE.findAll().isEmpty()){
            throw new SamicException("CPE list is empty!");
        }else{
            return repositoryCPE.findAll().stream();
        }
    }
}
