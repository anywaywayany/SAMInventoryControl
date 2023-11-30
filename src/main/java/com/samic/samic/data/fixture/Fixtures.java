package com.samic.samic.data.fixture;

import com.samic.samic.data.entity.*;
import com.samic.samic.data.foundation.DateTimeFactory;

public class Fixtures{

    public static Producer giveProducer(){
        return Producer.builder()
                       .shortname("Cisco")
                       .name("langerCiscoName")
                       .build();
    }

    public static SFP giveSFP(){
        return SFP.builder()
                       .wavelength("zwanzig_meta")
                       .nicSpeed(9999)
                       .serialnumber("921jlh31")
                       .type(Type.IP_PHONE)
                       .sfpType(SFPType.MM)
                       .producer(giveProducer())
                       .build();
    }

    public static CPE giveCPE(){
        return CPE.builder()
                       .macAddress("FF-FF-FF")
                       .serialnumber("123456")
                       .producer(giveProducer())
                       .type(Type.IP_PHONE).build();
    }

    public static User giveUser(){
        return User.builder()
                       .createdAt(DateTimeFactory.now_minus_one_year())
                       .lastLogin(DateTimeFactory.now_minus_one_week())
                       .profile(Profile.builder()
                                        .firstName("Eula")
                                        .lastName("Lane")
                                        .username("management")

                                        .build())
                       .mail("agnes.toccafondi@viipo.ae")
                       .hashedPassword("$2a$10$xdbKoM48VySZqVSU/cSlVeJn0Z04XCZ7KZBjUBC00eKo5uLswyOpe")
                       .profile(Profile.builder().phone("(762) 526-5961").build())
                       .role(Role.MANAGMENT).build();
    }

    public static Storage giveStorage(){
        return Storage.builder()
                       .name("mittleresRegalGanzHintenUnterDemOberen")
                       .address(Address.builder()
                                        .street("Backstreet")
                                        .houseNo(44)
                                        .doorNo(4)
                                        .zipCode(444)
                                        .city("SinCity")
                                        .build())
                       .build();
    }
}
