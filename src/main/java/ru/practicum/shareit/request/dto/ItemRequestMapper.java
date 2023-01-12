package ru.practicum.shareit.request.dto;

import ru.practicum.shareit.request.model.ItemRequest;

import java.time.LocalDateTime;

public class ItemRequestMapper {

    public static ItemRequest toItemRequest(ItemRequestDto dto, long requestorId) {
        return ItemRequest.builder()
                .description(dto.getDescription())
                .requestorId(requestorId)
                .created(LocalDateTime.now())
                .build();

    }

    public static GetItemRequestDto toGetItemRequestDto(ItemRequest request) {
        return GetItemRequestDto.builder()
                .id(request.getId())
                .created(request.getCreated())
                .description(request.getDescription())
                .build();
    }
}
