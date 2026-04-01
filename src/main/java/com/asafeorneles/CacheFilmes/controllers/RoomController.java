package com.asafeorneles.CacheFilmes.controllers;

import com.asafeorneles.CacheFilmes.dtos.RoomRequest;
import com.asafeorneles.CacheFilmes.dtos.RoomResponse;
import com.asafeorneles.CacheFilmes.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
