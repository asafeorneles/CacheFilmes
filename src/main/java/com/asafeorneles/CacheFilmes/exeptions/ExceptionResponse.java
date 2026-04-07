package com.asafeorneles.CacheFilmes.exeptions;

import java.time.LocalDateTime;

public record ExceptionResponse(
        String title,
        int code,
        String description,
        String developerMessage,
        LocalDateTime timestamp
) {
}
