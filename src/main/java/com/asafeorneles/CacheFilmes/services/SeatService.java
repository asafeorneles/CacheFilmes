package com.asafeorneles.CacheFilmes.services;

import com.asafeorneles.CacheFilmes.dtos.SeatDetailsResponse;
import com.asafeorneles.CacheFilmes.dtos.SeatResponse;
import com.asafeorneles.CacheFilmes.entities.Room;
import com.asafeorneles.CacheFilmes.entities.Seat;
import com.asafeorneles.CacheFilmes.repositories.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;

    public void createAndSetSeats(Room room) {
        List<Seat> seats = new ArrayList<>();

        for (int i = 1; i <= room.getRowsQuantity(); i++) {
            for (int j = 1; j <= room.getColumnsQuantity(); j++) {
                Seat seat = Seat.builder()
                        .room(room)
                        .rowNumber(i)
                        .columnNumber(j)
                        .build();

                seats.add(seat);
            }
        }

        seats.forEach(seat -> seat.setName(seat.getSeatName()));
        room.setSeats(seats);
    }

    public List<SeatResponse> createSeatsResponse(List<Seat> seats) {


        return seats.stream()
                .map(seat -> new SeatResponse(seat.getSeatName()))
                .sorted(Comparator.comparing(SeatResponse::name))
                .toList();
    }

    public List<SeatDetailsResponse> listAllByRoom(UUID roomId) {
        return seatRepository.findByRoom_RoomId(roomId)
                .stream()
                .map(seat -> new SeatDetailsResponse(seat.getSeatId(), seat.getSeatName(), roomId, seat.getRowNumber(), seat.getColumnNumber()))
                .sorted(Comparator.comparing(SeatDetailsResponse::name))
                .toList();
    }
}
