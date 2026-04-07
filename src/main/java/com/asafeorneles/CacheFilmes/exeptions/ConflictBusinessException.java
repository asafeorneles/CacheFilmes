package com.asafeorneles.CacheFilmes.exeptions;

import lombok.Getter;

@Getter
public class ConflictBusinessException extends RuntimeException {

    String developerMessage;

    public ConflictBusinessException(String message, String developerMessage) {
        super(message);
        this.developerMessage = developerMessage;
    }
}
