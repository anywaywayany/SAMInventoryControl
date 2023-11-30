package com.samic.samic.services;

import com.samic.samic.data.entity.User;
import com.samic.samic.data.persistence.RepositoryUser;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@RequiredArgsConstructor
@Service
public class ServiceUser{

    @Autowired
    RepositoryUser repositoryUser;


    public User findUser(String username){
        return repositoryUser.findByProfile_Username(username);
    }

    public User saveUser(User user){
        return repositoryUser.save(user);
    }

    public long userCount(){
        return repositoryUser.count();
    }
    ////////////////////////////////////////////////////

    public User findUserByID(Long id){
        return repositoryUser.findById(id).get();
    }


    public void deleteUserById(Long id){
        repositoryUser.deleteById(id);
    }

    public void deleteByObject(User user){
        repositoryUser.delete(user);
    }

    public boolean doesObjectExistById(Long id){
        return repositoryUser.existsById(id);
    }

}
