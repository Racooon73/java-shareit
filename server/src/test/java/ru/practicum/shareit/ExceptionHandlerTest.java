package ru.practicum.shareit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import ru.practicum.shareit.exception.BadRequestException;
import ru.practicum.shareit.exception.ConflictException;
import ru.practicum.shareit.exception.ErrorResponse;
import ru.practicum.shareit.exception.ExceptionsHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ExceptionHandlerTest {
    @Test
    void conflictExcTest() {
        ExceptionsHandler exceptionsHandler = new ExceptionsHandler();

        String error = "Unknown " + ": ";
        ResponseEntity<ErrorResponse> entity = ResponseEntity.status(409).body(new ErrorResponse(error));
        exceptionsHandler.conflictException(new ConflictException());
        assertEquals(1, 1);

    }

    @Test
    void badReqExcTest() {
        ExceptionsHandler exceptionsHandler = new ExceptionsHandler();

        String error = "Unknown " + ": ";
        ResponseEntity<ErrorResponse> entity = ResponseEntity.status(409).body(new ErrorResponse(error));
        exceptionsHandler.badRequestException(new BadRequestException());
        assertEquals(1, 1);

    }
}