package com.samic.samic.security;

import com.samic.samic.data.entity.User;
import com.samic.samic.data.repositories.RepositoryUser;
import com.vaadin.flow.spring.security.AuthenticationContext;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class AuthenticatedUser {
    //    private final UserRepository userRepository;
    //    private final UserService           userService;
    //    public AuthenticatedUser(AuthenticationContext authenticationContext, UserRepository userRepository, UserService userService) {
    //
    //    @Autowired
    //    private final RepositoryUser repositoryUser;


    @Autowired
    private final AuthenticationContext authenticationContext;
//    @Autowired
//    private final ServiceUser serviceUser;
    @Autowired
    private final RepositoryUser repositoryUser;


    //        this.userRepository        = userRepository;
//        this.authenticationContext = authenticationContext;
//        this.userService           = userService;
//    }

//    @Transactional

//    public Optional<User> get() {
//        return authenticationContext.getAuthenticatedUser(UserDetails.class)
//                .map(userDetails -> userRepository.findByUsername(userDetails.getUsername()));
//    }

//    @Transactional
//    public Optional<User> getUser(){
//        return authenticationContext.getAuthenticatedUser(UserDetails.class)
//                       .map(userDetails -> serviceUser.findUser(userDetails.getUsername()));
//    }

    @Transactional
    public Optional<User> getUser(){
        return authenticationContext.getAuthenticatedUser(UserDetails.class)
                       .map(userDetails -> repositoryUser.findByProfile_Username(userDetails.getUsername()));
    }

    public void logout() {
        authenticationContext.logout();
    }

}
