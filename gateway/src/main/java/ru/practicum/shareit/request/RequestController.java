package ru.practicum.shareit.request;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.dto.ItemRequestDto;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;


/**
 * TODO Sprint add-item-requests.
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/requests")
@Validated
public class RequestController {

    private final RequestClient requestClient;

    @PostMapping
    public ResponseEntity<Object> addRequest(@Valid @RequestBody ItemRequestDto request,
                                             @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("Получен запрос POST /requests");
        return requestClient.addRequest(request, userId);
    }

    @GetMapping
    public ResponseEntity<Object> getRequests(@RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("Получен запрос GET /requests");
        return requestClient.getRequests(userId);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getRequestsPageable(@RequestHeader("X-Sharer-User-Id") long userId,
                                                      @RequestParam(required = false, defaultValue = "0")
                                                      @PositiveOrZero Integer from,
                                                      @RequestParam(required = false, defaultValue = "1")
                                                      Integer size) {
        log.info("Получен запрос GET /requests/all?from=" + from + "&size=" + size);
        return requestClient.getRequestsPageable(userId, from, size);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<Object> getRequestById(@RequestHeader("X-Sharer-User-Id") long userId, @PathVariable long requestId) {
        log.info("Получен запрос GET /requests/" + requestId);
        return requestClient.getRequestById(userId, requestId);
    }
}