package ru.practicum.shareit.booking;

<<<<<<< HEAD
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookItemRequestDto;
import ru.practicum.shareit.booking.dto.BookingState;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Controller
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
@Slf4j
=======
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;


/**
 * TODO Sprint add-bookings.
 */
@Slf4j
@RestController
@RequestMapping(path = "/bookings")
@AllArgsConstructor
>>>>>>> 89d92ccb1652fb0b216b089f15f5ca410dba92cf
@Validated
public class BookingController {
    private final BookingClient bookingClient;

<<<<<<< HEAD
    @GetMapping
    public ResponseEntity<Object> getBookings(@RequestHeader("X-Sharer-User-Id") long userId,
                                              @RequestParam(name = "state", defaultValue = "all") String stateParam,
                                              @PositiveOrZero @RequestParam(name = "from", defaultValue = "0") Integer from,
                                              @Positive @RequestParam(name = "size", defaultValue = "10") Integer size) {
        BookingState state = BookingState.from(stateParam)
                .orElseThrow(() -> new IllegalArgumentException("Unknown state: " + stateParam));
        log.info("Get booking with state {}, userId={}, from={}, size={}", stateParam, userId, from, size);
        return bookingClient.getBookings(userId, state, from, size);
    }

    @PostMapping
    public ResponseEntity<Object> bookItem(@RequestHeader("X-Sharer-User-Id") long userId,
                                           @RequestBody @Valid BookItemRequestDto requestDto) {
        log.info("Creating booking {}, userId={}", requestDto, userId);
        return bookingClient.bookItem(userId, requestDto);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Object> getBooking(@RequestHeader("X-Sharer-User-Id") long userId,
                                             @PathVariable Long bookingId) {
        log.info("Get booking {}, userId={}", bookingId, userId);
        return bookingClient.getBooking(userId, bookingId);
    }
=======
    @PostMapping
    public ResponseEntity<Object> addBooking(@Valid @RequestBody BookingDto dto,
                                             @RequestHeader("X-Sharer-User-Id") long bookerId) {
        log.info("Получен запрос POST /bookings");
        return bookingClient.addBooking(bookerId, dto);
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<Object> approveBooking(@PathVariable long bookingId, @RequestParam boolean approved,
                                                 @RequestHeader("X-Sharer-User-Id") long bookerId) {
        log.info("Получен запрос PATCH /bookings/" + bookingId + "?approved=" + approved);
        return bookingClient.approveBooking(bookingId, approved, bookerId);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Object> getBooking(@PathVariable long bookingId,
                                             @RequestHeader("X-Sharer-User-Id") long bookerId) {
        log.info("Получен запрос GET /bookings/" + bookingId);
        return bookingClient.getBooking(bookerId, bookingId);
    }

    @GetMapping
    public ResponseEntity<Object> getAllBookingsByBookerId(@RequestHeader("X-Sharer-User-Id") long bookerId,
                                                           @RequestParam(name = "state", defaultValue = "ALL")
                                                           String stateParam,
                                                           @RequestParam(required = false, defaultValue = "0")
                                                           @PositiveOrZero Integer from,
                                                           @RequestParam(required = false, defaultValue = "10")
                                                           Integer size) {

        log.info("Получен запрос GET /bookings?state=" + stateParam);
        return bookingClient.getAllBookingsByBookerId(bookerId, stateParam, from, size);
    }

    @GetMapping("/owner")
    public ResponseEntity<Object> getAllBookingItemsByBookerId(@RequestHeader("X-Sharer-User-Id") long ownerId,
                                                               @RequestParam(name = "state", defaultValue = "ALL")
                                                               String stateParam,
                                                               @RequestParam(required = false, defaultValue = "0")
                                                               @PositiveOrZero Integer from,
                                                               @RequestParam(required = false, defaultValue = "10")
                                                               Integer size) {

        log.info("Получен запрос GET /bookings/owner?state=" + stateParam);
        return bookingClient.getAllBookingByItemsByOwnerId(ownerId, stateParam, from, size);
    }

>>>>>>> 89d92ccb1652fb0b216b089f15f5ca410dba92cf
}