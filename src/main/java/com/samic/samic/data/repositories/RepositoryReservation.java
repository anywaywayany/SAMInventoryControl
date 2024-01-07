package com.samic.samic.data.repositories;

import com.samic.samic.data.entity.Reservation;
import com.samic.samic.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface RepositoryReservation extends JpaRepository<Reservation, Long>{
    Stream<Reservation> findReservationByReservedFrom(User user);


    //    List<Reservation> findReservationListByReservedFrom(User user);

    List<Reservation> findAllByReservedFrom(User user);
}
