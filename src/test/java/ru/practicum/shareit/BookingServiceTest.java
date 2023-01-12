package ru.practicum.shareit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingMapper;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.enums.BookingState;
import ru.practicum.shareit.booking.model.enums.Status;
import ru.practicum.shareit.booking.service.BookingServiceImpl;
import ru.practicum.shareit.booking.storage.BookingRepository;
import ru.practicum.shareit.exception.BadRequestException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.storage.ItemRepository;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.storage.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ItemRepository itemRepository;
    @InjectMocks
    BookingServiceImpl bookingService;
    @InjectMocks
    BookingMapper mapper;

    @Test
    void addBooking() throws NotFoundException, BadRequestException {
        long itemId = 1L;
        long ownerId = 2L;
        long bookerId = 1L;

        BookingDto dto = new BookingDto(1, itemId, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        User newUser = new User(1, "test", "test@test.com");
        ItemDto itemDto = new ItemDto(itemId, "TestItem", "DescriptionTest", true, 0);
        Item item = ItemMapper.toItem(itemDto, ownerId);
        when(itemRepository.findById(anyLong()))
                .thenReturn(Optional.of(item));


        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(newUser));
        assertThrows(NullPointerException.class, () -> bookingService.addBooking(dto, bookerId));

    }

    @Test
    void approveBooking() throws NotFoundException, BadRequestException {
        long itemId = 1L;
        long ownerId = 1L;
        long bookerId = 1L;

        BookingDto dto = new BookingDto(1, itemId, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        User newUser = new User(1, "test", "test@test.com");
        ItemDto itemDto = new ItemDto(itemId, "TestItem", "DescriptionTest", true, 0);
        Item item = ItemMapper.toItem(itemDto, ownerId);

        when(itemRepository.findById(anyLong()))
                .thenReturn(Optional.of(item));


        when(bookingRepository.findById(anyLong()))
                .thenReturn(Optional.of(BookingMapper.toBooking(dto, bookerId, Status.WAITING)));
        assertThrows(NullPointerException.class, () -> bookingService.approveBooking(1, true, itemId));

    }

    @Test
    void getBooking() throws NotFoundException, BadRequestException {
        long itemId = 1L;
        long ownerId = 1L;
        long bookerId = 1L;

        BookingDto dto = new BookingDto(1, itemId, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        User newUser = new User(1, "test", "test@test.com");
        ItemDto itemDto = new ItemDto(itemId, "TestItem", "DescriptionTest", true, 0);
        Item item = ItemMapper.toItem(itemDto, ownerId);


        when(bookingRepository.findById(anyLong()))
                .thenReturn(Optional.of(BookingMapper.toBooking(dto, bookerId, Status.WAITING)));
        assertThrows(NoSuchElementException.class, () -> bookingService.getBooking(1, bookerId));

    }

    @Test
    void getBookingNotFound() throws NotFoundException, BadRequestException {
        long itemId = 1L;
        long ownerId = 1L;
        long bookerId = 1L;

        BookingDto dto = new BookingDto(1, itemId, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        User newUser = new User(1, "test", "test@test.com");
        ItemDto itemDto = new ItemDto(itemId, "TestItem", "DescriptionTest", true, 0);
        Item item = ItemMapper.toItem(itemDto, ownerId);


        when(bookingRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> bookingService.getBooking(1, bookerId));

    }

    @Test
    void getAllBookings() throws NotFoundException, BadRequestException {
        long itemId = 1L;
        long ownerId = 1L;
        long bookerId = 1L;

        BookingDto dto = new BookingDto(1, itemId, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        User newUser = new User(1, "test", "test@test.com");
        ItemDto itemDto = new ItemDto(itemId, "TestItem", "DescriptionTest", true, 0);
        Item item = ItemMapper.toItem(itemDto, ownerId);
        final Page<Item> page = new PageImpl<>(List.of(item));
        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(newUser));


        when(bookingRepository.findAllByBookerId(anyLong(), any()))
                .thenReturn(List.of(BookingMapper.toBooking(dto, bookerId, Status.WAITING)));

        when(bookingRepository.findByBookerIdAndEndAfter(anyLong(), any(), any()))
                .thenReturn(List.of(BookingMapper.toBooking(dto, bookerId, Status.WAITING)));
        when(bookingRepository.findByBookerIdAndStartAfter(anyLong(), any(), any()))
                .thenReturn(List.of(BookingMapper.toBooking(dto, bookerId, Status.WAITING)));
        when(bookingRepository.findByBookerIdAndEndIsBeforeAndStartIsAfter(anyLong(), any(), any(), any()))
                .thenReturn(List.of(BookingMapper.toBooking(dto, bookerId, Status.WAITING)));
        when(bookingRepository.findAllByBookerIdAndStatus(anyLong(), any(), any()))
                .thenReturn(List.of(BookingMapper.toBooking(dto, bookerId, Status.WAITING)));

        assertThrows(NoSuchElementException.class, () -> bookingService.getAllBookingsByBookerId(1,
                BookingState.ALL, 0, 10));
        assertThrows(NoSuchElementException.class, () -> bookingService.getAllBookingsByBookerId(1,
                BookingState.PAST, 0, 10));
        assertThrows(NoSuchElementException.class, () -> bookingService.getAllBookingsByBookerId(1,
                BookingState.FUTURE, 0, 10));
        assertThrows(NoSuchElementException.class, () -> bookingService.getAllBookingsByBookerId(1,
                BookingState.CURRENT, 0, 10));
        assertThrows(NoSuchElementException.class, () -> bookingService.getAllBookingsByBookerId(1,
                BookingState.WAITING, 0, 10));
        assertThrows(NoSuchElementException.class, () -> bookingService.getAllBookingsByBookerId(1,
                BookingState.REJECTED, 0, 10));

    }

    @Test
    void getAllBookingsNotFoundUser() throws NotFoundException, BadRequestException {
        long itemId = 1L;
        long ownerId = 1L;
        long bookerId = 1L;

        BookingDto dto = new BookingDto(1, itemId, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        User newUser = new User(1, "test", "test@test.com");
        ItemDto itemDto = new ItemDto(itemId, "TestItem", "DescriptionTest", true, 0);
        Item item = ItemMapper.toItem(itemDto, ownerId);
        final Page<Item> page = new PageImpl<>(List.of(item));
        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.empty());


        assertThrows(NotFoundException.class, () -> bookingService.getAllBookingsByBookerId(1,
                BookingState.ALL, 0, 10));


    }

    @Test
    void getAllBookingsByItems() throws NotFoundException, BadRequestException {
        long itemId = 1L;
        long ownerId = 1L;
        long bookerId = 1L;

        BookingDto dto = new BookingDto(1, itemId, LocalDateTime.now(), LocalDateTime.now().plusHours(1));

        User newUser = new User(1, "test", "test@test.com");
        ItemDto itemDto = new ItemDto(itemId, "TestItem", "DescriptionTest", true, 0);
        Item item = ItemMapper.toItem(itemDto, ownerId);
        final Page<Item> page = new PageImpl<>(List.of(item));
        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(newUser));


        when(bookingRepository.bookingsForItem(anyLong(), any()))
                .thenReturn(List.of(BookingMapper.toBooking(dto, bookerId, Status.WAITING)));

        when(bookingRepository.bookingsForItemPast(anyLong(), any(), any()))
                .thenReturn(List.of(BookingMapper.toBooking(dto, bookerId, Status.WAITING)));
        when(bookingRepository.bookingsForItemFuture(anyLong(), any(), any()))
                .thenReturn(List.of(BookingMapper.toBooking(dto, bookerId, Status.WAITING)));
        when(bookingRepository.bookingsForItemCurrent(anyLong(), any(), any()))
                .thenReturn(List.of(BookingMapper.toBooking(dto, bookerId, Status.WAITING)));

        assertThrows(NoSuchElementException.class, () -> bookingService.getAllBookingByItemsByOwnerId(1,
                BookingState.ALL, 0, 10));
        assertThrows(NoSuchElementException.class, () -> bookingService.getAllBookingByItemsByOwnerId(1,
                BookingState.PAST, 0, 10));
        assertThrows(NoSuchElementException.class, () -> bookingService.getAllBookingByItemsByOwnerId(1,
                BookingState.FUTURE, 0, 10));
        assertThrows(NoSuchElementException.class, () -> bookingService.getAllBookingByItemsByOwnerId(1,
                BookingState.CURRENT, 0, 10));
        assertThrows(NoSuchElementException.class, () -> bookingService.getAllBookingByItemsByOwnerId(1,
                BookingState.WAITING, 0, 10));
        assertThrows(NoSuchElementException.class, () -> bookingService.getAllBookingByItemsByOwnerId(1,
                BookingState.REJECTED, 0, 10));
    }

    @Test
    void getAllBookingsByItemsNotFoundUser() throws NotFoundException, BadRequestException {
        long itemId = 1L;
        long ownerId = 1L;
        long bookerId = 1L;

        BookingDto dto = new BookingDto(1, itemId, LocalDateTime.now(), LocalDateTime.now().plusHours(1));

        User newUser = new User(1, "test", "test@test.com");
        ItemDto itemDto = new ItemDto(itemId, "TestItem", "DescriptionTest", true, 0);
        Item item = ItemMapper.toItem(itemDto, ownerId);
        final Page<Item> page = new PageImpl<>(List.of(item));
        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.empty());


        assertThrows(NotFoundException.class, () -> bookingService.getAllBookingByItemsByOwnerId(1,
                BookingState.REJECTED, 0, 10));
    }

    @Test
    void fullBookingTest() {
        User newUser = new User(1, "test", "test@test.com");
        ItemDto itemDto = new ItemDto(1, "TestItem", "DescriptionTest", true, 0);
        Item item = ItemMapper.toItem(itemDto, 1);
        Booking booking = new Booking(1, LocalDateTime.MIN, LocalDateTime.now(), 1, 1, Status.WAITING);
        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> bookingService.getAllBookingByItemsByOwnerId(1,
                BookingState.REJECTED, 0, 10));
    }

    @Test
    void bookingMapperTest() {
        User newUser = new User(1, "test", "test@test.com");
        ItemDto itemDto = new ItemDto(1, "TestItem", "DescriptionTest", true, 0);
        Item item = ItemMapper.toItem(itemDto, 1);
        Booking booking = new Booking(1, LocalDateTime.MIN, LocalDateTime.now(), 1, 1, Status.WAITING);
        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(newUser));
        when(itemRepository.findById(anyLong()))
                .thenReturn(Optional.of(item));
        assertEquals(1,
                BookingMapper.toFullBookingFromBooking(booking, Status.WAITING, itemRepository, userRepository).getId());


    }

}
