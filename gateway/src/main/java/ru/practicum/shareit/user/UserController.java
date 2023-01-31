package ru.practicum.shareit.user;

public class UserController {
}
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;

import javax.validation.Valid;


/**
 * TODO Sprint add-controllers.
 */
@Slf4j
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserClient userClient;

    @PostMapping
    public ResponseEntity<Object> add(@Valid @RequestBody UserDto user) {
        log.info("Получен запрос POST /users");
        return userClient.add(user);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable long id) {
        log.info("Получен запрос GET /users/" + id);
        return userClient.getUserById(id);
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        log.info("Получен запрос GET /users");
        return userClient.getAllUsers();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> update(@RequestBody UserDto user, @PathVariable long id) {
        log.info("Получен запрос PATCH /users/" + id);
        return userClient.update(user, id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable long id) {
        log.info("Получен запрос DELETE /users/" + id);
        userClient.deleteUser(id);
    }
}
>>>>>>> 89d92ccb1652fb0b216b089f15f5ca410dba92cf
