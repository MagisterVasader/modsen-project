package com.modsen.route.service.services.exceptions;

public class CountryNotFoundException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "Could not find country with code: (%s)";

    public CountryNotFoundException(String code) {
        super(String.format(MESSAGE_TEMPLATE, code));
    }
}
