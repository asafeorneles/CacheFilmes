package com.asafeorneles.CacheFilmes.controllers;

import com.asafeorneles.CacheFilmes.dtos.RoomRequest;
import com.asafeorneles.CacheFilmes.dtos.RoomResponse;
import com.asafeorneles.CacheFilmes.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomResponse> create(@RequestBody RoomRequest roomRequest) {
        RoomResponse roomResponse = roomService.create(roomRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(roomResponse);
    }

    @GetMapping
    public ResponseEntity<List<RoomResponse>> listAll(){
        List<RoomResponse> roomResponses = roomService.listAll();
        return ResponseEntity.status(HttpStatus.OK).body(roomResponses);
    }
}
