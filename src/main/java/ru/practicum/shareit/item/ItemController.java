package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;

import javax.validation.Valid;
import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public ItemDto addItem(@RequestBody @Valid ItemDto dto, @RequestHeader("X-Sharer-User-Id") long ownerId) throws NotFoundException {
        log.info("Получен запрос POST /items");
        return itemService.addItem(dto,ownerId);
    }

    @PatchMapping(value = "/{itemId}")
    public ItemDto patchItem(@RequestBody ItemDto dto, @PathVariable long itemId,
                             @RequestHeader("X-Sharer-User-Id") long ownerId) throws NotFoundException {
        log.info("Получен запрос PATCH /items/" + itemId);
        return itemService.patchItem(dto,ownerId,itemId);
    }

    @GetMapping(value = "/{itemId}")
    public ItemDto getItem(@PathVariable long itemId, @RequestHeader("X-Sharer-User-Id") long ownerId) {
        log.info("Получен запрос GET /items/" + itemId);
        return itemService.getItem(itemId,ownerId);
    }

    @GetMapping
    public List<ItemDto> getAllItemsByOwner(@RequestHeader("X-Sharer-User-Id") long ownerId) {
        log.info("Получен запрос GET /items");
        return itemService.getAllItemsByOwner(ownerId);
    }

    @GetMapping("/search")
    public List<ItemDto> searchItem(@RequestParam String text, @RequestHeader("X-Sharer-User-Id") long ownerId) {
        log.info("Получен запрос GET /items/search?text=" + text);
        return itemService.searchItem(text.toLowerCase(),ownerId);
    }
}
