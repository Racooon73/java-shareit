package ru.practicum.shareit.item.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @NotNull
    private String text;
    private LocalDateTime created;
    @Column(name = "item_id")
    private long itemId;
    @Column(name = "author_id")
    private long authorId;
    private String authorName;
}
