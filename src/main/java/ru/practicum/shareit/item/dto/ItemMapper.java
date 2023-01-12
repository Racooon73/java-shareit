package ru.practicum.shareit.item.dto;

import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public class ItemMapper {

    public static GetItemDto toGetItemDto(Item item, Booking last, Booking near,
                                          List<Comment> comments) {
        return new GetItemDto(item.getId(),
                item.getName(),
                item.getDescription(),
                item.isAvailable(),
                last,
                near,
                comments
        );
    }

    public static ItemDto toItemDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .available(item.isAvailable())
                .description(item.getDescription())
                .name(item.getName())
                .requestId(item.getRequestId())
                .build();

    }

    public static Item toItem(ItemDto dto, long owner) {
        return new Item(dto.getId(),
                dto.getName(),
                dto.getDescription(),
                dto.getAvailable(),
                owner,
                dto.getRequestId()
        );
    }

}
