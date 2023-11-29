//package com.samic.samic.data.entity;
//
//import com.samic.samic.data.persistence.RepositoryCPE;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//class CPETest{
//
//    @Autowired
//    private RepositoryCPE repository;
////    private final Logger LOGGER = LoggerFactory.getLogger(CPETest.class);
//    @Test
//    void ensue_CPE_fetch_into_db(){
//
//
//        //given
//        Producer prod = Producer.builder().shortname("Cisco").name("Cisco").build();
//
//        CPE cpe = new CPE.CPEBuilder()
//                          .macAddress("FF-FF-FF")
//                          .serialnumber("123456")
//                          .producer(prod)
//                          .type(Type.IP_PHONE).build();
//
//
//
//        //when
//
//        var saved = repository.saveAndFlush(cpe);
//
//        //then
//
//        assertThat(repository.findById(cpe.getId()).get()).isSameAs(saved);
//
//    }
//
//}