package com.samic.samic.services;

import com.samic.samic.data.entity.Reservation;
import com.samic.samic.data.entity.User;
import com.samic.samic.data.repositories.RepositoryReservation;
import com.samic.samic.exceptions.ReservationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
//@ComponentScan(basePackages = {"com.samic.samic.security"})

public class ServiceReservation{

    @Autowired
    private final RepositoryReservation repositoryReservation;
    //    @Autowired
    //    private final AuthenticatedUser authenticatedUser;

    @Transactional
    public Reservation saveReservationByObject(Reservation reservation){
        if(reservation != null){
            if(reservation.getId() == null){
                System.out.println(reservation.getReservedFrom()+"----------User1");
                if(reservation.getReservedFrom() != null){
                    System.out.println(reservation.getReservedFrom()+"----------User2");
                    //                    reservation.setReservedAt(DateTimeFactory.now());
                    if(reservation.getCustomer() != null){
                        System.out.println(reservation.getReservedFrom()+"----------User3");
                        reservation.setCustomer(reservation.getCustomer());
                    }
                    System.out.println(reservation.getReservedFrom()+"----------User4");
                    //                    reservation.setReservedFrom(authenticatedUser.getUser().get());
                    System.out.println(reservation.getReservedFrom()+"----------User5");
                    return repositoryReservation.save(reservation);

                }else{
                    throw new ReservationException("Reservation with id: '%s' does not have a user".formatted(reservation.getId()));
                }
            }else{
                throw new ReservationException("Reservation with id: '%s' already exists in DB".formatted(reservation.getId()));
            }
        }else{
            throw new ReservationException("Reservation is null!");
        }
    }

    @Transactional
    public Reservation findReservationById(Long id){
        if(id != null){
            if(repositoryReservation.findById(id)
                                    .isPresent()){
                return repositoryReservation.findById(id)
                                            .get();
            }else{
                throw new ReservationException("Could not find Reservation with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new ReservationException("Given id is null!");
        }
    }

    @Transactional
    public Optional<Reservation> findReservationByIDOptional(Long id){
        if(id != null){
            if(repositoryReservation.findById(id)
                                    .isPresent()){
                return repositoryReservation.findById(id);
            }else{
                throw new ReservationException("Could not find Reservation with id: '%s' in DB".formatted(id));
            }
        }else{
            throw new ReservationException("Given id is null!");
        }
    }

    public void deleteReservationById(Long id){
        if(id != null){
            repositoryReservation.deleteById(id);
        }else{
            throw new ReservationException("Given id is null!");
        }
    }

    public void deleteByObject(Reservation reservation){
        if(reservation != null){
            repositoryReservation.delete(reservation);
        }else{
            throw new ReservationException("Given Reservation is null!");
        }
    }

    public boolean doesObjectExistById(Long id){
        if(id != null){
            return repositoryReservation.existsById(id);
        }else{
            throw new ReservationException("Given id is null!");
        }
    }

    //    @Transactional
    //    public List<Reservation> findReservationListByUserOptional(User user){
    //        if(user != null){
    //            if(repositoryReservation.count()>0L){
    //                return repositoryReservation.findAllBy_reservedFrom(user);
    //            }else{
    //                throw new ReservationException("Could not find Reservation with user: '%s' in DB".formatted(user));
    //            }
    //        }else{
    //            throw new ReservationException("Given name is null!");
    //        }
    //    }
    @Transactional
    public Stream<Reservation> findAll(){
        return repositoryReservation.findAll()
                                    .stream();
    }

    @Transactional
    public List<Reservation> findAllasList(){
        return repositoryReservation.findAll();
    }


    public void deleteAll(){
        repositoryReservation.deleteAll();
    }

    public List<Reservation> findAllReservationByUserId(Long id){
        if(id != null){
            return repositoryReservation.findAllByReservedFromId(id);
        }else{
            throw new ReservationException("Given id is null!");
        }
    }

    @Transactional
    public Stream<Reservation> findAllReservationByUserIdStream(Long id){
        if(id != null){
            return repositoryReservation.findAllByReservedFrom_Id(id)
                                        .stream();
        }else{
            throw new ReservationException("Given id is null!");
        }
    }

    @Transactional
    public Stream<Reservation> findAllReservationByGivenUser(User user){
        Stream<Reservation> reservationOnUser = repositoryReservation.findReservationsByReservedFrom(user);


        return reservationOnUser/*.peek(u -> System.out.println(u.toString()+"\n"))*/.filter(reservation -> reservation.getReservedFrom()
                                                                                                                       .getId()
                                                                                                                       .equals(user.getId()));
    }
}
