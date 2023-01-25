package com.modsen.route.service.exceptions;

import com.modsen.route.service.services.exceptions.CountryAlreadyExistsException;
import com.modsen.route.service.services.exceptions.CountryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

    private final ExceptionHelper exceptionHelper;

    @ExceptionHandler(CountryAlreadyExistsException.class)
    public ResponseEntity<AppExceptionDto> handelCountryAlreadyExistsException(CountryAlreadyExistsException exception) {
        return exceptionHelper.handleCountryAlreadyExistsException(exception);
    }

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<AppExceptionDto> handelCountryNotFoundException(CountryNotFoundException exception) {
        return exceptionHelper.handleCountryNotFoundException(exception);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<AppExceptionDto> handleRuntimeException(RuntimeException exception) {
        return exceptionHelper.handleRuntimeException(exception);
    }
}
