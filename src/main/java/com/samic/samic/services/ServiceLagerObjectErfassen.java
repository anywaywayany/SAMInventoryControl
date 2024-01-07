package com.samic.samic.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceLagerObjectErfassen{

    @Autowired
    private ServiceProducer serviceProducer;
    @Autowired
    private ServiceSFP serviceSFP;
//    @Autowired
//    private Service

}
