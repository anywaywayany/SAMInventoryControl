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
    Optional<Reservation> findReservationByReservedFrom(User name);

        List<Reservation> findReservationListByReservedFrom(User user);

    Stream<Reservation> findAllByReservedFrom(User user);
    Stream<Reservation> findReservationsByReservedFrom(User user);

    List<Reservation> findAllByReservedFromId(Long id);

    Optional<Reservation> findAllByReservedFrom_Id(Long id);

}
