package ru.practicum.shareit.booking.dto;

<<<<<<< HEAD:server/src/main/java/ru/practicum/shareit/booking/dto/BookingDto.java
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
=======
import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
>>>>>>> 89d92ccb1652fb0b216b089f15f5ca410dba92cf:gateway/src/main/java/ru/practicum/shareit/booking/dto/BookingDto.java

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private long id;
    private long itemId;

    private LocalDateTime start;

    private LocalDateTime end;
}