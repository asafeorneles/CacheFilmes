package com.asafeorneles.CacheFilmes.services;

import com.asafeorneles.CacheFilmes.dtos.RoomRequest;
import com.asafeorneles.CacheFilmes.dtos.RoomResponse;
import com.asafeorneles.CacheFilmes.entities.Room;
import com.asafeorneles.CacheFilmes.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final SeatService seatService;

    public RoomResponse create(RoomRequest roomRequest) {
        Room room = Room.builder()
                .name(roomRequest.name())
                .rowsQuantity(roomRequest.rowsQuantity())
                .columnsQuantity(roomRequest.columnsQuantity())
                .build();

        seatService.createAndSetSeats(room);

        roomRepository.save(room);

        return new RoomResponse(room.getRoomId(), room.getName(), seatService.createSeatsResponse(room.getSeats()));
    }

    public List<RoomResponse> listAll() {
        List<Room> rooms = roomRepository.findAll();

        return rooms.stream()
                .map(room -> new RoomResponse(room.getRoomId(), room.getName(), seatService.createSeatsResponse(room.getSeats())))
                .toList();
    }
}
