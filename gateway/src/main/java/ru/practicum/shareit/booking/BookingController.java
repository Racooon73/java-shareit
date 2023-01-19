package ru.practicum.shareit.booking;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingState;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;


/**
 * TODO Sprint add-bookings.
 */
@Slf4j
@RestController
@RequestMapping(path = "/bookings")
@AllArgsConstructor
@Validated
public class BookingController {
    private final BookingClient bookingClient;

    @PostMapping
    public ResponseEntity<Object> addBooking(@Valid @RequestBody BookingDto dto,
                                             @RequestHeader("X-Sharer-User-Id") long bookerId) {
        log.info("Получен запрос POST /bookings");
        return bookingClient.addBooking(bookerId,dto);
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<Object> approveBooking(@PathVariable long bookingId, @RequestParam boolean approved,
                                                 @RequestHeader("X-Sharer-User-Id") long bookerId) {
        log.info("Получен запрос PATCH /bookings/" + bookingId + "?approved=" + approved);
        return bookingClient.approveBooking(bookingId, approved, bookerId);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Object> getBooking(@PathVariable long bookingId, @RequestHeader("X-Sharer-User-Id") long bookerId) {
        log.info("Получен запрос GET /bookings/" + bookingId);
        return bookingClient.getBooking(bookingId, bookerId);
    }

    @GetMapping
    public ResponseEntity<Object> getAllBookingsByBookerId(@RequestHeader("X-Sharer-User-Id") long bookerId,
                                                           @RequestParam(defaultValue = "ALL") BookingState state,
                                                           @RequestParam(required = false, defaultValue = "0")
                                                           @PositiveOrZero Integer from,
                                                           @RequestParam(required = false, defaultValue = "10")
                                                           Integer size) {
        log.info("Получен запрос GET /bookings?state=" + state.toString());
        return bookingClient.getAllBookingsByBookerId(bookerId, state, from, size);
    }

    @GetMapping("/owner")
    public ResponseEntity<Object> getAllBookingItemsByBookerId(@RequestHeader("X-Sharer-User-Id") long ownerId,
                                                               @RequestParam(defaultValue = "ALL") BookingState state,
                                                               @RequestParam(required = false, defaultValue = "0")
                                                               @PositiveOrZero Integer from,
                                                               @RequestParam(required = false, defaultValue = "10")
                                                               Integer size) {
        log.info("Получен запрос GET /bookings/owner?state=" + state.toString());
        return bookingClient.getAllBookingByItemsByOwnerId(ownerId, state, from, size);
    }

}