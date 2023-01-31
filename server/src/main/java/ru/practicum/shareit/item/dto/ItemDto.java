package ru.practicum.shareit.item.dto;

import lombok.*;


/**
 * TODO Sprint add-controllers.
 */
@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class ItemDto {
    private long id;

    private String name;

    private String description;

    private Boolean available;

<<<<<<< HEAD
    private long requestId;
=======
    private Long requestId;
>>>>>>> 89d92ccb1652fb0b216b089f15f5ca410dba92cf

}
