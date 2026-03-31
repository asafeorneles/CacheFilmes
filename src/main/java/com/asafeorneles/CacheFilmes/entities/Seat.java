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
        @UniqueConstraint(columnNames = "room_id", "row", "column")
})
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID seatId;
    private String seatName;
    private String row;
    private String column;
    @ManyToOne
    @JoinColumn(name = "roomId")
    private Room room;
}
