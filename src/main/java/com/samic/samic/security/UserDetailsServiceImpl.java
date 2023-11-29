package com.samic.samic.security;

import com.samic.samic.data.entity.Role;
import com.samic.samic.data.entity.User;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.samic.samic.data.persistence.RepositoryUser;
import com.samic.samic.services.ServiceUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

//    @Autowired
//    private final ServiceUser serviceUser;
    @Autowired
    private final RepositoryUser repositoryUser;



    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user1 = serviceUser.findByUsername(username);
//        User user = serviceUser.findUser(username);
        User user1 = repositoryUser.findByProfile_Username(username);

        if (user1 == null) {
            throw new UsernameNotFoundException("No user present with username: " + username);
        } else {
            return new org.springframework.security.core.userdetails.User(user1.getProfile().getUsername(), user1.getHashedPassword(),
                    getAuthorities(user1));
        }
    }



//    private static List<GrantedAuthority> getAuthorities(User user) {
//        return user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                .collect(Collectors.toList());
//
//    }

    private static List<GrantedAuthority> getAuthorities(User user){
        Role userRole = user.getRole();
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+userRole));
    }

}
