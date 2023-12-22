package com.samic.samic.data.service;

import com.samic.samic.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;




public interface RepositoryUser extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, ListCrudRepository<User, Long>, ListPagingAndSortingRepository<User, Long>, QueryByExampleExecutor<User>{

//    User findByProfile(Profile profile);


//    User findByUsername(String username);


//   User findByUsername2(String username);
//
//    User findByProfileUsername(String username);

    User findByProfile_Username(String username);
    User save(User user);
}