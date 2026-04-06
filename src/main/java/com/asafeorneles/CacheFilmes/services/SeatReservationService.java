package com.asafeorneles.CacheFilmes.services;

import com.asafeorneles.CacheFilmes.entities.Reservation;
import com.asafeorneles.CacheFilmes.entities.Seat;
import com.asafeorneles.CacheFilmes.entities.SeatReservation;
import com.asafeorneles.CacheFilmes.entities.Session;
import com.asafeorneles.CacheFilmes.enums.SeatStatus;
import com.asafeorneles.CacheFilmes.repositories.SeatReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatReservationService {

    private final SeatReservationRepository seatReservationRepository;

    public List<SeatReservation> create(List<Seat> seats, Session session) {

        List<SeatReservation> seatReservations = new ArrayList<>();

        for (Seat seat : seats) {
            SeatReservation seatReservation = SeatReservation.builder()
                    .seat(seat)
                    .session(session)
                    .status(SeatStatus.AVAILABLE)
                    .build();
            seatReservations.add(seatReservation);
        }
        seatReservationRepository.saveAll(seatReservations);
        return seatReservations;
    }

    public List<SeatReservation> makeReservation(List<Seat> seats, Session session, Reservation reservation) {

        verifyStatus(seats, session);

        List<SeatReservation> seatReservations = new ArrayList<>();

        for (Seat seat : seats) {
            SeatReservation seatReservation = seatReservationRepository.findBySeatAndSession(seat, session)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            seatReservation.setStatus(SeatStatus.RESERVED); // Ainda não pagou, portanto, vai ficar reservada por alguns minutos
            seatReservation.setReservation(reservation);
            seatReservations.add(seatReservation);
        }
        return seatReservations;
    }

    public void verifyStatus(List<Seat> seats, Session session) {

        for (Seat seat : seats) {

            SeatReservation seatReservation = seatReservationRepository.findBySeatAndSession(seat, session)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

            if (seatReservation.getStatus() == SeatStatus.RESERVED || seatReservation.getStatus() == SeatStatus.OCCUPIED) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Assento já está reservado");
            }
        }
    }
}
