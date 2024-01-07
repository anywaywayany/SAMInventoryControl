package com.samic.samic.services;

import com.samic.samic.data.entity.CPE;
import com.samic.samic.data.repositories.RepositoryCPE;
import com.samic.samic.exceptions.SamicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        if(cpe != null){
            if(cpe.getId() != null){
                if(doesObjectExistById(cpe.getId())){
                    CPE objectById = findCPEByID(cpe.getId());
                    if(objectById != null){
                        if(objectById.getId().equals(cpe.getId())){
                            objectById = cpe;
                            return repositoryCPE.save(objectById);
                        }else{
                            throw new SamicException("CPE with id1: '%s' and id2: '%s' does not match. Some error occoured while fetch!!".formatted(objectById.getId(), cpe.getId()));
                        }
                    }else{
                        throw new SamicException("CPE with id: '%s' does not exist in DB".formatted(cpe.getId()));
                    }
                }else{
                    throw new SamicException("CPE with id: '%s' does not exist in DB but does have a id: ".formatted(cpe.getId()));
                }
            }else{
                CPE saved = repositoryCPE.save(cpe);
                return saved;
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
        return repositoryCPE.findAll().stream();
    }

    public Stream<CPE> findAllCPEByProducerIDPageRequest(Long id, PageRequest request){
        return repositoryCPE.findCPESByProducerId(id, request).stream();
    }


    public Page<CPE> findAll(Pageable pageable){
        return repositoryCPE.findAll(pageable);

    }
}
