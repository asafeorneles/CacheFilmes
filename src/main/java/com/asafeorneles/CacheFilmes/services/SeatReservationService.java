package com.asafeorneles.CacheFilmes.services;

import com.asafeorneles.CacheFilmes.entities.Reservation;
import com.asafeorneles.CacheFilmes.entities.Seat;
import com.asafeorneles.CacheFilmes.entities.SeatReservation;
import com.asafeorneles.CacheFilmes.entities.Session;
import com.asafeorneles.CacheFilmes.enums.SeatStatus;
import com.asafeorneles.CacheFilmes.exeptions.ConflictBusinessException;
import com.asafeorneles.CacheFilmes.exeptions.ResourceNotFoundExceptions;
import com.asafeorneles.CacheFilmes.repositories.SeatRepository;
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
    private final SeatRepository seatRepository;

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
                    .orElseThrow(() -> new ResourceNotFoundExceptions("SeatReservation not found.",
                            "SeatReservation not found by this seat: " + seat.getSeatId() +
                            " and this session: " + session.getSessionId() + " in makeReservation method."));
            seatReservation.setStatus(SeatStatus.RESERVED); // Ainda não pagou, portanto, vai ficar reservada por alguns minutos
            seatReservation.setReservation(reservation);
            seatReservations.add(seatReservation);
        }
        return seatReservations;
    }

    public void verifyStatus(List<Seat> seats, Session session) {

        for (Seat seat : seats) {

            SeatReservation seatReservation = seatReservationRepository.findBySeatAndSession(seat, session)
                    .orElseThrow(() -> new ResourceNotFoundExceptions("SeatReservation not found.",
                            "SeatReservation not found by this seat: " + seat.getSeatId() +
                            " and this session: " + session.getSessionId() + " in verifyStatus method."));

            if (seatReservation.getStatus() == SeatStatus.RESERVED || seatReservation.getStatus() == SeatStatus.OCCUPIED) {
                throw new ConflictBusinessException(
                        "seat already occupied or reserved. Please, choose other seat.", null);
            }
        }
    }
}
