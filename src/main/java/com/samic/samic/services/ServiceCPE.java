package com.samic.samic.services;

import com.samic.samic.data.entity.CPE;
import com.samic.samic.data.persistence.RepositoryCPE;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceCPE {

    @Autowired
    private RepositoryCPE repositoryCPE;

        public CPE saveCPEByObject(CPE cpe){
            return repositoryCPE.save(cpe);
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
}
