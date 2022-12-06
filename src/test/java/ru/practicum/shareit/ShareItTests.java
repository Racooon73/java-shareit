package ru.practicum.shareit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.practicum.shareit.exception.BadRequestException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.item.service.ItemServiceImpl;
import ru.practicum.shareit.item.storage.InMemoryItemStorage;
import ru.practicum.shareit.item.storage.ItemStorage;
import ru.practicum.shareit.user.UserService;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.storage.InMemoryUserStorage;
import ru.practicum.shareit.user.storage.UserStorage;

@SpringBootTest
class ShareItTests {

	UserStorage userStorage;
	UserService userService;
	ItemService itemService;
	ItemStorage itemStorage;
	User user;
	ItemDto itemDto;

	@BeforeEach
	void beforeEach() {
		userStorage = new InMemoryUserStorage();
		userService = new UserService(userStorage);
		itemStorage = new InMemoryItemStorage();
		itemService = new ItemServiceImpl(itemStorage,userStorage);
		user = new User(1,"Test","test@test.com");
		itemDto = new ItemDto(1,"TestItem","DescriptionTest", true);
	}

	@Test
	void addNewUser() throws BadRequestException, NotFoundException {
		userService.add(user);
		Assertions.assertEquals(userService.getUserById(1),user);
	}

	@Test
	void updateUser() throws BadRequestException, NotFoundException {
		userService.add(user);
		User newUser = new User(1,"Updated","test@test.com");
		userService.update(newUser,1);
		Assertions.assertEquals(userService.getUserById(1),newUser);
	}

	@Test
	void deleteUser() throws BadRequestException {
		userService.add(user);
		userService.delete(1);
		Assertions.assertThrows(NotFoundException.class,() -> userService.getUserById(1));
	}

	@Test
	void addItem() throws NotFoundException, BadRequestException {
		userService.add(user);
		itemService.addItem(itemDto,1);
		Assertions.assertEquals(itemDto.getName(),itemService.getItem(1,1).getName());
	}

	@Test
	void patchItem() throws NotFoundException, BadRequestException {
		userService.add(user);
		itemService.addItem(itemDto,1);
		ItemDto newItemDto = new ItemDto(1,"Patch"," ",true);
		itemService.patchItem(newItemDto,1,1);
		Assertions.assertEquals(newItemDto,itemService.getItem(1,1));
	}


}
