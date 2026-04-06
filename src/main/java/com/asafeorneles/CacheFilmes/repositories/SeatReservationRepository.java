package com.asafeorneles.CacheFilmes.repositories;

import com.asafeorneles.CacheFilmes.entities.Seat;
import com.asafeorneles.CacheFilmes.entities.SeatReservation;
import com.asafeorneles.CacheFilmes.entities.Session;
import com.asafeorneles.CacheFilmes.enums.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SeatReservationRepository extends JpaRepository<SeatReservation, UUID> {
   List<SeatReservation> findBySession(Session session);

    Optional<SeatReservation> findBySeatAndSession(Seat seat, Session session);

}
