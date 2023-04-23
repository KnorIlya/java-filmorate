package ru.yandex.practicum.filmorate.handler;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage).collect(Collectors.toList());
        log.info("Ошибка пользователя" + errors);
        return new ResponseEntity<>(new ErrorInfo(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorInfo> emptyResultDataAccessExceptionHandler() {
        List<String> errors = new ArrayList<>();
        errors.add("Entity not found");
        return new ResponseEntity<>(new ErrorInfo(errors), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorInfo> notFoundHandler(NotFoundException exception) {
        List<String> errors = new ArrayList<>();
        errors.add(exception.getMessage());
        return new ResponseEntity<>(new ErrorInfo(errors), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> commonHandler(Exception exception) {
        List<String> errors = new ArrayList<>();
        errors.add(exception.getMessage());
        return new ResponseEntity<>(new ErrorInfo(errors), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Value
    private static class ErrorInfo {

        List<String> messages;

        public ErrorInfo(List<String> message) {
            this.messages = message;
        }

    }
}


