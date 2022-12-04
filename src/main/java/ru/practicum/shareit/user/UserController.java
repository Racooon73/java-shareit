package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.exception.BadRequestException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.model.User;

import javax.validation.Valid;
import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping
    public User add(@Valid @RequestBody User user) throws BadRequestException {
        return userService.add(user);
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable long id) throws NotFoundException {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @PatchMapping(value = "/{id}")
    public User update(@RequestBody User user, @PathVariable long id) {
        return userService.update(user, id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable long id) {
        userService.delete(id);
    }
}
