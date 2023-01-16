package ru.practicum.shareit.request;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.dto.GetItemRequestDto;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.request.service.ItemRequestService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * TODO Sprint add-item-requests.
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/requests")
@Validated
public class ItemRequestController {

    private final ItemRequestService itemRequestService;

    @PostMapping
    public ItemRequest addRequest(@Valid @RequestBody ItemRequestDto request,
                                  @RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("Получен запрос POST /requests");
        return itemRequestService.addRequest(request, userId);
    }

    @GetMapping
    public List<GetItemRequestDto> getRequests(@RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("Получен запрос GET /requests");
        return itemRequestService.getRequests(userId);
    }

    @GetMapping("/all")
    public List<GetItemRequestDto> getRequestsPageable(@RequestHeader("X-Sharer-User-Id") long userId,
                                                       @RequestParam(required = false, defaultValue = "0")
                                                       @Min(0) Integer from,
                                                       @RequestParam(required = false, defaultValue = "1")
                                                           Integer size) {
        log.info("Получен запрос GET /requests/all?from=" + from + "&size=" + size);
        return itemRequestService.getRequestsPageable(userId, from, size);
    }

    @GetMapping("/{requestId}")
    public GetItemRequestDto getRequestById(@RequestHeader("X-Sharer-User-Id") long userId, @PathVariable long requestId) {
        log.info("Получен запрос GET /requests/" + requestId);
        return itemRequestService.getRequestById(userId, requestId);
    }
}
