package ru.practicum.shareit.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@AllArgsConstructor
public class UserDto {

    private long id;
    private String name;
    @Email
    @NotNull
    private String email;
}
