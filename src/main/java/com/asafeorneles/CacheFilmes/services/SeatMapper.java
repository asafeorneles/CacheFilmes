package com.asafeorneles.CacheFilmes.services;

public class SeatMapper {
    public static int getColumnNumber(String seatName) {
        char letter = seatName.charAt(0);
        return letter - 'A' + 1;
    }

    public static int getRowNumber(String seatName) {
        return Integer.parseInt(seatName.substring(1));
    }
}
