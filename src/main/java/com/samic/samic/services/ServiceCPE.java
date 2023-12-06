package com.samic.samic.services;

import com.samic.samic.data.entity.CPE;
import com.samic.samic.data.service.RepositoryCPE;
import com.samic.samic.exceptions.ValidationException;
import com.samic.samic.services.serviceControl.ServiceValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ServiceCPE{

    @Autowired
    private final RepositoryCPE repositoryCPE;

    @Autowired
    private final ServiceValidation serviceValidation;

    public CPE saveCPEByObject(CPE cpe) throws ValidationException{
        //            boolean exist = false;
        //             exist = serviceValidation.checkObject(cpe);
        if(serviceValidation.checkObject(cpe)){
            return repositoryCPE.save(cpe);
        }else{
            throw new ValidationException("CPE checking in service fail!");
        }
    }

    public CPE findCPEByID(Long id){
        return repositoryCPE.findById(id).get();
    }


    public void deleteCPEtById(Long id){
        repositoryCPE.deleteById(id);
    }

    public void deleteByObject(CPE cpe){
        repositoryCPE.delete(cpe);
    }

    public boolean doesObjectExistById(Long id){
        return repositoryCPE.existsById(id);
    }

    public Stream<CPE> findAll(){
        return repositoryCPE.findAll().stream();
    }
}
