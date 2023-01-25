package com.modsen.route.service.services;

import com.modsen.route.service.model.Country;

import java.util.List;

public interface CountryService {

    List<Country> getCountries();

    Country getCountry(String code);

    Country createCountry(Country country);

    Country updateCountry(Country country);

    void deleteCountry(String code);
}
