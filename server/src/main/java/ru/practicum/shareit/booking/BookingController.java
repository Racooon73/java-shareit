package ru.practicum.shareit.booking;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.FullBookingDto;
import ru.practicum.shareit.booking.model.enums.BookingState;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.exception.BadRequestException;
import ru.practicum.shareit.exception.NotFoundException;

import java.util.List;

/**
 * TODO Sprint add-bookings.
 */
@Slf4j
@RestController
@RequestMapping(path = "/bookings")
@AllArgsConstructor
@Validated
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public FullBookingDto addBooking(@RequestBody BookingDto dto,
                                     @RequestHeader("X-Sharer-User-Id") long bookerId)
            throws NotFoundException, BadRequestException {
        log.info("Получен запрос POST /bookings");
        return bookingService.addBooking(dto, bookerId);
    }

    @PatchMapping("/{bookingId}")
    public FullBookingDto approveBooking(@PathVariable long bookingId, @RequestParam boolean approved,
                                         @RequestHeader("X-Sharer-User-Id") long bookerId)
            throws NotFoundException, BadRequestException {
        log.info("Получен запрос PATCH /bookings/" + bookingId + "?approved=" + approved);
        return bookingService.approveBooking(bookingId, approved, bookerId);
    }

    @GetMapping("/{bookingId}")
    public FullBookingDto getBooking(@PathVariable long bookingId, @RequestHeader("X-Sharer-User-Id") long bookerId)
            throws NotFoundException {
        log.info("Получен запрос GET /bookings/" + bookingId);
        return bookingService.getBooking(bookingId, bookerId);
    }

    @GetMapping
    public List<FullBookingDto> getAllBookingsByBookerId(@RequestHeader("X-Sharer-User-Id") long bookerId,
                                                         @RequestParam(defaultValue = "ALL") BookingState state,
                                                         @RequestParam(required = false, defaultValue = "0") Integer from,
                                                         @RequestParam(required = false, defaultValue = "10")
                                                         Integer size) {
        log.info("Получен запрос GET /bookings?state=" + state.toString());
        return bookingService.getAllBookingsByBookerId(bookerId, state, from, size);
    }

    @GetMapping("/owner")
    public List<FullBookingDto> getAllBookingItemsByBookerId(@RequestHeader("X-Sharer-User-Id") long ownerId,
                                                             @RequestParam(defaultValue = "ALL") BookingState state,
                                                             @RequestParam(required = false, defaultValue = "0")
                                                                 Integer from,
                                                             @RequestParam(required = false, defaultValue = "10")
                                                             Integer size) {
        log.info("Получен запрос GET /bookings/owner?state=" + state.toString());
        return bookingService.getAllBookingByItemsByOwnerId(ownerId, state, from, size);
    }

}
