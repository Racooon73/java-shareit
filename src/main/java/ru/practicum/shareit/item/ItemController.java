package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.exception.BadRequestException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.GetItemDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.service.ItemService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/items")
@Validated
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public ItemDto addItem(@RequestBody @Valid ItemDto dto, @RequestHeader("X-Sharer-User-Id") long ownerId) throws NotFoundException {
        log.info("Получен запрос POST /items");
        return itemService.addItem(dto, ownerId);
    }

    @PatchMapping(value = "/{itemId}")
    public ItemDto patchItem(@RequestBody ItemDto dto, @PathVariable long itemId,
                             @RequestHeader("X-Sharer-User-Id") long ownerId) throws NotFoundException {
        log.info("Получен запрос PATCH /items/" + itemId);
        return itemService.patchItem(dto, ownerId, itemId);
    }

    @GetMapping(value = "/{itemId}")
    public GetItemDto getItem(@PathVariable long itemId, @RequestHeader("X-Sharer-User-Id") long ownerId)
            throws NotFoundException {
        log.info("Получен запрос GET /items/" + itemId);
        return itemService.getItem(itemId, ownerId);
    }

    @GetMapping
    public List<GetItemDto> getAllItemsByOwner(@RequestHeader("X-Sharer-User-Id") long ownerId,
                                               @RequestParam(required = false, defaultValue = "0")
                                               @Min(0) Integer from,
                                               @RequestParam(required = false, defaultValue = "10")
                                               Integer size) {
        log.info("Получен запрос GET /items");
        return itemService.getAllItemsByOwner(ownerId, from, size);
    }

    @GetMapping("/search")
    public List<ItemDto> searchItem(@RequestParam String text, @RequestHeader("X-Sharer-User-Id") long ownerId,
                                    @RequestParam(required = false, defaultValue = "0")
                                    @Min(0) Integer from,
                                    @RequestParam(required = false, defaultValue = "10")
                                    Integer size) {
        log.info("Получен запрос GET /items/search?text=" + text);
        return itemService.searchItem(text.toLowerCase(), ownerId, from, size);
    }

    @PostMapping("{itemId}/comment")
    public Comment addComment(@RequestBody @Valid Comment dto, @PathVariable long itemId,
                              @RequestHeader("X-Sharer-User-Id") long authorId)
            throws NotFoundException, BadRequestException {
        log.info("Получен запрос POST /items/" + itemId + "/comment");
        return itemService.addComment(dto, itemId, authorId);
    }
}
