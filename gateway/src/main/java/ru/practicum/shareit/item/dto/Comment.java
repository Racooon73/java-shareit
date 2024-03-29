package ru.practicum.shareit.item.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Comment {

    private long id;
    @NotBlank
    @NotNull
    private String text;
    private LocalDateTime created;
    private long itemId;
    private long authorId;
    private String authorName;
}