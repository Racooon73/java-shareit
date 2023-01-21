package ru.practicum.shareit.item.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;
    private LocalDateTime created;
    @Column(name = "item_id")
    private long itemId;
    @Column(name = "author_id")
    private long authorId;
    @Column(name = "author_name")
    private String authorName;
}
