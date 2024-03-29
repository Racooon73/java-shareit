package ru.practicum.shareit.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.shareit.ErrorResponse;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingState;
import ru.practicum.shareit.client.BaseClient;

import java.util.Map;

@Service
public class BookingClient extends BaseClient {
    private static final String API_PREFIX = "/bookings";

    @Autowired
    public BookingClient(@Value("${shareit-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }


    public ResponseEntity<Object> addBooking(long userId, BookingDto requestDto) {
        return post("", userId, requestDto);
    }

    public ResponseEntity<Object> getBooking(long userId, long bookingId) {
        return get("/" + bookingId, userId);
    }

    public ResponseEntity<Object> approveBooking(long bookingId, boolean approved, long bookerId) {
        return patch("/" + bookingId + "?approved=" + approved, bookerId);
    }

    public ResponseEntity<Object> getAllBookingsByBookerId(long userId, String stateParam, Integer from, Integer size) {

        BookingState state;
        if (BookingState.from(stateParam).isPresent()) {
            state = BookingState.from(stateParam).get();
        } else {
            String error = "Unknown state" + ": " + stateParam;
            return ResponseEntity.status(400).body(new ErrorResponse(error));
        }
        Map<String, Object> parameters = Map.of(
                "state", state.name(),
                "from", from,
                "size", size
        );
        return get("?state={state}&from={from}&size={size}", userId, parameters);
    }

    public ResponseEntity<Object> getAllBookingByItemsByOwnerId(long ownerId, String stateParam,
                                                                Integer from, Integer size) {
        BookingState state;
        if (BookingState.from(stateParam).isPresent()) {
            state = BookingState.from(stateParam).get();
        } else {
            String error = "Unknown state" + ": " + stateParam;
            return ResponseEntity.status(400).body(new ErrorResponse(error));
        }
        Map<String, Object> parameters = Map.of(
                "state", state.name(),
                "from", from,
                "size", size
        );
        return get("/owner?state={state}&from={from}&size={size}", ownerId, parameters);
    }
}