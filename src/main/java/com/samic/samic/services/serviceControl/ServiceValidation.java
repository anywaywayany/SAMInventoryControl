package com.samic.samic.services.serviceControl;

import com.samic.samic.data.entity.CPE;
import com.samic.samic.data.service.RepositoryCPE;
import com.samic.samic.exceptions.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServiceValidation{

    private final RepositoryCPE repositoryCPE;

    public boolean checkObject(CPE cpe) throws ValidationException{
        if(cpe.getId() == null){
            Example<CPE> cpeExample = Example.of(cpe);
            if(!repositoryCPE.exists(cpeExample))
            return true;
        }else{
            throw new ValidationException("Object has already a ID! Should exist in DB! checkObject()");
        }
        return false;
    }

}
