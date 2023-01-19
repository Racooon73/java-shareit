package ru.practicum.shareit.item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.model.Comment;


import java.util.List;

@Data
@AllArgsConstructor
public class GetItemDto {
    private long id;

    private String name;

    private String description;

    private Boolean available;
    private Booking lastBooking;
    private Booking nextBooking;
    private List<Comment> comments;
}
