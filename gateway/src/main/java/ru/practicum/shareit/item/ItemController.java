package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ru.practicum.shareit.item.dto.Comment;
import ru.practicum.shareit.item.dto.ItemDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * TODO Sprint add-controllers.
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/items")
@Validated
public class ItemController {

    private final ItemClient itemClient;

    @PostMapping
    public ResponseEntity<Object> addItem(@RequestBody @Valid ItemDto dto, @RequestHeader("X-Sharer-User-Id") long ownerId) {
        log.info("Получен запрос POST /items");
        return itemClient.addItem(dto, ownerId);
    }

    @PatchMapping(value = "/{itemId}")
    public ResponseEntity<Object> patchItem(@RequestBody ItemDto dto, @PathVariable long itemId,
                                            @RequestHeader("X-Sharer-User-Id") long ownerId) {
        log.info("Получен запрос PATCH /items/" + itemId);
        return itemClient.patchItem(dto, ownerId, itemId);
    }

    @GetMapping(value = "/{itemId}")
    public ResponseEntity<Object> getItem(@PathVariable long itemId, @RequestHeader("X-Sharer-User-Id") long ownerId) {
        log.info("Получен запрос GET /items/" + itemId);
        return itemClient.getItem(itemId, ownerId);
    }

    @GetMapping
    public ResponseEntity<Object> getAllItemsByOwner(@RequestHeader("X-Sharer-User-Id") long ownerId,
                                                     @RequestParam(required = false, defaultValue = "0")
                                                     @PositiveOrZero Integer from,
                                                     @RequestParam(required = false, defaultValue = "10")
                                                     Integer size) {
        log.info("Получен запрос GET /items");
        return itemClient.getAllItemsByOwner(ownerId, from, size);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchItem(@NotNull @RequestParam String text, @RequestHeader("X-Sharer-User-Id") long ownerId,
                                             @RequestParam(required = false, defaultValue = "0")
                                             @PositiveOrZero Integer from,
                                             @RequestParam(required = false, defaultValue = "10")
                                             Integer size) {
        log.info("Получен запрос GET /items/search?text=" + text);
        return itemClient.searchItem(text.toLowerCase(), ownerId, from, size);
    }

    @PostMapping("{itemId}/comment")
    public ResponseEntity<Object> addComment(@RequestBody @Valid Comment dto, @PathVariable long itemId,
                                             @RequestHeader("X-Sharer-User-Id") long authorId) {
        log.info("Получен запрос POST /items/" + itemId + "/comment");

        return itemClient.addComment(dto, itemId, authorId);
    }
}