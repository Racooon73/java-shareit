package ru.practicum.shareit.user;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.BadRequestException;
import ru.practicum.shareit.exception.ConflictException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.storage.UserStorage;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserStorage userStorage;

    public User add(User user) throws BadRequestException {

        return userStorage.add(user);
    }

    public User getUserById(long id) throws NotFoundException {
        return userStorage.get(id);
    }

    public List<User> getAllUsers(){
        return new ArrayList<>(userStorage.getAll());
    }

    public User update(User user, long id) {
        return userStorage.update(user,id);
    }


    public void delete(long id) {
        userStorage.delete(id);
    }


}
