package ru.practicum.shareit.booking.model;

import lombok.*;
import ru.practicum.shareit.booking.model.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * TODO Sprint add-bookings.
 */
@Getter
@Setter
@Builder
@Entity
@Table(name = "Bookings")
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "start_date")
    private LocalDateTime start;
    @Column(name = "end_date")
    private LocalDateTime end;
    @Column(name = "item_id")
    private long itemId;
    @Column(name = "booker_id")
    private long bookerId;
    private Status status;
}
