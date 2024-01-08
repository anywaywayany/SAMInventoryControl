package com.samic.samic.data.repositories;

import com.samic.samic.data.entity.Reservation;
import com.samic.samic.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryReservation extends JpaRepository<Reservation, Long>{
    Optional<Reservation> findReservationByReservedFrom(User name);

    //    List<Reservation> findReservationListByReservedFrom(User user);

    List<Reservation> findAllByReservedFrom(User user);

    List<Reservation> findAllByReservedFromId(Long id);

    Optional<Reservation> findAllByReservedFrom_Id(Long id);
}
