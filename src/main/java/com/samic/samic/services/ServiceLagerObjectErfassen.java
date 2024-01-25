package com.samic.samic.services;

import com.samic.samic.data.entity.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.samic.samic.data.foundation.Guard.isNotNull;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceLagerObjectErfassen{

    private final Logger          log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private final ServiceProducer serviceProducer;
    @Autowired
    private final ServiceSFP      serviceSFP;
    @Autowired
    private final ServiceCPE      serviceCPE;
    @Autowired
    private final ServiceSupply   serviceSupply;

    @Autowired
    private final ServiceStorage       serviceStorage;
    @Autowired
    private final ServiceStorageObject serviceStorageObject;



    public StorageObject LagerOBjectErfassenSFP(
            StorageObject storageObject,
            Storage storage,
            Producer producer,
            SFP sfp){



       return saveSFP_Erfassen(storageObject, storage, producer, sfp);
    }

    public void LagerOBjectErfassenCPE(
            StorageObject storageObject,
            Storage storage,
            Producer producer,
            CPE cpe){
        saveCPE_Erfassen(storageObject, storage, producer, cpe);
    }



    public void LagerOBjectErfassenSUPPLY(
            StorageObject storageObject,
            Storage storage,
            Producer producer,
            Supply supply){

        saveSupply_Erfassen(storageObject, storage, producer, supply);

    }




    public StorageObject saveSFP_Erfassen(StorageObject storageObject, Storage storage, Producer producer, SFP sfp){
        isNotNull(storageObject, "storageObject in StorageObejectErfassen() | Backend ");
        isNotNull(storage, "storage in StorageObejectErfassen() | Backend ");
        isNotNull(producer, "producer in StorageObejectErfassen() | Backend ");


        StorageObject storageObject1 = serviceStorageObject.findStorageObjectById(storageObject.getId());

        // if StorageObject not null -> fetch StorageObject from DB

        // if SFP not null ->

        return storageObject1;
    }

    private void saveCPE_Erfassen(StorageObject storageObject, Storage storage, Producer producer, CPE cpe){
    }


    private void saveSupply_Erfassen(StorageObject storageObject, Storage storage, Producer producer, Supply supply){
    }
}
