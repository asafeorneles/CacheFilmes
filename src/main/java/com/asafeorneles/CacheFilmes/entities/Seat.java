package com.asafeorneles.CacheFilmes.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_seats", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"room_id", "seat_row", "seat_column"})
})
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "seat_id")
    private UUID seatId;

    private String name;

    @Column(name = "seat_row")
    private int rowNumber;

    @Column(name = "seat_column")
    private int columnNumber;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    public String getColumnLetter() {
        return String.valueOf((char) ('A' + columnNumber - 1));
    }

    public String getSeatName() {
        return getColumnLetter() + rowNumber;
    }
}
