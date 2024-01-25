package com.samic.samic.services;

import com.samic.samic.data.entity.Role;
import com.samic.samic.data.entity.Storage;
import com.samic.samic.data.entity.StorageObject;
import com.samic.samic.data.entity.User;
import com.samic.samic.data.repositories.RepositoryUser;
import com.samic.samic.exceptions.SamicException;
import com.samic.samic.security.AuthenticatedUser;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class ServiceUser{

    @Autowired
    RepositoryUser repositoryUser;
    @Autowired
    ServiceStorageObject serviceStorageObject;
    //    @Autowired
    //    AuthenticatedUser authenticatedUser;
    //
    public User findUser(String username){
        if(username != null){
            return repositoryUser.findByProfile_Username(username);
        }else{
            throw new RuntimeException("Given username is null!");
        }
    }

    @Transactional
    public User saveUser(User user){
        if(user != null){
            if(user.getId() != null){
                if(doesObjectExistById(user.getId())){
                    User objectById = findUserByID(user.getId());
                    if(objectById != null){
                        if(objectById.getId().equals(user.getId())){
                            objectById = user;
                            return repositoryUser.save(objectById);
                        }else{
                            throw new SamicException("User with id1: '%s' and id2: '%s' does not match. Some error occoured while fetch!!".formatted(objectById.getId(), user.getId()));
                        }
                    }else{
                        throw new SamicException("User with id: '%s' does not exist in DB".formatted(user.getId()));
                    }
                }else{
                    throw new SamicException("User with id: '%s' does not exist in DB but does have a id: ".formatted(user.getId()));
                }
            }else{
                User saved = repositoryUser.save(user);
                return saved;
            }
        }else{
            throw new SamicException("User is null!");
        }
    }

    public long userCount(){
        if(repositoryUser.findAll().isEmpty()){
            throw new SamicException("There is no user in DB!");
        }
        return repositoryUser.count();
    }
    ////////////////////////////////////////////////////

    public User findUserByID(Long id){
        if(id != null){
            if(repositoryUser.findById(id).isPresent()){
                return repositoryUser.findById(id).get();
            }else{
                throw new SamicException("Could not find User with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new SamicException("Given id is null!");
        }

    }

    public Optional<User> findUserByIDOptional(Long id){
        if(id != null){
            if(repositoryUser.findById(id).isPresent()){
                return repositoryUser.findById(id);
            }else{
                throw new SamicException("Could not find User with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new SamicException("Given id is null!");
        }
    }


    public void deleteUserById(Long id){
        if(id != null){
            if(!repositoryUser.findAll().isEmpty()){
                repositoryUser.deleteById(id);
            }else{
                throw new SamicException("User DB is empty!");
            }
        }else{
            throw new SamicException("Given id is null!");
        }

    }

    public void deleteByObject(User user){
        if(user != null){
            if(!repositoryUser.findAll().isEmpty()){
                repositoryUser.delete(user);
            }else{
                throw new SamicException("User DB is empty!");
            }
        }else{
            throw new SamicException("Given User is null!");
        }
    }

    public boolean doesObjectExistById(Long id){
        if(id != null){
            return repositoryUser.existsById(id);
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public Stream<User> findStorageByNameOptional(Role role){
        if(role != null){
            if(repositoryUser.findByRole(role).findAny().isPresent()){
                return repositoryUser.findByRole(role);
            }else{
                throw new SamicException("Could not find User with Role: '%s' in DB".formatted(role));
            }
        }else{
            throw new SamicException("Given name is null!");
        }
    }

    public Stream<User> findAll(){
        if(repositoryUser.findAll().isEmpty()){
            throw new SamicException("User list is empty!");
        }else{
            return repositoryUser.findAll().stream();
        }

    }

    public void deleteAll(){
        if(!repositoryUser.findAll().isEmpty()){
            repositoryUser.deleteAll();
        }else{
            throw new SamicException("User DB is empty!");
        }
    }



}
