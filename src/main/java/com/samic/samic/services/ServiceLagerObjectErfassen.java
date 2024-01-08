package com.samic.samic.services;

import com.samic.samic.security.AuthenticatedUser;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ComponentScan(basePackages = "com.samic.samic.security")
public class ServiceLagerObjectErfassen{

    @Autowired
    private final ServiceProducer serviceProducer;
    @Autowired
    private final ServiceSFP serviceSFP;

    private final AuthenticatedUser authenticatedUser;


//    @Autowired
//    private Service

}
