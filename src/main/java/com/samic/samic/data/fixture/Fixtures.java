package com.samic.samic.data.fixture;

import com.samic.samic.data.entity.*;
import com.samic.samic.data.foundation.DateTimeFactory;

public class Fixtures{

    public static Producer giveProducer1(){
        return Producer.builder()
                       .shortname("Cisco")
                       .name("langerCiscoName")
                       .build();
    }

    public static SFP giveSFP1(){
        return SFP.builder()
                       .wavelength("111nm")
                       .nicSpeed(123)
                       .serialnumber("921jlh31")
                       .type(Type.IP_PHONE)
                       .sfpType(SFPType.MM)
                       .producer(giveProducer1())
                       .build();
    }

    public static SFP giveSFP2(){
        return SFP.builder()
                       .wavelength("222nm")
                       .nicSpeed(9999)
                       .serialnumber("921jlh31")
                       .type(Type.IP_PHONE)
                       .sfpType(SFPType.MM)
                       .producer(giveProducer1())
                       .build();
    }

    public static SFP giveSFP3(){
        return SFP.builder()
                       .wavelength("333nm")
                       .nicSpeed(9999)
                       .serialnumber("921jlh31")
                       .type(Type.IP_PHONE)
                       .sfpType(SFPType.MM)
                       .producer(giveProducer1())
                       .build();
    }

    public static SFP giveSFP4(){
        return SFP.builder()
                       .wavelength("444m,")
                       .nicSpeed(9999)
                       .serialnumber("921jlh31")
                       .type(Type.IP_PHONE)
                       .sfpType(SFPType.MM)
                       .producer(giveProducer1())
                       .build();
    }

    public static SFP giveSFP5(){
        return SFP.builder()
                       .wavelength("555nm")
                       .nicSpeed(9999)
                       .serialnumber("921jlh31")
                       .type(Type.IP_PHONE)
                       .sfpType(SFPType.MM)
                       .producer(giveProducer1())
                       .build();
    }


    public static CPE giveCPE1(){
        return CPE.builder()
                       .macAddress("FF-FF-FF-FF-FF-FF")
                       .serialnumber("123456")
                       .producer(giveProducer1())
                       .type(Type.IP_PHONE).build();
    }

    public static CPE giveCPE2(){
        return CPE.builder()
                       .macAddress("00-00-00-00-00-00")
                       .serialnumber("33333")
                       .producer(giveProducer1())
                       .type(Type.IP_PHONE).build();
    }


    public static CPE giveCPE3(){
        return CPE.builder()
                       .macAddress("aa-bb-cc-dd-ff-11")
                       .serialnumber("654321")
                       .producer(giveProducer1())
                       .type(Type.IP_PHONE).build();
    }

    public static CPE giveCPE4(){
        return CPE.builder()
                       .macAddress("aa-bb-cc-dd-ff-22")
                       .serialnumber("654321")
                       .producer(giveProducer1())
                       .type(Type.IP_PHONE).build();
    }

    public static CPE giveCPE5(){
        return CPE.builder()
                       .macAddress("aa-bb-cc-dd-ff-33")
                       .serialnumber("654321")
                       .producer(giveProducer1())
                       .type(Type.IP_PHONE).build();
    }

    public static User giveUser1(){
        return User.builder()
                       .createdAt(DateTimeFactory.now_minus_one_year())
                       .lastLogin(DateTimeFactory.now_minus_one_week())

                       .profile(giveProfile1())
                       .mail("AAA.toccafondi@viipo.ae")
                       .hashedPassword("$2a$10$xdbKoM48VySZqVSU/cSlVeJn0Z04XCZ7KZBjUBC00eKo5uLswyOpe")
                       .profile(Profile.builder().phone("(762) 526-5961").build())
                       .role(Role.MANAGMENT)
                       .activated(true)
                       .build();
    }

    public static User giveUser2(){
        return User.builder()
                       .createdAt(DateTimeFactory.now_minus_one_year())
                       .lastLogin(DateTimeFactory.now_minus_one_week())

                       .profile(giveProfile1())
                       .mail("BBB.toccafondi@viipo.ae")
                       .hashedPassword("$2a$10$xdbKoM48VySZqVSU/cSlVeJn0Z04XCZ7KZBjUBC00eKo5uLswyOpe")
                       .profile(Profile.builder().phone("(762) 526-5961").build())
                       .role(Role.MANAGMENT)
                       .activated(true)
                       .build();
    }

    public static User giveUser3(){
        return User.builder()
                       .createdAt(DateTimeFactory.now_minus_one_year())
                       .lastLogin(DateTimeFactory.now_minus_one_week())

                       .profile(giveProfile1())
                       .mail("CCC.toccafondi@viipo.ae")
                       .hashedPassword("$2a$10$xdbKoM48VySZqVSU/cSlVeJn0Z04XCZ7KZBjUBC00eKo5uLswyOpe")
                       .profile(Profile.builder().phone("(762) 526-5961").build())
                       .role(Role.MANAGMENT)
                       .activated(true)
                       .build();
    }

    public static User giveUser4(){
        return User.builder()
                       .createdAt(DateTimeFactory.now_minus_one_year())
                       .lastLogin(DateTimeFactory.now_minus_one_week())

                       .profile(giveProfile1())
                       .mail("DDD.toccafondi@viipo.ae")
                       .hashedPassword("$2a$10$xdbKoM48VySZqVSU/cSlVeJn0Z04XCZ7KZBjUBC00eKo5uLswyOpe")
                       .profile(Profile.builder().phone("(762) 526-5961").build())
                       .role(Role.MANAGMENT)
                       .activated(true)
                       .build();
    }

    public static User giveUser5(){
        return User.builder()
                       .createdAt(DateTimeFactory.now_minus_one_year())
                       .lastLogin(DateTimeFactory.now_minus_one_week())

                       .profile(giveProfile1())
                       .mail("EEE.toccafondi@viipo.ae")
                       .hashedPassword("$2a$10$xdbKoM48VySZqVSU/cSlVeJn0Z04XCZ7KZBjUBC00eKo5uLswyOpe")
                       .profile(Profile.builder().phone("(762) 526-5961").build())
                       .role(Role.MANAGMENT)
                       .activated(true)
                       .build();
    }

    private static Profile giveProfile1(){
        return Profile.builder()
                       .firstName("AAAA")
                       .lastName("Lane2")
                       .username("management2")
                       .build();
    }

    private static Profile giveProfile2(){
        return Profile.builder()
                       .firstName("BBB")
                       .lastName("Lane2")
                       .username("management2")
                       .build();
    }

    private static Profile giveProfile3(){
        return Profile.builder()
                       .firstName("CCC")
                       .lastName("Lane2")
                       .username("management2")
                       .build();
    }

    private static Profile giveProfile4(){
        return Profile.builder()
                       .firstName("DDD")
                       .lastName("Lane2")
                       .username("management2")
                       .build();
    }

    private static Profile giveProfile5(){
        return Profile.builder()
                       .firstName("EEE")
                       .lastName("Lane2")
                       .username("management2")
                       .build();
    }

    public static Storage giveStorage1(){
        return Storage.builder()
                       .name("Storage A")
                       .address(Address.builder()
                                        .street("Backstreet")
                                        .houseNo(44)
                                        .doorNo(4)
                                        .zipCode(444)
                                        .city("SinCity")
                                        .build())
                       .build();
    }

    public static Storage giveStorage2(){
        return Storage.builder()
                       .name("Storage B")
                       .address(Address.builder()
                                        .street("Backstreet")
                                        .houseNo(44)
                                        .doorNo(4)
                                        .zipCode(444)
                                        .city("SinCity")
                                        .build())
                       .build();
    }

    public static Storage giveStorage3(){
        return Storage.builder()
                       .name("Storage C")
                       .address(Address.builder()
                                        .street("Backstreet")
                                        .houseNo(44)
                                        .doorNo(4)
                                        .zipCode(444)
                                        .city("SinCity")
                                        .build())
                       .build();
    }

    public static Storage giveStorage4(){
        return Storage.builder()
                       .name("Storage D")
                       .address(Address.builder()
                                        .street("Backstreet")
                                        .houseNo(44)
                                        .doorNo(4)
                                        .zipCode(444)
                                        .city("SinCity")
                                        .build())
                       .build();
    }

    public static Storage giveStorage5(){
        return Storage.builder()
                       .name("Storage E")
                       .address(Address.builder()
                                        .street("Backstreet")
                                        .houseNo(44)
                                        .doorNo(4)
                                        .zipCode(444)
                                        .city("SinCity")
                                        .build())
                       .build();
    }


    public static Supply giveSupply1(){
        return Supply.builder()
                       .description("this is a description")
                       .amount(4)
                       .build();
    }

    public static Supply giveSupply2(){
        return Supply.builder()
                       .description("this is a description")
                       .amount(4)
                       .build();
    }

    public static Supply giveSupply3(){
        return Supply.builder()
                       .description("this is a description")
                       .amount(4)
                       .build();
    }

    public static Supply giveSupply4(){
        return Supply.builder()
                       .description("this is a description")
                       .amount(4)
                       .build();
    }

    public static Supply giveSupply5(){
        return Supply.builder()
                       .description("this is a description")
                       .amount(4)
                       .build();
    }

    public static StorageObject giveStorageObject1(){
        return StorageObject.builder()
                       .remark("remark1")
                       .storage(giveStorage1())
                       .cpe(giveCPE1())
                       .sfp(giveSFP1())
                       .objectTypeName(giveObjectType1())
                       .storedAtUser(giveUser1())
                       .storedAtCustomer(Customer.builder()
                                                 .connectionNo(1234).build())
                       .projectDevice(false)
                       .status(Status.AVAILABLE)
                       .supply(giveSupply1())
                       .build();

    }

    public static StorageObject giveStorageObject2(){
        return StorageObject.builder()
                       .remark("remark1")
                       .storage(giveStorage1())
                       .cpe(giveCPE1())
                       .sfp(giveSFP1())
                       .objectTypeName(giveObjectType1())
                       .storedAtUser(giveUser1())
                       .storedAtCustomer(Customer.builder()
                                                 .connectionNo(1234).build())
                       .projectDevice(false)
                       .status(Status.AVAILABLE)
                       .supply(giveSupply1())
                       .build();

    }

    public static StorageObject giveStorageObject3(){
        return StorageObject.builder()
                       .remark("remark1")
                       .storage(giveStorage1())
                       .cpe(giveCPE1())
                       .sfp(giveSFP1())
                       .storedAtUser(giveUser1())
                       .storedAtCustomer(Customer.builder()
                                                 .connectionNo(1234).build())
                       .projectDevice(false)
                       .status(Status.AVAILABLE)
                       .supply(giveSupply1())
                       .build();

    }

    public static StorageObject giveStorageObject4(){
        return StorageObject.builder()
                       .remark("remark1")
                       .storage(giveStorage1())
                       .cpe(giveCPE1())
                       .sfp(giveSFP1())
                       .storedAtUser(giveUser1())
                       .storedAtCustomer(Customer.builder()
                                                 .connectionNo(1234).build())
                       .projectDevice(false)
                       .status(Status.AVAILABLE)
                       .supply(giveSupply1())
                       .build();

    }

    public static StorageObject giveStorageObject5(){
        return StorageObject.builder()
                       .remark("remark1")
                       .storage(giveStorage1())
                       .cpe(giveCPE1())
                       .sfp(giveSFP1())
                       .storedAtUser(giveUser1())
                       .storedAtCustomer(Customer.builder()
                                                 .connectionNo(1234).build())
                       .projectDevice(false)
                       .status(Status.AVAILABLE)
                       .supply(giveSupply1())
                       .build();

    }

    public static ObjectType giveObjectType1(){
        return ObjectType.builder()
                       .name("AAA-CB183LKNO")
                       .build();
    }

    public static ObjectType giveObjectType2(){
        return ObjectType.builder()
                       .name("BBB-CB183LKNO")
                       .build();
    }

    public static ObjectType giveObjectType3(){
        return ObjectType.builder()
                       .name("CCC-CB183LKNO")
                       .build();
    }

    public static ObjectType giveObjectType4(){
        return ObjectType.builder()
                       .name("CB183LKNO")
                       .build();
    }

    public static ObjectType giveObjectType5(){
        return ObjectType.builder()
                       .name("CB183LKNO")
                       .build();
    }

}
