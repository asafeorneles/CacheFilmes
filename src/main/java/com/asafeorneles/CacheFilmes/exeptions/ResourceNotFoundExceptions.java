package com.asafeorneles.CacheFilmes.exeptions;

import lombok.Getter;

@Getter
public class ResourceNotFoundExceptions extends RuntimeException {
    String developerMessage;

    public ResourceNotFoundExceptions(String message, String developerMessage) {
        super(message);
        this.developerMessage = developerMessage;
    }
}
