package com.modsen.route.service.services.exceptions;

public class CountryAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "Country with code (%s) already exists";

    public CountryAlreadyExistsException(String code) {
        super(String.format(MESSAGE_TEMPLATE, code));
    }
}
