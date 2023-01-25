package com.modsen.route.service.exceptions;

import com.modsen.route.service.services.exceptions.CountryAlreadyExistsException;
import com.modsen.route.service.services.exceptions.CountryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ExceptionHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHelper.class);

    public ResponseEntity<AppExceptionDto> handleCountryAlreadyExistsException(CountryAlreadyExistsException exception) {
        var exceptionDto = getAppExceptionDto(exception, 400);

        logException(exception, LogLevel.WARN);

        return ResponseEntity
                .status(exceptionDto.getCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(exceptionDto);
    }

    public ResponseEntity<AppExceptionDto> handleCountryNotFoundException(CountryNotFoundException exception) {
        var exceptionDto = getAppExceptionDto(exception, 404);

        logException(exception, LogLevel.WARN);

        return ResponseEntity
                .status(exceptionDto.getCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(exceptionDto);
    }

    public ResponseEntity<AppExceptionDto> handleRuntimeException(RuntimeException exception) {
        var exceptionDto = getAppExceptionDto(exception, 500);

        logException(exception, LogLevel.ERROR);

        return ResponseEntity
                .status(exceptionDto.getCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(exceptionDto);
    }

    private AppExceptionDto getAppExceptionDto(RuntimeException exception, Integer code) {
        AppExceptionDto exceptionDto = new AppExceptionDto();
        exceptionDto.setCode(code);
        exceptionDto.setMessage(exception.getMessage());
        return exceptionDto;
    }

    private void logException(Exception exception, LogLevel level) {
        switch (level) {
            case ERROR:
                LOGGER.error(exception.getMessage());
                break;
            case WARN:
                LOGGER.warn(exception.getMessage());
                break;
            case INFO:
                LOGGER.info(exception.getMessage());
                break;
            default:
                throw new IllegalArgumentException("Unsupported log level: " + level, exception);
        }
    }
}
