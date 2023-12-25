package com.samic.samic.services;

import com.samic.samic.data.entity.Reservation;
import com.samic.samic.data.entity.User;
import com.samic.samic.data.repositories.RepositoryReservation;
import com.samic.samic.exceptions.SamicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Log4j2
public class ServiceReservation{

    @Autowired
    private final RepositoryReservation repositoryReservation;


    public Reservation saveReservationByObject(Reservation reservation){
        if(reservation != null){
            if(reservation.getId() != null){
                if(repositoryReservation.existsById(reservation.getId())){
                    log.debug("Reservation with id: '%s' already exists in DB".formatted(reservation.getId()));
                    throw new SamicException("Reservation with id: '%s' already exists in DB".formatted(reservation.getId()));
                }else{
                    return repositoryReservation.save(reservation);
                }
            }else{
                return repositoryReservation.save(reservation);
            }
        }else{
            throw new SamicException("Reservation is null!");
        }
    }

    public Reservation findReservationById(Long id){
        if(id != null){
            if(repositoryReservation.findById(id).isPresent()){
                return repositoryReservation.findById(id).get();
            }else{
                throw new SamicException("Could not find Reservation with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public Optional<Reservation> findReservationByIDOptional(Long id){
        if(id != null){
            if(repositoryReservation.findById(id).isPresent()){
                return repositoryReservation.findById(id);
            }else{
                throw new SamicException("Could not find Reservation with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public void deleteReservationById(Long id){
        if(id != null){
            repositoryReservation.deleteById(id);
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public void deleteByObject(Reservation reservation){
        if(reservation != null){
            repositoryReservation.delete(reservation);
        }else{
            throw new SamicException("Given Reservation is null!");
        }
    }

    public boolean doesObjectExistById(Long id){
        if(id != null){
            return repositoryReservation.existsById(id);
        }else{
            throw new SamicException("Given id is null!");
        }
    }

    public List<Reservation> findReservationListByUserOptional(User user){
        if(user != null){
            if(!repositoryReservation.findAllByReservedFrom(user).isEmpty()){
                return repositoryReservation.findAllByReservedFrom(user);
            }else{
                throw new SamicException("Could not find Reservation with user: '%s' in DB".formatted(user));
            }
        }else{
            throw new SamicException("Given name is null!");
        }
    }

    public Stream<Reservation> findAll(){
        if(repositoryReservation.findAll().isEmpty()){
            throw new SamicException("Reservation list is empty!");
        }else{
            return repositoryReservation.findAll().stream();
        }
    }

    public List<Reservation> findAllasList(){
        if(repositoryReservation.findAll().isEmpty()){
            throw new SamicException("Reservation list is empty!");
        }else{
            return repositoryReservation.findAll();
        }
    }


    public void deleteAll(){
        if(!repositoryReservation.findAll().isEmpty()){
            repositoryReservation.deleteAll();
        }else{
            throw new SamicException("Reservation DB is empty!");
        }
    }
}