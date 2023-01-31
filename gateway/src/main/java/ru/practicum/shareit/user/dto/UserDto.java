package ru.practicum.shareit.user.dto;

import lombok.AllArgsConstructor;
<<<<<<< HEAD
import lombok.Data;


@Data
=======
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@ToString
>>>>>>> 89d92ccb1652fb0b216b089f15f5ca410dba92cf
@AllArgsConstructor
public class UserDto {

    private long id;
    private String name;
<<<<<<< HEAD

=======
    @Email
    @NotNull
>>>>>>> 89d92ccb1652fb0b216b089f15f5ca410dba92cf
    private String email;
}
