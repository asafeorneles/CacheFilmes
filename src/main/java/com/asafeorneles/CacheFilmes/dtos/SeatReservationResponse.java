package com.asafeorneles.CacheFilmes.dtos;

import java.util.UUID;

public record SeatReservationResponse(
        UUID seatId,
        String name,
        int rowNumber,
        int columnNumber,
        UUID roomId,
        UUID sessionId,
        String status
) {
}
