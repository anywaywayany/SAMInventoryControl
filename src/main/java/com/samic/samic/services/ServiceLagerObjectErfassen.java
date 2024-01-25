package com.samic.samic.services;

import com.samic.samic.data.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceLagerObjectErfassen{

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


    public void saveLagerOBjectErfassen(
            ObjectType objectType,
            Reservation reservation,
            CPE cpe,
            SFP sfp,
            Supply supply,
            User user,
            Storage storage){


    }


}
