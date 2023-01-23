package ru.practicum.shareit.request.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * TODO Sprint add-item-requests.
 */
@Getter
@Setter
public class ItemRequestDto {

    @NotNull
    private String description;
}