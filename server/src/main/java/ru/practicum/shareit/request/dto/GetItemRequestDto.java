package ru.practicum.shareit.request.dto;

import lombok.*;
import ru.practicum.shareit.item.dto.ItemDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class GetItemRequestDto {
    private long id;
    private String description;
    private LocalDateTime created;
    private List<ItemDto> items;

}
